/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.springmvc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

/**
 * 读取应用配置属性文件信息工具类
 *
 * @author Chanus
 * @date 2018-09-01 01:32:57
 * @since 0.0.1
 */
public class ConfigUtils extends PropertyPlaceholderConfigurer {

    private static Properties properties;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        properties = props;
    }

    /**
     * 根据配置文件中的属性名称获取属性值
     *
     * @param name 属性名称
     * @return 属性值
     * @since 0.0.1
     */
    public static String getProperty(String name) {
        return properties.getProperty(name);
    }
}