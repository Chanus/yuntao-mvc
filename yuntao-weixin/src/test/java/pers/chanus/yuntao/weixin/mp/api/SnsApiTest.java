/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: SnsApiTest
 * Author:   Chanus
 * Date:     2020-05-19 14:42:41
 * Description: SnsApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import pers.chanus.yuntao.weixin.mp.api.bean.SnsAccessToken;

/**
 * SnsApi 测试
 *
 * @author Chanus
 * @date 2020-05-19 14:42:41
 * @since 0.1.9
 */
public class SnsApiTest extends ApiConfigTest {
    @Test
    public void getAuthorizeURLTest() {
        String redirectUri = "http://t.liulianhuan.com/register/oauth2";
        String url = SnsApi.getAuthorizeURL(redirectUri, true);
        System.out.println(url);
        String state = "test";
        url = SnsApi.getAuthorizeURL(redirectUri, false, state);
        System.out.println(url);
    }

    @Test
    public void getQrConnectURLTest() {
        String redirectUri = "http://t.liulianhuan.com/register/oauth2";
        String url = SnsApi.getQrConnectURL(redirectUri);
        System.out.println(url);
        String state = "test";
        url = SnsApi.getQrConnectURL(redirectUri, state);
        System.out.println(url);
    }

    @Test
    public void getSnsAccessTokenTest() {
        String code = "001CH9Bz1TEcJc0tiDAz1RX8Bz1CH9BG";
        SnsAccessToken snsAccessToken = SnsApi.getSnsAccessToken(code);
        System.out.println(snsAccessToken.toString());
    }

    @Test
    public void getUserOpenIdTest() {
        String code = "001CH9Bz1TEcJc0tiDAz1RX8Bz1CH9BG";
        String openId = SnsApi.getUserOpenId(code);
        System.out.println(openId);
    }

    @Test
    public void refreshSnsAccessTokenTest() {
        String refreshToken = "33_9CQql90EruOkAoMeFZmh7jz7EJsQV0u6LSd75jeU1XL4ik7uP-RZkKHObZC1u8z4ysKUx2TBoWGxsQwa-LXFOhrVhGuLZPTicDicbJJWyT8";
        SnsAccessToken snsAccessToken = SnsApi.refreshSnsAccessToken(refreshToken);
        System.out.println(snsAccessToken.toString());
    }

    @Test
    public void getUserInfoTest() {
        String accessToken = "33_Cog0JM9pFMfzIetwrQJlDkJcKnXPQlDfk2eoMxZDoze5FREB45EGp8THp_sFj3l-a9dEfXbhU1FA5jgySCcKPNmno4crqSmCLZlEDTj7SVM";
        String openId = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = SnsApi.getUserInfo(accessToken, openId);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void authSnsAccessTokenTest() {
        String accessToken = "33_Cog0JM9pFMfzIetwrQJlDkJcKnXPQlDfk2eoMxZDoze5FREB45EGp8THp_sFj3l-a9dEfXbhU1FA5jgySCcKPNmno4crqSmCLZlEDTj7SVM";
        String openId = "o9Q3g0c-95M7vLPzPY4iUfLyCLVs";
        JSONObject jsonObject = SnsApi.authSnsAccessToken(accessToken, openId);
        System.out.println(jsonObject.toJSONString());
    }
}
