/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP地址工具类
 *
 * @author Chanus
 * @date 2018-09-01 01:29:47
 * @since 0.0.1
 */
public class IpUtils {
    /**
     * 获取当前网络IP地址
     *
     * @param request
     * @return IP地址
     * @since 0.0.1
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Forwarded-For");
        }

        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }

        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet != null ? inet.getHostAddress() : ipAddress;
            }
        } else {
            String[] ips = ipAddress.split(",");
            for (String ip : ips) {
                if (!("unknown".equalsIgnoreCase(ip))) {
                    ipAddress = ip;
                    break;
                }
            }
        }

        return ipAddress;
    }

    /**
     * 获取{@code ip}对应的物理地址信息
     *
     * @param ip    IP地址
     * @return {@code ip}对应的物理地址信息
     * @author Chanus
     * @date 2020-03-01 13:55:34
     * @since 0.1.5
     */
    public static IpAddress getIpPhysicalAddress(String ip) {
        String url = "http://api.pi.do/api/v1/queryip" + "?ip=" +  ip;
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIp(ip);
        try {
            String result = HttpUtils.get(url);
            JSONObject json = JSON.parseObject(result);
            int code = json.getInteger("statuscode");
            if (code == 0) {
                JSONObject data = json.getJSONObject("data");
                JSONObject ipInfo = data.getJSONObject("ipInfo");
                ipAddress.setCode(0);
                ipAddress.setCountry(ipInfo.getString("Country"));
                ipAddress.setProvince(ipInfo.getString("Province"));
                ipAddress.setCity(ipInfo.getString("City"));
                ipAddress.setIsp(ipInfo.getString("ISP"));
            } else
                ipAddress.setCode(-1);
        } catch (Exception e) {
            ipAddress.setCode(-1);
        }

        return ipAddress;
    }

    /**
     * 获取{@code ip}对应的物理地址信息，若IP为内网IP或是外网IP则对应的国家、省、市和运营商为空
     *
     * @param ip    IP地址
     * @return {@code ip}对应的物理地址信息
     * @author Chanus
     * @date 2020-03-01 15:55:14
     * @since 0.1.5
     */
    public static IpAddress getIpPhysicalAddressReal(String ip) {
        IpAddress ipAddress = getIpPhysicalAddress(ip);
        if ("0".equals(ipAddress.getCountry()))
            ipAddress.setCountry(null);
        if ("0".equals(ipAddress.getProvince()))
            ipAddress.setProvince(null);
        if ("0".equals(ipAddress.getCity()) || "内网IP".equals(ipAddress.getCity()))
            ipAddress.setCity(null);
        if ("0".equals(ipAddress.getIsp()) || "内网IP".equals(ipAddress.getIsp()))
            ipAddress.setIsp(null);

        return ipAddress;
    }

    /**
     * IP物理地址
     *
     * @author Chanus
     * @date 2020-03-01 13:47:15
     * @since 0.1.5
     */
    public static class IpAddress implements Serializable {
        private static final long serialVersionUID = -1L;

        /**
         * 状态码，0标识成功 ，非0表示失败
         */
        private int code;

        /**
         * IP地址
         */
        private String ip;

        /**
         * 国家
         */
        private String country;

        /**
         * 省
         */
        private String province;

        /**
         * 城市
         */
        private String city;

        /**
         * 运营商
         */
        private String isp;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }

        @Override
        public String toString() {
            return "IpAddress [code=" + code + ", ip=" + ip + ", country=" + country + ", province=" + province + ", city=" + city + ", isp=" + isp + "]";
        }
    }
}
