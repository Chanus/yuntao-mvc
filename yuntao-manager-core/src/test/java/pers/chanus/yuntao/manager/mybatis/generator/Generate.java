/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.manager.mybatis.generator;

import java.io.File;

/**
 * 生成Mybatis代码
 *
 * @author Chanus
 * @date 2018-09-01 16:30:16
 * @since 0.0.1
 */
public class Generate {

    public static void main(String[] args) {
        pers.chanus.yuntao.mybatis.generator.Generate.generator(new File(new File("").getAbsolutePath(), "src/main/resources/development/generatorConfig.xml"));
    }

}
