/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: QRCodeUtils
 * Author:   Chanus
 * Date:     2020-05-07 16:26
 * Description: 云道二维码工具类
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import pers.chanus.yuntao.util.image.ImageUtils;
import pers.chanus.yuntao.util.image.Img;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * 二维码工具类，基于zxing库
 *
 * @author Chanus
 * @date 2020-05-07 16:26
 * @since 0.1.8
 */
public class QRCodeUtils {
    /**
     * 生成PNG格式的二维码图片，以byte[]形式表示
     *
     * @param content 内容
     * @param width   宽度
     * @param height  高度
     * @return 图片的byte[]
     * @since 0.1.8
     */
    public static byte[] generatePng(String content, int width, int height) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        generate(content, width, height, ImageUtils.IMAGE_TYPE_PNG, out);
        return out.toByteArray();
    }

    /**
     * 生成PNG格式的二维码图片，以byte[]形式表示
     *
     * @param config 二维码配置
     * @return 图片的byte[]
     * @since 0.1.8
     */
    public static byte[] generatePng(QRCodeConfig config) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        generate(config.setImageType(ImageUtils.IMAGE_TYPE_PNG), out);
        return out.toByteArray();
    }

    /**
     * 生成二维码到文件，二维码图片格式取决于文件的扩展名
     *
     * @param content    文本内容
     * @param width      宽度
     * @param height     高度
     * @param targetFile 目标文件，扩展名决定输出格式
     * @return 目标文件
     * @since 0.1.8
     */
    public static File generate(String content, int width, int height, File targetFile) {
        final BufferedImage image = generate(content, width, height);
        ImageUtils.write(image, targetFile);
        return targetFile;
    }

    /**
     * 生成二维码到文件，二维码图片格式取决于文件的扩展名
     *
     * @param config     二维码配置
     * @param targetFile 目标文件，扩展名决定输出格式
     * @return 目标文件
     * @since 0.1.8
     */
    public static File generate(QRCodeConfig config, File targetFile) {
        final BufferedImage image = generate(config);
        ImageUtils.write(image, targetFile);
        return targetFile;
    }

    /**
     * 生成二维码到文件，二维码图片格式取决于文件的扩展名
     *
     * @param content        文本内容
     * @param width          宽度
     * @param height         高度
     * @param targetFilePath 目标文件路径，扩展名决定输出格式
     * @return 目标文件
     * @since 0.1.8
     */
    public static File generate(String content, int width, int height, String targetFilePath) {
        final BufferedImage image = generate(content, width, height);
        File targetFile = new File(targetFilePath);
        ImageUtils.write(image, targetFile);
        return targetFile;
    }

    /**
     * 生成二维码到文件，二维码图片格式取决于文件的扩展名
     *
     * @param config         二维码配置
     * @param targetFilePath 目标文件路径，扩展名决定输出格式
     * @return 目标文件
     * @since 0.1.8
     */
    public static File generate(QRCodeConfig config, String targetFilePath) {
        final BufferedImage image = generate(config);
        File targetFile = new File(targetFilePath);
        ImageUtils.write(image, targetFile);
        return targetFile;
    }

    /**
     * 生成二维码到输出流
     *
     * @param content   文本内容
     * @param width     宽度
     * @param height    高度
     * @param imageType 图片类型（图片扩展名），见{@link ImageUtils}
     * @param out       目标流
     * @since 0.1.8
     */
    public static void generate(String content, int width, int height, String imageType, OutputStream out) {
        final BufferedImage image = generate(content, width, height);
        ImageUtils.write(image, imageType, out);
    }

    /**
     * 生成二维码到输出流
     *
     * @param config 二维码配置
     * @param out    目标流
     * @since 0.1.8
     */
    public static void generate(QRCodeConfig config, OutputStream out) {
        final BufferedImage image = generate(config);
        ImageUtils.write(image, config.getImageType(), out);
    }

    /**
     * 生成二维码图片
     *
     * @param content 文本内容
     * @param width   宽度
     * @param height  高度
     * @return 二维码图片（黑白）
     * @since 0.1.8
     */
    public static BufferedImage generate(String content, int width, int height) {
        return generate(new QRCodeConfig(content, width, height));
    }

    /**
     * 生成二维码图片
     *
     * @param config 二维码配置
     * @return 二维码图片
     * @since 0.1.8
     */
    public static BufferedImage generate(QRCodeConfig config) {
        return generate(BarcodeFormat.QR_CODE, config);
    }

    /**
     * 生成二维码或条形码图片
     *
     * @param format  格式，可选二维码或者条形码
     * @param content 文本内容
     * @param width   宽度
     * @param height  高度
     * @return 二维码图片（黑白）
     * @since 0.1.8
     */
    public static BufferedImage generate(BarcodeFormat format, String content, int width, int height) {
        return generate(format, new QRCodeConfig(content, width, height));
    }

    /**
     * 生成二维码或条形码图片<br>
     * 只有二维码时QRCodeConfig中的图片才有效
     *
     * @param format 格式，可选二维码、条形码等
     * @param config 二维码配置
     * @return 二维码图片（黑白）
     * @since 0.1.8
     */
    public static BufferedImage generate(BarcodeFormat format, QRCodeConfig config) {
        final BitMatrix bitMatrix = encode(config.getContent(), format, config);
        final BufferedImage image = toImage(bitMatrix, config.getForeColor(), config.getBackColor());
        final Image logoImage = config.getLogoImage();
        if (logoImage != null && BarcodeFormat.QR_CODE == format) {
            // 只有二维码可以贴图
            final int qrWidth = image.getWidth();
            final int qrHeight = image.getHeight();
            int width;
            int height;
            // 按照最短的边做比例缩放
            if (qrWidth < qrHeight) {
                width = (int) (qrWidth * config.getLogoRatio());
                height = logoImage.getHeight(null) * width / logoImage.getWidth(null);
            } else {
                height = (int) (qrHeight * config.getLogoRatio());
                width = logoImage.getWidth(null) * height / logoImage.getHeight(null);
            }

            Img.from(image).pressImage(Img.from(logoImage).round(0.3).getImg(), new Rectangle(width, height), 1);
        }
        return image;
    }

    /**
     * 将文本内容编码为条形码或二维码<br>
     * 只有二维码时QRCodeConfig中的图片才有效
     *
     * @param content 文本内容
     * @param format  格式枚举
     * @param config  二维码配置信息
     * @return {@link BitMatrix}
     * @since 0.1.8
     */
    public static BitMatrix encode(String content, BarcodeFormat format, QRCodeConfig config) {
        final MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        if (config == null) {
            // 默认配置
            config = new QRCodeConfig();
        }
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, format, config.getWidth(), config.getHeight(), config.toHints());
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return bitMatrix;
    }

    /**
     * 将文本内容编码为条形码或二维码<br>
     * 只有二维码时QRCodeConfig中的图片才有效
     *
     * @param content 文本内容
     * @param format  格式枚举
     * @param width   宽度
     * @param height  高度
     * @return {@link BitMatrix}
     * @since 0.1.8
     */
    public static BitMatrix encode(String content, BarcodeFormat format, int width, int height) {
        return encode(content, format, new QRCodeConfig(width, height));
    }

    /**
     * 将文本内容编码为二维码
     *
     * @param content 文本内容
     * @param config  二维码配置信息
     * @return {@link BitMatrix}
     * @since 0.1.8
     */
    public static BitMatrix encode(String content, QRCodeConfig config) {
        return encode(content, BarcodeFormat.QR_CODE, config);
    }

    /**
     * 将文本内容编码为二维码
     *
     * @param content 文本内容
     * @param width   宽度
     * @param height  高度
     * @return {@link BitMatrix}
     * @since 0.1.8
     */
    public static BitMatrix encode(String content, int width, int height) {
        return encode(content, BarcodeFormat.QR_CODE, width, height);
    }

    /**
     * 解码二维码图片为文本
     *
     * @param qrCodeInputstream 二维码输入流
     * @return 解码文本
     * @since 0.1.8
     */
    public static String decode(InputStream qrCodeInputstream) {
        return decode(ImageUtils.read(qrCodeInputstream));
    }

    /**
     * 解码二维码图片为文本
     *
     * @param qrCodeFile 二维码文件
     * @return 解码文本
     * @since 0.1.8
     */
    public static String decode(File qrCodeFile) {
        return decode(ImageUtils.read(qrCodeFile));
    }

    /**
     * 解码二维码图片为文本
     *
     * @param qrCodeFilePath 二维码文件路径
     * @return 解码文本
     * @since 0.1.8
     */
    public static String decode(String qrCodeFilePath) {
        return decode(ImageUtils.read(qrCodeFilePath));
    }

    /**
     * 将二维码图片解码为文本
     *
     * @param image {@link Image} 二维码图片
     * @return 解码后的文本
     * @since 0.1.8
     */
    public static String decode(Image image) {
        return decode(image, true, false);
    }

    /**
     * 将二维码图片解码为文本
     *
     * @param image         {@link Image} 二维码图片
     * @param isTryHarder   是否优化精度
     * @param isPureBarcode 是否使用复杂模式，扫描带logo的二维码设为true
     * @return 解码后的文本
     * @since 0.1.8
     */
    public static String decode(Image image, boolean isTryHarder, boolean isPureBarcode) {
        return decode(image, isTryHarder, isPureBarcode, QRCodeConfig.CHARSET);
    }

    /**
     * 将二维码图片解码为文本
     *
     * @param image         {@link Image} 二维码图片
     * @param isTryHarder   是否优化精度
     * @param isPureBarcode 是否使用复杂模式，扫描带logo的二维码设为true
     * @param charset       编码格式
     * @return 解码后的文本
     * @since 0.1.8
     */
    public static String decode(Image image, boolean isTryHarder, boolean isPureBarcode, String charset) {
        final MultiFormatReader formatReader = new MultiFormatReader();

        final LuminanceSource source = new BufferedImageLuminanceSource(ImageUtils.toBufferedImage(image));
        final BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));

        final HashMap<DecodeHintType, Object> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, charset);
        // 优化精度
        hints.put(DecodeHintType.TRY_HARDER, isTryHarder);
        // 复杂模式，开启PURE_BARCODE模式
        hints.put(DecodeHintType.PURE_BARCODE, isPureBarcode);
        Result result;
        try {
            result = formatReader.decode(binaryBitmap, hints);
        } catch (NotFoundException e) {
            // 报错尝试关闭复杂模式
            hints.remove(DecodeHintType.PURE_BARCODE);
            try {
                result = formatReader.decode(binaryBitmap, hints);
            } catch (NotFoundException notFoundException) {
                throw new RuntimeException("qr_code decode fail.", notFoundException);
            }
        }
        return result.getText();
    }

    /**
     * BitMatrix转BufferedImage
     *
     * @param matrix    BitMatrix
     * @param foreColor 前景色
     * @param backColor 背景色(null表示透明背景)
     * @return BufferedImage
     * @since 0.1.8
     */
    public static BufferedImage toImage(BitMatrix matrix, int foreColor, Integer backColor) {
        final int width = matrix.getWidth();
        final int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, backColor == null ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (matrix.get(x, y)) {
                    image.setRGB(x, y, foreColor);
                } else if (null != backColor) {
                    image.setRGB(x, y, backColor);
                }
            }
        }
        return image;
    }
}
