/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: QRCodeUtilsTest
 * Author:   Chanus
 * Date:     2020-05-08 9:07
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test;

import com.google.zxing.BarcodeFormat;
import org.junit.Test;
import pers.chanus.yuntao.util.DateUtils;
import pers.chanus.yuntao.util.image.ImageUtils;
import pers.chanus.yuntao.util.qrcode.QRCodeConfig;
import pers.chanus.yuntao.util.qrcode.QRCodeUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * QRCodeUtils工具类测试
 *
 * @author Chanus
 * @date 2020-05-08 9:07
 * @since 0.1.8
 */
public class QRCodeUtilsTest {
    // private String path = "F:/";// windows
    private String path = "/Users/Chanus/Documents/";// mac

    @Test
    public void generateTest1() {
        String imageName = System.currentTimeMillis() + ".png";
        String qrCodePath = path + imageName;
        QRCodeUtils.generate("test", 300, 300, new File(qrCodePath));
    }

    @Test
    public void generateTest2() {
        String imageName = System.currentTimeMillis() + ".png";
        String qrCodePath = path + imageName;
        QRCodeConfig config = QRCodeConfig.create().setContent("1234567890")
                .setWidth(400).setHeight(400).setForeColor(Color.GREEN).setBackColor(Color.WHITE)
                .setMargin(1).setFontSize(18).setLogoImage(path + "头像.jpg")
                .setLogoRatio(0.2f).setDesc("测试二维码").setDate(DateUtils.getDateToday())
                .setBottomStart(new int[]{0, 350}).setBottomEnd(new int[]{350,350});
        QRCodeUtils.generate(config, new File(qrCodePath));
    }

    @Test
    public void generateTest3() {
        String imageName = System.currentTimeMillis() + ".png";
        String qrCodePath = path + imageName;
        BufferedImage image = QRCodeUtils.generate(BarcodeFormat.CODE_128, "11111111", 1000, 300);
        ImageUtils.write(image, new File(qrCodePath));
    }

    @Test
    public void generateTest4() {
        String imageName = System.currentTimeMillis() + ".png";
        String qrCodePath = path + imageName;
        QRCodeConfig config = QRCodeConfig.create().setContent("1234567890")
                .setWidth(400).setHeight(400).setForeColor(Color.GREEN).setBackColor(Color.LIGHT_GRAY)
                .setMargin(1).setFontSize(18);
        QRCodeUtils.generate(config, qrCodePath);
    }

    @Test
    public void decode1() {
        String string = QRCodeUtils.decode(new File(path + "1588938888244.png"));
        System.out.println(string);
    }

    @Test
    public void decode2() {
        String string = QRCodeUtils.decode(path + "1588938932468.png");
        System.out.println(string);
    }
}
