/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成Mybatis代码
 *
 * @author Chanus
 * @date 2018-09-01 12:50:45
 * @since 0.0.1
 */
public class Generate {
    /**
     * 自动生成Mybatis代码
     *
     * @param cfgPath Mybatis配置文件路径，默认放到src下面
     * @since 0.0.1
     */
    public static void generator(String cfgPath) {
        List<String> warnings = new ArrayList<>();
        /* String cfgPath = "/generatorConfig.xml";// 配置文件的路径，默认放到src下面 */
        URL url = Generate.class.getResource(cfgPath);
        String file = url.getFile();
        File configFile = new File(file);
        generator(configFile, warnings);
    }

    /**
     * 自动生成Mybatis代码
     *
     * @param configFile Mybatis配置文件
     * @since 0.0.1
     */
    public static void generator(File configFile) {
        List<String> warnings = new ArrayList<>();
        generator(configFile, warnings);
    }

    private static void generator(File configFile, List<String> warnings) {
        ConfigurationParser cfgParser = new ConfigurationParser(warnings);// 配置文件解析器
        Configuration config = null;
        try {
            config = cfgParser.parseConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator generator = null;
        try {
            generator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            generator.generate(null);
            System.out.println("Mybatis 代码生成成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String cfgPath = "/generatorConfig.xml";// 配置文件的路径，默认放到src下面
        Generate.generator(cfgPath);
    }
}
