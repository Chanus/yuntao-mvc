/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import pers.chanus.yuntao.util.StringUtils;

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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n").append(
                "_____.___.             __                     _________   _____________  \n").append(
                "\\__  |   |__ __  _____/  |______    ____     /     \\   \\ /   /\\_   ___ \\ \n").append(
                " /   |   |  |  \\/    \\   __\\__  \\  /  _ \\   /  \\ /  \\   Y   / /    \\  \\/ \n").append(
                " \\____   |  |  /   |  \\  |  / __ \\(  <_> ) /    Y    \\     /  \\     \\____\n").append(
                " / ______|____/|___|  /__| (____  /\\____/  \\____|__  /\\___/    \\______  /\n").append(
                " \\/                 \\/          \\/                 \\/                 \\/ \n").append(
                "                                                              0.2.1\n").append(
                "=========================================================================\n").append(
                "                                 _ooOoo_\n").append(
                "                                o8888888o\n").append(
                "                                88\" . \"88\n").append(
                "                                (| -_- |)\n").append(
                "                                O\\  =  /O\n").append(
                "                             ____/`---'\\____\n").append(
                "                           .'  \\\\|     |//  `.\n").append(
                "                          /  \\\\|||  :  |||//  \\\n").append(
                "                         /  _||||| -:- |||||-  \\\n").append(
                "                         |   | \\\\\\  -  /// |   |\n").append(
                "                         | \\_|  ''\\---/''  |   |\n").append(
                "                         \\  .-\\__  `-`  ___/-. /\n").append(
                "                       ___`. .'  /--.--\\  `. . __\n").append(
                "                    .\"\" '<  `.___\\_<|>_/___.'  >'\"\".\n").append(
                "                   | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n").append(
                "                   \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n").append(
                "              ======`-.____`-.___\\_____/___.-`____.-'======\n").append(
                "                                 `=---='\n").append(
                "              ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n").append(
                "                         佛祖保佑        永无BUG\n").append(
                "                佛曰:\n").append(
                "                       写字楼里写字间，写字间里程序员；\n").append(
                "                       程序人员写程序，又拿程序换酒钱。\n").append(
                "                       酒醒只在网上坐，酒醉还来网下眠；\n").append(
                "                       酒醉酒醒日复日，网上网下年复年。\n").append(
                "                       但愿老死电脑间，不愿鞠躬老板前；\n").append(
                "                       奔驰宝马贵者趣，公交自行程序员。\n").append(
                "                       别人笑我忒疯癫，我笑自己命太贱；\n").append(
                "                       不见满街漂亮妹，哪个归得程序员？\n");
        System.out.println(stringBuilder.toString());

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
