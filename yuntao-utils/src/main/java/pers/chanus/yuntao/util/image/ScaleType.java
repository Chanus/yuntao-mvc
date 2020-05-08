/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: QRCodeUtils
 * Author:   Chanus
 * Date:     2020-05-08 10:44
 * Description: 云道图片处理工具类
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.image;

import java.awt.Image;

/**
 * 图片缩略算法类型
 *
 * @author Chanus
 * @date 2020-05-08 10:44
 * @since 0.1.8
 */
public enum ScaleType {
    /**
     * 默认
     */
    DEFAULT(Image.SCALE_DEFAULT),
    /**
     * 快速
     */
    FAST(Image.SCALE_FAST),
    /**
     * 平滑
     */
    SMOOTH(Image.SCALE_SMOOTH),
    /**
     * 使用 ReplicateScaleFilter 类中包含的图像缩放算法
     */
    REPLICATE(Image.SCALE_REPLICATE),
    /**
     * Area Averaging算法
     */
    AREA_AVERAGING(Image.SCALE_AREA_AVERAGING);

    /**
     * 缩放方式
     */
    private final int value;

    /**
     * 构造
     *
     * @param value 缩放方式
     * @see Image#SCALE_DEFAULT
     * @see Image#SCALE_FAST
     * @see Image#SCALE_SMOOTH
     * @see Image#SCALE_REPLICATE
     * @see Image#SCALE_AREA_AVERAGING
     */
    ScaleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
