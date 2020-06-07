/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: NewsCommentApiTest
 * Author:   Chanus
 * Date:     2020-06-07 17:00:00
 * Description: NewsCommentApi 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * NewsCommentApi 测试
 *
 * @author Chanus
 * @date 2020-06-07 17:15:12
 * @since 0.1.9
 */
public class NewsCommentApiTest extends ApiConfigTest {
    @Test
    public void openTest() {
        JSONObject jsonObject = NewsCommentApi.open(2247483669L, null);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void closeTest() {
        JSONObject jsonObject = NewsCommentApi.close(2247483669L, null);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void listTest() {
        JSONObject jsonObject = NewsCommentApi.list(2247483669L, null, 0, 20, 0);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void markElectTest() {
        JSONObject jsonObject = NewsCommentApi.markElect(2247483669L, null, 1);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void unmarkElectTest() {
        JSONObject jsonObject = NewsCommentApi.unmarkElect(2247483669L, null, 1);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteTest() {
        JSONObject jsonObject = NewsCommentApi.delete(2247483669L, null, 1);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void replyTest() {
        JSONObject jsonObject = NewsCommentApi.reply(2247483669L, null, 1, "很好");
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void deleteReplyTest() {
        JSONObject jsonObject = NewsCommentApi.deleteReply(2247483669L, null, 1);
        System.out.println(jsonObject.toJSONString());
    }
}
