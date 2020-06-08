/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: Poi
 * Author:   Chanus
 * Date:     2020-06-08 16:21:51
 * Description: 门店信息
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 门店信息
 *
 * @author Chanus
 * @date 2020-06-08 16:21:51
 * @since 0.1.9
 */
public class Poi implements Serializable {
    private static final long serialVersionUID = 7266253475164380072L;

    /**
     * 获取门店信息 json 字符串
     *
     * @param baseInfo
     * @return
     */
    public static String toJSONString(BaseInfo baseInfo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("business", new Business(baseInfo));
        return jsonObject.toJSONString();
    }

    /**
     * 门店信息
     */
    public static class Business {
        /**
         * 门店基础信息
         */
        private BaseInfo base_info;

        public Business() {
        }

        public Business(BaseInfo base_info) {
            this.base_info = base_info;
        }

        public BaseInfo getBase_info() {
            return base_info;
        }

        public void setBase_info(BaseInfo base_info) {
            this.base_info = base_info;
        }
    }

    /**
     * 门店基础信息
     */
    public static class BaseInfo {
        /**
         * 微信门店的 poi_id
         */
        private String poi_id;
        /**
         * 商户自己的id，用于后续审核通过收到poi_id 的通知时，做对应关系。请商户自己保证唯一识别性
         */
        private String sid;
        /**
         * 门店名称（仅为商户名，如：国美、麦当劳，不应包含地区、地址、分店名等信息，错误示例：北京国美） 不能为空，15个汉字或30个英文字符内
         */
        private String business_name;
        /**
         * 分店名称（不应包含地区信息，不应与门店名有重复，错误示例：北京王府井店） 20 个字 以内
         */
        private String branch_name;
        /**
         * 门店所在的省份（直辖市填城市名,如：北京 市） 10个字 以内
         */
        private String province;
        /**
         * 门店所在的城市 10个字 以内
         */
        private String city;
        /**
         * 门店所在地区 10个字 以内
         */
        private String district;
        /**
         * 门店所在的详细街道地址（不要填写省市信息）
         */
        private String address;
        /**
         * 门店的电话（纯数字，区号、分机号均由“-”隔开）
         */
        private String telephone;
        /**
         * 门店的类型（不同级分类用“,”隔开，如：美食，川菜，火锅。详细分类参见附件：微信门店类目表）
         */
        private String[] categories;
        /**
         * 坐标类型： 1 为火星坐标 2 为sogou经纬度 3 为百度经纬度 4 为mapbar经纬度 5 为GPS坐标 6 为sogou墨卡托坐标 注：高德经纬度无需转换可直接使用
         */
        private int offset_type;
        /**
         * 门店所在地理位置的经度
         */
        private double longitude;
        /**
         * 门店所在地理位置的纬度（经纬度均为火星坐标，最好选用腾讯地图标记的坐标）
         */
        private double latitude;
        /**
         * 图片列表，url 形式，可以有多张图片，尺寸为 640*340px。必须为上一接口生成的url。 图片内容不允许与门店不相关，不允许为二维码、员工合照（或模特肖像）、营业执照、无门店正门的街景、地图截图、公交地铁站牌、菜单截图等
         */
        private List<Photo> photo_list;
        /**
         * 推荐品，餐厅可为推荐菜；酒店为推荐套房；景点为推荐游玩景点等，针对自己行业的推荐内容 200字以内
         */
        private String recommend;
        /**
         * 特色服务，如免费wifi，免费停车，送货上门等商户能提供的特色功能或服务
         */
        private String special;
        /**
         * 商户简介，主要介绍商户信息等 300字以内
         */
        private String introduction;
        /**
         * 营业时间，24 小时制表示，用“-”连接，如 8:00-20:00
         */
        private String open_time;
        /**
         * 人均价格，大于0 的整数
         */
        private Integer avg_price;

        public static BaseInfo create() {
            return new BaseInfo();
        }

        public String getPoi_id() {
            return poi_id;
        }

        public BaseInfo setPoi_id(String poi_id) {
            this.poi_id = poi_id;
            return this;
        }

        public String getSid() {
            return sid;
        }

        public BaseInfo setSid(String sid) {
            this.sid = sid;
            return this;
        }

        public String getBusiness_name() {
            return business_name;
        }

        public BaseInfo setBusiness_name(String business_name) {
            this.business_name = business_name;
            return this;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public BaseInfo setBranch_name(String branch_name) {
            this.branch_name = branch_name;
            return this;
        }

        public String getProvince() {
            return province;
        }

        public BaseInfo setProvince(String province) {
            this.province = province;
            return this;
        }

        public String getCity() {
            return city;
        }

        public BaseInfo setCity(String city) {
            this.city = city;
            return this;
        }

        public String getDistrict() {
            return district;
        }

        public BaseInfo setDistrict(String district) {
            this.district = district;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public BaseInfo setAddress(String address) {
            this.address = address;
            return this;
        }

        public String getTelephone() {
            return telephone;
        }

        public BaseInfo setTelephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public String[] getCategories() {
            return categories;
        }

        public BaseInfo setCategories(String[] categories) {
            this.categories = categories;
            return this;
        }

        public int getOffset_type() {
            return offset_type;
        }

        public BaseInfo setOffset_type(int offset_type) {
            this.offset_type = offset_type;
            return this;
        }

        public double getLongitude() {
            return longitude;
        }

        public BaseInfo setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public double getLatitude() {
            return latitude;
        }

        public BaseInfo setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public List<Photo> getPhoto_list() {
            return photo_list;
        }

        public BaseInfo setPhoto_list(List<Photo> photo_list) {
            this.photo_list = photo_list;
            return this;
        }

        public BaseInfo addPhoto(String photo_url) {
            if (this.photo_list == null)
                this.photo_list = new ArrayList<>();
            this.photo_list.add(new Photo(photo_url));
            return this;
        }

        public String getRecommend() {
            return recommend;
        }

        public BaseInfo setRecommend(String recommend) {
            this.recommend = recommend;
            return this;
        }

        public String getSpecial() {
            return special;
        }

        public BaseInfo setSpecial(String special) {
            this.special = special;
            return this;
        }

        public String getIntroduction() {
            return introduction;
        }

        public BaseInfo setIntroduction(String introduction) {
            this.introduction = introduction;
            return this;
        }

        public String getOpen_time() {
            return open_time;
        }

        public BaseInfo setOpen_time(String open_time) {
            this.open_time = open_time;
            return this;
        }

        public Integer getAvg_price() {
            return avg_price;
        }

        public BaseInfo setAvg_price(Integer avg_price) {
            this.avg_price = avg_price;
            return this;
        }
    }

    /**
     * 图片
     */
    public static class Photo {
        /**
         * 图片 url
         */
        private String photo_url;

        public Photo(String photo_url) {
            this.photo_url = photo_url;
        }

        public String getPhoto_url() {
            return photo_url;
        }

        public void setPhoto_url(String photo_url) {
            this.photo_url = photo_url;
        }
    }
}
