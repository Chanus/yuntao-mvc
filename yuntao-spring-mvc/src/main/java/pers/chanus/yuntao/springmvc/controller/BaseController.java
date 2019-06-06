/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import pers.chanus.yuntao.commons.constant.MsgCode;
import pers.chanus.yuntao.commons.pojo.CustomMap;
import pers.chanus.yuntao.commons.pojo.Message;
import pers.chanus.yuntao.util.RandomUtils;
import pers.chanus.yuntao.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Controller基类
 *
 * @author Chanus
 * @date 2018-09-01 02:37:44
 * @since 0.0.1
 */
public abstract class BaseController {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 设置List的最大长度
        binder.setAutoGrowCollectionLimit(10000);
    }

    /**
     * 获取Request对象
     *
     * @return Request对象
     * @since 0.0.1
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Response对象
     *
     * @return Response对象
     * @since 0.0.1
     */
    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取Session对象
     *
     * @return Session对象
     * @since 0.0.1
     */
    protected HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    /**
     * 将请求参数组装成Map
     *
     * @return 请求参数Map集合
     * @since 0.0.1
     */
    protected CustomMap getParams() {
        CustomMap params = CustomMap.get();
        Map<String, String[]> parameterMap = getRequest().getParameterMap();
        for (String k : parameterMap.keySet()) {
            String[] v = parameterMap.get(k);
            if (v.length == 1) {
                if (StringUtils.isNotBlank(v[0]))
                    params.put(k, v[0]);
            } else if (v.length > 1) {
                params.put(k, v);
            }
        }

        return params;
    }

    /**
     * 单文件上传
     *
     * @param file
     * @param path 文件保存路径
     * @return {@code Message}信息
     * @since 0.0.1
     */
    protected Message upload(MultipartFile file, String path) {
        Message message = new Message(MsgCode.SUCCESS, "文件上传成功");
        if (file != null && file.getSize() > 0) {
            // 保存路径
            String realFileName, fileName;
            realFileName = file.getOriginalFilename();
            fileName = RandomUtils.getRandomUniqueNo() + realFileName.substring(realFileName.lastIndexOf("."));
            File f = new File(getSession().getServletContext().getAttribute("parentRealCtx") + "/" + path, fileName);
            if (!f.exists())
                f.mkdirs();
            try {
                file.transferTo(f);
                // 返回文件保存的路径，文件真实名称，文件保存的名称
                message.setMap(CustomMap.get().putNext("filePath", path).putNext("realFileName", realFileName).putNext("fileName", fileName));
            } catch (Exception e) {// 图片上传失败
                return Message.initMsg(MsgCode.FAIL, "文件上传失败");
            }
        }

        return message;
    }

    /**
     * 文件下载
     *
     * @param fileParh 文件存储绝对路径
     * @param realName 返回的文件名称，若为空则返回原始文件名称
     * @since 0.0.1
     */
    protected void download(String fileParh, String realName) {
        HttpServletRequest request = getRequest();
        HttpServletResponse response = getResponse();
        response.reset();// 清空输出流
        // 设置响应类型
        response.setContentType("text/html;charset=UTF-8");
        response.setBufferSize(10240);// 设定缓冲大小

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 解决中文名称乱码问题
            request.setCharacterEncoding("UTF-8");
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {// 火狐浏览器
                realName = new String(realName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            } else {// IE浏览器、谷歌浏览器
                realName = java.net.URLEncoder.encode(realName, "UTF-8");
            }

            response.setHeader("Content-Disposition", "attachment; filename=" + (StringUtils.isBlank(realName) ? fileParh.substring(fileParh.lastIndexOf("/")) : realName) + fileParh.substring(fileParh.lastIndexOf(".")));
            response.setHeader("Content-Length", String.valueOf(new File(fileParh).length()));

            bis = new BufferedInputStream(new FileInputStream(fileParh));
            bos = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            LOGGER.error("文件下载异常", e);
        } finally {
            if (bos != null) {
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("关闭输出流异常", e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    LOGGER.error("关闭输入流异常", e);
                }
            }
        }
    }
}
