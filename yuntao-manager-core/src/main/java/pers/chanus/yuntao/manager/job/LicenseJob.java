/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: LicenseJob
 * Author:   Chanus
 * Date:     2020-05-10 10:47:13
 * Description: 系统授权鉴定定时器
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.manager.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import pers.chanus.yuntao.springmvc.LicenseUtils;

import javax.servlet.ServletContext;

/**
 * 系统授权鉴定定时器
 *
 * @author Chanus
 * @date 2020-05-10 10:47:13
 * @since 0.1.8
 */
@Component
public class LicenseJob implements Job {
    @Autowired
    private ServletContext servletContext;

    @Override
    public void execute(JobExecutionContext context) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);// 解决无法依赖注入Bean的问题

        String license = (String) servletContext.getAttribute("license");
        String rsaPublicKey = (String) servletContext.getAttribute("rsaPublicKey");

        servletContext.setAttribute("licenseMessage", LicenseUtils.verify(license, rsaPublicKey));
    }
}
