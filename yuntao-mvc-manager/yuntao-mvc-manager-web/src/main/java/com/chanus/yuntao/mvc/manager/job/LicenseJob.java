/*
 * Copyright (c) 2020 Chanus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chanus.yuntao.mvc.manager.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.chanus.yuntao.mvc.framework.LicenseUtils;

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
