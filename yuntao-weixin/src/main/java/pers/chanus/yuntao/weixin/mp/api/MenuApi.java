/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MenuApi
 * Author:   Chanus
 * Date:     2020-05-19 09:04:28
 * Description: 自定义菜单 API
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.chanus.yuntao.util.HttpUtils;

/**
 * 自定义菜单 API<br>
 * 详情请见：
 * <pre>
 *     创建接口：https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Creating_Custom-Defined_Menu.html
 *     查询接口：https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Querying_Custom_Menus.html
 *     删除接口：https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Deleting_Custom-Defined_Menu.html
 *     事件推送：https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Custom_Menu_Push_Events.html
 *     个性化菜单接口：https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Personalized_menu_interface.html
 *     获取自定义菜单配置：https://developers.weixin.qq.com/doc/offiaccount/Custom_Menus/Getting_Custom_Menu_Configurations.html
 * </pre>
 *
 * @author Chanus
 * @date 2020-05-19 09:04:28
 * @since 0.1.9
 */
public class MenuApi {
    /**
     * 创建自定义菜单 url，请求方式为 POST
     */
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 获取自定义菜单配置 url，请求方式为 GET
     */
    private static final String GET_SELFMENU_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
    /**
     * 获取菜单配置 url，请求方式为 GET
     */
    private static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    /**
     * 删除菜单接口 url，请求方式为 GET
     */
    private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    /**
     * 创建个性化菜单 url，请求方式为 POST
     */
    private static final String ADD_CONDITIONAL_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
    /**
     * 删除个性化菜单 url，请求方式为 POST
     */
    private static final String DEL_CONDITIONAL_URL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";
    /**
     * 测试个性化菜单匹配结果 url，请求方式为 POST
     */
    private static final String TRY_MATCH_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN";


    /**
     * 创建自定义菜单
     *
     * @param json 自定义菜单数据的 json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject createMenu(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 获取自定义菜单配置
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getCurrentSelfMenuInfo() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_SELFMENU_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 获取菜单配置
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject getMenuInfo() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = GET_MENU_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 删除菜单
     *
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject deleteMenu() {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.get(url);

        return JSON.parseObject(result);
    }

    /**
     * 创建个性化菜单
     *
     * @param json 个性化菜单数据的 json 字符串
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject addConditional(String json) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = ADD_CONDITIONAL_URL.replace("ACCESS_TOKEN", accessToken);

        String result = HttpUtils.post(url, json);

        return JSON.parseObject(result);
    }

    /**
     * 删除个性化菜单
     *
     * @param menuId menuid为菜单id，可以通过自定义菜单查询接口获取
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject delConditional(String menuId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = DEL_CONDITIONAL_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("menuid", menuId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

    /**
     * 测试个性化菜单匹配结果
     *
     * @param userId user_id可以是粉丝的OpenID，也可以是粉丝的微信号
     * @return 请求结果的 json 对象
     * @since 0.1.9
     */
    public static JSONObject tryMatch(String userId) {
        String accessToken = AccessTokenApi.getAccessTokenStr();
        String url = TRY_MATCH_MENU_URL.replace("ACCESS_TOKEN", accessToken);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);

        String result = HttpUtils.post(url, jsonObject.toJSONString());

        return JSON.parseObject(result);
    }

}
