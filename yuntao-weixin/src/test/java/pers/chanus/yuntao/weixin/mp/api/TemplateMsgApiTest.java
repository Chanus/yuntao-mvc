/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: TemplateMsgApiTest
 * Author:   Chanus
 * Date:     2020-05-18 14:47:15
 * Description: TemplateMsgApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import com.chanus.yuntao.utils.core.DateUtils;
import pers.chanus.yuntao.weixin.mp.api.bean.TemplateMessage;

/**
 * TemplateMsgApi 测试
 *
 * @author Chanus
 * @date 2020-05-18 14:47:15
 * @since 0.1.9
 */
public class TemplateMsgApiTest extends ApiConfigTest {
    @Test
    public void setIndustryTest() {
        JSONObject jsonObject = TemplateMsgApi.setIndustry("1", "17");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getIndustryTest() {
        JSONObject jsonObject = TemplateMsgApi.getIndustry();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getTemplateIdTest() {
        JSONObject jsonObject = TemplateMsgApi.getTemplateId("TM00303");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void getAllTemplateTest() {
        JSONObject jsonObject = TemplateMsgApi.getAllTemplate();
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void delTemplateTest() {
        JSONObject jsonObject = TemplateMsgApi.delTemplate("uYne7qJpCvEbyQCJkZ9wz9axFo8C9bsXs1HgY0bjROU");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void sendTest() {
        String json = TemplateMessage.create().setTouser("o9Q3g0c-95M7vLPzPY4iUfLyCLVs")
                .setTemplate_id("6SihCiqY8XBgK-OUto2MHnA4c8ump7kT55rd-PHyEzo")
                .setUrl("http://t.liulianhuan.com/teacher/register?schoolCode=350524001")
                .setData("first", "老师注册")
                .setData("keyword1", "安溪一中")
                .setData("keyword2", DateUtils.nowDateTime())
                .setData("remark", "请点击注册，感谢您的支持。", "#FF0000")
                .toJSONString();

        JSONObject jsonObject = TemplateMsgApi.send(json);
        System.out.println(jsonObject.toJSONString());
    }
}
