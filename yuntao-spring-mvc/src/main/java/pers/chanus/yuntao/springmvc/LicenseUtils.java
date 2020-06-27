/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: LicenseUtils
 * Author:   Chanus
 * Date:     2020-05-09 16:23
 * Description: 处理系统授权
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.springmvc;

import pers.chanus.yuntao.commons.constant.ConfigConsts;
import pers.chanus.yuntao.commons.pojo.Message;
import com.chanus.yuntao.utils.core.*;
import com.chanus.yuntao.utils.core.encrypt.RSAUtils;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 系统授权工具类
 *
 * @author Chanus
 * @date 2020-05-09 16:23
 * @since 0.1.8
 */
public class LicenseUtils {
    /**
     * 生成证书
     *
     * @param name       产品名称
     * @param version    产品版本号
     * @param macAddress 设备MAC地址，多个用","分隔
     * @param limit      证书时效，为yyyy-MM-dd格式日期字符串，若为0则表示永久有效
     * @param enable     是否启用证书授权，1-启用，0-禁用
     * @param privateKey RSA私钥
     * @return 证书内容
     * @since 0.1.8
     */
    public static String createLicense(String name, String version, String macAddress, String limit, String enable, String privateKey) {
        String text = "name=" + name + "&version=" + version + "&macAddress=" + macAddress + "&limit=" + limit + "&enable=" + enable;
        String sign = RSAUtils.sign(text, privateKey);
        text = text + "&sign=" + sign;

        return RSAUtils.encryptByPrivateKey(text, privateKey);
    }

    /**
     * 生成证书到文件
     *
     * @param name       产品名称
     * @param version    产品版本号
     * @param macAddress 设备MAC地址，多个用","分隔
     * @param limit      证书时效，为yyyy-MM-dd格式日期字符串，若为0则表示永久有效
     * @param enable     是否启用证书授权，1-启用，0-禁用
     * @param privateKey RSA私钥
     * @param path       文件路径
     * @since 0.1.8
     */
    public static void createLicense(String name, String version, String macAddress, String limit, String enable, String privateKey, String path) {
        String ciphertext = createLicense(name, version, macAddress, limit, enable, privateKey);
        FileUtils.write(path, ciphertext, false);
    }

    /**
     * 校验证书信息
     *
     * @param license   证书密文
     * @param publicKey RSA公钥
     * @return 证书校验结果
     * @since 0.1.8
     */
    public static Message verify(String license, String publicKey) {
        if (StringUtils.isBlank(license))
            return Message.fail("证书不存在");
        if (StringUtils.isBlank(publicKey))
            return Message.fail("公钥不存在");

        String text = RSAUtils.decryptByPublicKey(license, publicKey);

        Map<String, String> map = UrlUtils.getParamsMap(text, null);
        if (CollectionUtils.isEmpty(map))
            return Message.fail("证书异常");

        String name = map.get("name");
        String version = map.get("version");
        String macAddress = map.get("macAddress");
        String limit = map.get("limit");
        String enable = map.get("enable");
        String sign = map.get("sign");
        String signText = "name=" + name + "&version=" + version + "&macAddress=" + macAddress + "&limit=" + limit + "&enable=" + enable;

        if (!RSAUtils.verify(signText, sign, publicKey))
            return Message.fail("证书异常，签名不一致");

        if (ConfigConsts.STATUS_NO.equals(enable))
            return Message.success("校验成功");

        Set<String> macSet = SystemUtils.getHostMac();
        if (CollectionUtils.isEmpty(macSet))
            return Message.fail("无法获取本机 Mac 地址");

        boolean b = false;
        for (String mac : macSet) {
            if (StringUtils.contains(macAddress, mac)) {
                b = true;
                break;
            }
        }
        if (!b)
            return Message.fail("机器码不一致");

        if (StringUtils.isBlank(limit))
            return Message.fail("证书过期");
        if (!"0".equals(limit)) {
            Date limitDate = DateUtils.parseDate(limit);
            if (DateUtils.compare(limitDate, new Date()) == -1)
                return Message.fail("证书过期");
        }

        return Message.success("校验成功");
    }
}
