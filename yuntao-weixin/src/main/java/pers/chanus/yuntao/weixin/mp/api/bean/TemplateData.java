/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: TemplateData
 * Author:   Chanus
 * Date:     2020-05-18 13:14:22
 * Description: 模板消息数据对象
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 模板消息数据对象
 *
 * @author Chanus
 * @date 2020-05-18 13:14:22
 * @since 0.1.9
 */
public class TemplateData implements Serializable {
    private static final long serialVersionUID = -468042346786904801L;
    /**
     * 默认模板内容字体颜色
     */
    private static final String DEFAULT_COLOR = "#173177";
    /**
     * 接收者openid
     */
    private String touser;
    /**
     * 模板ID
     */
    private String template_id;
    /**
     * 模板跳转链接（海外帐号没有跳转能力）
     */
    private String url;
    /**
     * 模板数据
     */
    private final TemplateDataItem data;
    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    private MiniProgramData miniprogram;

    public static TemplateData create() {
        return new TemplateData();
    }

    private TemplateData() {
        this.data = new TemplateDataItem();
    }

    public String getTouser() {
        return touser;
    }

    public TemplateData setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public TemplateData setTemplate_id(String template_id) {
        this.template_id = template_id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TemplateData setUrl(String url) {
        this.url = url;
        return this;
    }

    public TemplateDataItem getData() {
        return data;
    }

    public TemplateData setData(String key, String value, String color) {
        data.put(key, new DataItem(value, color));
        return this;
    }

    public TemplateData setData(String key, String value) {
        data.put(key, new DataItem(value));
        return this;
    }

    public MiniProgramData getMiniprogram() {
        return miniprogram;
    }

    public TemplateData setMiniprogram(String appid, String pagepath) {
        this.miniprogram = new MiniProgramData(appid, pagepath);
        return this;
    }

    /**
     * 将当前对象转化成 json 字符串
     *
     * @return json 字符串
     */
    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    /**
     * 模板数据
     */
    public static class TemplateDataItem extends HashMap<String, DataItem> {
        private static final long serialVersionUID = 2256855913305513400L;

        public TemplateDataItem() {
        }

        public TemplateDataItem(String key, DataItem dataItem) {
            this.put(key, dataItem);
        }
    }

    /**
     * 模板数据内容
     */
    public static class DataItem {
        private Object value;
        private String color;

        public DataItem() {
        }

        public DataItem(Object value) {
            this(value, DEFAULT_COLOR);
        }

        public DataItem(Object value, String color) {
            this.value = value;
            this.color = color;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    public static class MiniProgramData {
        /**
         * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
         */
        private String appid;
        /**
         * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏
         */
        private String pagepath;

        public MiniProgramData() {
        }

        public MiniProgramData(String appid, String pagepath) {
            this.appid = appid;
            this.pagepath = pagepath;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagepath() {
            return pagepath;
        }

        public void setPagepath(String pagepath) {
            this.pagepath = pagepath;
        }
    }
}
