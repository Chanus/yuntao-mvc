/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MediaTypeEnum
 * Author:   Chanus
 * Date:     2020-06-06 23:48:00
 * Description: 媒体文件类型枚举
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

/**
 * 媒体文件类型枚举
 *
 * @author Chanus
 * @date 2020-06-06 23:48:00
 * @since 0.1.9
 */
public enum MediaTypeEnum {
    // 图片
    IMAGE,
    // 语音
    VOICE,
    // 视频
    VIDEO,
    // 缩略图
    THUMB,
    // 图文
    NEWS;

    public String get() {
        return this.name().toLowerCase();
    }
}
