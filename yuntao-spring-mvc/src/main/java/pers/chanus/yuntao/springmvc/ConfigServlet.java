/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import com.chanus.yuntao.utils.core.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.nio.file.Files;

/**
 * 初始化全局参数
 *
 * @author Chanus
 * @date 2018-09-01 14:01:12
 * @since 0.0.1
 */
public class ConfigServlet extends HttpServlet {
    private static final long serialVersionUID = -6682155111608500310L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServlet.class);

    /**
     * 初始化全局参数
     *
     * @since 0.0.1
     */
    public void init() {
        String logo = "\n" +
                "_____.___.             __                     _________   _____________  \n" +
                "\\__  |   |__ __  _____/  |______    ____     /     \\   \\ /   /\\_   ___ \\ \n" +
                " /   |   |  |  \\/    \\   __\\__  \\  /  _ \\   /  \\ /  \\   Y   / /    \\  \\/ \n" +
                " \\____   |  |  /   |  \\  |  / __ \\(  <_> ) /    Y    \\     /  \\     \\____\n" +
                " / ______|____/|___|  /__| (____  /\\____/  \\____|__  /\\___/    \\______  /\n" +
                " \\/                 \\/          \\/                 \\/                 \\/ \n" +
                "                                                              v0.6.0\n";
        System.out.println(logo);

        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("ctx", servletContext.getContextPath().replaceAll("\\\\", "/"));
        servletContext.setAttribute("parentCtx", StringUtils.isBlank(servletContext.getContextPath()) ? "/" : new File(servletContext.getContextPath()).getParent().replaceAll("\\\\", "/"));
        servletContext.setAttribute("parentRealCtx", new File(servletContext.getRealPath("")).getParent().replaceAll("\\\\", "/"));
        try {
            servletContext.setAttribute("system_name", ConfigUtils.getProperty("system.name"));
            servletContext.setAttribute("system_version", ConfigUtils.getProperty("system.version"));
            servletContext.setAttribute("system_copyright", ConfigUtils.getProperty("system.copyright"));

            // 获取验证授权信息
            File licenseFile = new ClassPathResource("lic/license.lic").getFile();
            File rsaFile = new ClassPathResource("lic/rsa_pub.txt").getFile();
            String license = new String(Files.readAllBytes(licenseFile.toPath()));
            String rsaPublicKey = new String(Files.readAllBytes(rsaFile.toPath()));
            servletContext.setAttribute("license", license);
            servletContext.setAttribute("rsaPublicKey", rsaPublicKey);
            servletContext.setAttribute("licenseMessage", LicenseUtils.verify(license, rsaPublicKey));
        } catch (Exception e) {
            LOGGER.error("系统初始化参数配置有误", e);
        }
    }
}
