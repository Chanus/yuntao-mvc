/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: XMLParseTest
 * Author:   Chanus
 * Date:     2020-05-20 08:52:41
 * Description: XMLParse 测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * XMLParse 测试
 *
 * @author Chanus
 * @date 2020-05-20 08:52:41
 * @since 0.1.9
 */
public class XMLParseTest {
    @Test
    public void extractTest() {
        String xml = "<xml>\n" +
                "<Encrypt><![CDATA[qqqqq]]></Encrypt>\n" +
                "<ToUserName><![CDATA[aaaaaa]]></ToUserName>\n" +
                "<TimeStamp>1589936148698</TimeStamp>\n" +
                "<Nonce><![CDATA[1234]]></Nonce>\n" +
                "</xml>";

        Object[] arr = XMLParse.extract(xml);

        Arrays.asList(arr).forEach(System.out::println);
    }

    @Test
    public void parseXmlToMapTest() {
        String xml = "<xml><ToUserName><![CDATA[gh_f19e07ef435d]]></ToUserName><FromUserName><![CDATA[o-mAK55lxMjG-3Kd5wRtFtS__4rA]]></FromUserName><CreateTime>1589503479</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[qrscene_350524001]]></EventKey><Ticket><![CDATA[gQE-8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyREhHNHNGV3djYUQxVEJPQjF1Y1oAAgTl5b1eAwQAjScA]]></Ticket></xml>";
        Map<String, String> map = XMLParse.parseXmlToMap(xml);
        map.forEach((x, y) -> System.out.println(x + "=" + y));
    }

    @Test
    public void generateTest() {
        String xml = XMLParse.generate("qqqqq", "aaaaaa", String.valueOf(System.currentTimeMillis()), "1234");
        System.out.println(xml);
    }
}
