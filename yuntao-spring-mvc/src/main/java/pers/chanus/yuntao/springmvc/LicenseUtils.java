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

import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.util.*;
import pers.chanus.yuntao.util.encrypt.RSAUtils;
import pers.chanus.yuntao.util.encrypt.SHAUtils;

import java.util.Date;
import java.util.Map;

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
     * @param limit      证书时效
     * @param privateKey RSA私钥
     * @return 证书内容
     * @since 0.1.8
     */
    public static String createLicense(String name, String version, String macAddress, String limit, String privateKey) {
        String text = "name=" + name + "&version=" + version + "&macAddress=" + macAddress + "&limit=" + limit;
        String sign = SHAUtils.sha256(text);
        text = text + "&sign=" + sign;

        return RSAUtils.encryptByPrivateKey(text, privateKey);
    }

    /**
     * 生成证书到文件
     *
     * @param name       产品名称
     * @param version    产品版本号
     * @param macAddress 设备MAC地址，多个用","分隔
     * @param limit      证书时效
     * @param privateKey RSA私钥
     * @param path       文件路径
     * @since 0.1.8
     */
    public static void createLicense(String name, String version, String macAddress, String limit, String privateKey, String path) {
        String ciphertext = createLicense(name, version, macAddress, limit, privateKey);
        FileUtils.write(path, ciphertext, false);
    }

    /**
     * 校验证书信息
     *
     * @param license   证书密文
     * @param publicKey RSA公钥
     * @return
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
        String sign = map.get("sign");
        String signText = "name=" + name + "&version=" + version + "&macAddress=" + macAddress + "&limit=" + limit;

        if (!SHAUtils.verifySHA256(signText, sign))
            return Message.fail("证书异常，签名不一致");

        if (!StringUtils.contains(macAddress, SystemUtils.HOST_MAC))
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
