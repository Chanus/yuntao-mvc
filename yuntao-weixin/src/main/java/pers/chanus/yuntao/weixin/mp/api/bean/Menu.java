/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: Menu
 * Author:   Chanus
 * Date:     2020-05-19 09:31:04
 * Description: 自定义菜单数据对象
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义菜单数据对象
 *
 * @author Chanus
 * @date 2020-05-19 09:31:04
 * @since 0.1.9
 */
public class Menu implements Serializable {
    private static final long serialVersionUID = 7806475913547490728L;

    /**
     * 一级菜单数组，个数应为1~3个
     */
    private List<MenuButton> button;
    /**
     * 菜单匹配规则
     */
    private MatchRule matchrule;

    private Menu() {
        this.button = new ArrayList<>();
    }

    public static Menu create() {
        return new Menu();
    }

    public List<MenuButton> getButton() {
        return button;
    }

    public void setButton(List<MenuButton> button) {
        this.button = button;
    }

    public Menu setButton(String type, String name, String key) {
        this.button.add(new MenuButton(type, name, key));
        return this;
    }

    public Menu setButton(String name, List<MenuButton> sub_button) {
        this.button.add(new MenuButton(name, sub_button));
        return this;
    }

    public Menu setButton(String type, String name, String key, List<MenuButton> sub_button) {
        this.button.add(new MenuButton(type, name, key, sub_button));
        return this;
    }

    public Menu setClickButton(String name, String key) {
        this.button.add(new MenuButton("click", name, key, (String) null));
        return this;
    }

    public Menu setViewButton(String name, String url) {
        this.button.add(new MenuButton("view", name, null, url));
        return this;
    }

    public MatchRule getMatchrule() {
        return matchrule;
    }

    public Menu setMatchrule(MatchRule matchrule) {
        this.matchrule = matchrule;
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

    public static class MenuButton {
        /**
         * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
         */
        private String type;
        /**
         * 菜菜单标题，不超过16个字节，子菜单不超过60个字节
         */
        private String name;
        /**
         * 菜单KEY值，用于消息接口推送，不超过128字节
         */
        private String key;
        /**
         * 网页链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url
         */
        private String url;
        /**
         * 调用新增永久素材接口返回的合法media_id
         */
        private String media_id;
        /**
         * 小程序的appid（仅认证公众号可配置）
         */
        private String appid;
        /**
         * 小程序的页面路径
         */
        private String pagepath;
        /**
         * 二级菜单数组，个数应为1~5个
         */
        private List<MenuButton> sub_button;

        private MenuButton() {
            this.sub_button = new ArrayList<>();
        }

        public static MenuButton create() {
            return new MenuButton();
        }

        public MenuButton(String type, String name, String url) {
            this.type = type;
            this.name = name;
            this.url = url;
        }

        public MenuButton(String name, List<MenuButton> sub_button) {
            this.name = name;
            this.sub_button = sub_button;
        }

        public MenuButton(String type, String name, String key, String url) {
            this.type = type;
            this.name = name;
            this.key = key;
            this.url = url;
        }

        public MenuButton(String type, String name, String key, List<MenuButton> sub_button) {
            this.type = type;
            this.name = name;
            this.key = key;
            this.sub_button = sub_button;
        }

        public String getType() {
            return type;
        }

        public MenuButton setType(String type) {
            this.type = type;
            return this;
        }

        public String getName() {
            return name;
        }

        public MenuButton setName(String name) {
            this.name = name;
            return this;
        }

        public String getKey() {
            return key;
        }

        public MenuButton setKey(String key) {
            this.key = key;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public MenuButton setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getMedia_id() {
            return media_id;
        }

        public MenuButton setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }

        public String getAppid() {
            return appid;
        }

        public MenuButton setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public String getPagepath() {
            return pagepath;
        }

        public MenuButton setPagepath(String pagepath) {
            this.pagepath = pagepath;
            return this;
        }

        public List<MenuButton> getSub_button() {
            return sub_button;
        }

        public void setSub_button(List<MenuButton> sub_button) {
            this.sub_button = sub_button;
        }

        public MenuButton setSubButton(String type, String name, String key) {
            this.sub_button.add(new MenuButton(type, name, key));
            return this;
        }

        public MenuButton setSubButton(String name, List<MenuButton> sub_button) {
            this.sub_button.add(new MenuButton(name, sub_button));
            return this;
        }

        public MenuButton setSubButton(String type, String name, String key, List<MenuButton> sub_button) {
            this.sub_button.add(new MenuButton(type, name, key, sub_button));
            return this;
        }

        public MenuButton setClickSubButton(String name, String key) {
            this.sub_button.add(new MenuButton("click", name, key));
            return this;
        }

        public MenuButton setViewSubButton(String name, String url) {
            this.sub_button.add(new MenuButton("view", name, url));
            return this;
        }
    }

    /**
     * 菜单匹配规则
     */
    public static class MatchRule {
        /**
         * 用户标签的id，可通过用户标签管理接口获取
         */
        private String tag_id;
        /**
         * 性别：男（1）女（2），不填则不做匹配
         */
        private String sex;
        /**
         * 国家信息，是用户在微信中设置的地区，具体请参考地区信息表
         */
        private String country;
        /**
         * 省份信息，是用户在微信中设置的地区，具体请参考地区信息表
         */
        private String province;
        /**
         * 城市信息，是用户在微信中设置的地区，具体请参考地区信息表
         */
        private String city;
        /**
         * 客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)，不填则不做匹配
         */
        private String client_platform_type;
        /**
         * 语言信息，是用户在微信中设置的语言，具体请参考语言表： 1、简体中文 "zh_CN" 2、繁体中文TW "zh_TW" 3、繁体中文HK "zh_HK"
         * 4、英文 "en" 5、印尼 "id" 6、马来 "ms" 7、西班牙 "es" 8、韩国 "ko" 9、意大利 "it" 10、日本 "ja" 11、波兰 "pl" 12、葡萄牙 "pt"
         * 13、俄国 "ru" 14、泰文 "th" 15、越南 "vi" 16、阿拉伯语 "ar" 17、北印度 "hi" 18、希伯来 "he" 19、土耳其 "tr" 20、德语 "de" 21、法语 "fr"
         */
        private String language;

        public MatchRule() {
            this.language = "zh_CN";
        }

        public static MatchRule create() {
            return new MatchRule();
        }

        public String getTag_id() {
            return tag_id;
        }

        public MatchRule setTag_id(String tag_id) {
            this.tag_id = tag_id;
            return this;
        }

        public String getSex() {
            return sex;
        }

        public MatchRule setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public MatchRule setCountry(String country) {
            this.country = country;
            return this;
        }

        public String getProvince() {
            return province;
        }

        public MatchRule setProvince(String province) {
            this.province = province;
            return this;
        }

        public String getCity() {
            return city;
        }

        public MatchRule setCity(String city) {
            this.city = city;
            return this;
        }

        public String getClient_platform_type() {
            return client_platform_type;
        }

        public MatchRule setClient_platform_type(String client_platform_type) {
            this.client_platform_type = client_platform_type;
            return this;
        }

        public String getLanguage() {
            return language;
        }

        public MatchRule setLanguage(String language) {
            this.language = language;
            return this;
        }
    }
}
