/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: QRCodeUtils
 * Author:   Chanus
 * Date:     2020-05-07 16:51
 * Description: 云道二维码工具类
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.qrcode;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import pers.chanus.yuntao.util.image.ImageUtils;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

/**
 * 二维码配置
 *
 * @author Chanus
 * @date 2020-05-07 16:51
 * @since 0.1.8
 */
public class QRCodeConfig {
    public static final int BLACK = 0xFF000000;
    public static final int WHITE = 0xFFFFFFFF;
    /**
     * 编码格式
     */
    public static final String CHARSET = "UTF-8";

    /**
     * 二维码正文
     */
    private String content;
    /**
     * 二维码宽度
     */
    private int width;
    /**
     * 二维码高度
     */
    private int height;
    /**
     * 二维码前景色（二维码颜色），默认黑色
     */
    private int foreColor = BLACK;
    /**
     * 二维码背景色，默认白色，null表示透明
     */
    private Integer backColor = WHITE;
    /**
     * 二维码边距
     */
    private Integer margin = 2;
    /**
     * 纠错级别，L（QR_ECLEVEL_L，7%），M（QR_ECLEVEL_M，15%），Q（QR_ECLEVEL_Q，25%），H（QR_ECLEVEL_H，30%）
     */
    private ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.H;
    /**
     * 编码方式
     */
    private String charset = CHARSET;
    /**
     * 图片类型（图片扩展名），见{@link ImageUtils}
     */
    private String imageType = ImageUtils.IMAGE_TYPE_PNG;
    /**
     * 字体大小
     */
    private int fontSize = 12;
    /**
     * 二维码logo
     */
    private Image logoImage;
    /**
     * 二维码logo所占二维码比例
     */
    private float logoRatio = 0.20f;
    /**
     * 二维码下文字
     */
    private String desc;
    /**
     * 二维码下方日期
     */
    private String date;
    /**
     * 二维码最下边的开始坐标
     */
    private int[] bottomStart;
    /**
     * 二维码最下边的结束坐标
     */
    private int[] bottomEnd;

    /**
     * 创建QrConfig
     *
     * @return QRCodeConfig
     * @since 0.1.8
     */
    public static QRCodeConfig create() {
        return new QRCodeConfig();
    }

    /**
     * 构造
     *
     * @param width  宽
     * @param height 高
     * @since 0.1.8
     */
    public QRCodeConfig(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * 构造
     *
     * @param content 二维码正文
     * @param width   宽
     * @param height  高
     * @since 0.1.8
     */
    public QRCodeConfig(String content, int width, int height) {
        this.content = content;
        this.width = width;
        this.height = height;
    }

    /**
     * 构造，默认宽高为400
     *
     * @since 0.1.8
     */
    public QRCodeConfig() {
        this(400, 400);
    }

    public String getContent() {
        return content;
    }

    public QRCodeConfig setContent(String content) {
        this.content = content;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public QRCodeConfig setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public QRCodeConfig setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getForeColor() {
        return foreColor;
    }

    public QRCodeConfig setForeColor(Color foreColor) {
        if (foreColor != null)
            this.foreColor = foreColor.getRGB();

        return this;
    }

    public Integer getBackColor() {
        return backColor;
    }

    public QRCodeConfig setBackColor(Color backColor) {
        if (backColor == null)
            this.backColor = null;
        else
            this.backColor = backColor.getRGB();

        return this;
    }

    public Integer getMargin() {
        return margin;
    }

    public QRCodeConfig setMargin(Integer margin) {
        this.margin = margin;
        return this;
    }

    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return errorCorrectionLevel;
    }

    public QRCodeConfig setErrorCorrectionLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        this.errorCorrectionLevel = errorCorrectionLevel;
        return this;
    }

    public String getCharset() {
        return charset;
    }

    public QRCodeConfig setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public String getImageType() {
        return imageType;
    }

    public QRCodeConfig setImageType(String imageType) {
        this.imageType = imageType;
        return this;
    }

    public int getFontSize() {
        return fontSize;
    }

    public QRCodeConfig setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Image getLogoImage() {
        return logoImage;
    }

    public QRCodeConfig setLogoImage(Image logoImage) {
        this.logoImage = logoImage;
        return this;
    }

    public QRCodeConfig setLogoImage(File logoFile) {
        return setLogoImage(ImageUtils.read(logoFile));
    }

    public QRCodeConfig setLogoImage(String logoPath) {
        return setLogoImage(new File(logoPath));
    }

    public float getLogoRatio() {
        return logoRatio;
    }

    public QRCodeConfig setLogoRatio(float logoRatio) {
        this.logoRatio = logoRatio;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public QRCodeConfig setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getDate() {
        return date;
    }

    public QRCodeConfig setDate(String date) {
        this.date = date;
        return this;
    }

    public int[] getBottomStart() {
        return bottomStart;
    }

    public QRCodeConfig setBottomStart(int[] bottomStart) {
        this.bottomStart = bottomStart;
        return this;
    }

    public int[] getBottomEnd() {
        return bottomEnd;
    }

    public QRCodeConfig setBottomEnd(int[] bottomEnd) {
        this.bottomEnd = bottomEnd;
        return this;
    }

    /**
     * 转换为Zxing的二维码配置
     *
     * @return 二维码配置
     */
    public HashMap<EncodeHintType, Object> toHints() {
        // 配置
        final HashMap<EncodeHintType, Object> hints = new HashMap<>();
        if (this.charset != null) {
            hints.put(EncodeHintType.CHARACTER_SET, charset);
        }
        if (this.errorCorrectionLevel != null) {
            hints.put(EncodeHintType.ERROR_CORRECTION, this.errorCorrectionLevel);
        }
        if (this.margin != null) {
            hints.put(EncodeHintType.MARGIN, this.margin);
        }
        return hints;
    }

    @Override
    public String toString() {
        return "QRCodeConfig [" +
                "content=" + content +
                ", width=" + width +
                ", height=" + height +
                ", foreColor=" + foreColor +
                ", backColor=" + backColor +
                ", margin=" + margin +
                ", charset=" + charset +
                ", fontSize=" + fontSize +
                ", logoRatio=" + logoRatio +
                ", desc=" + desc +
                ", date=" + date +
                "]";
    }
}