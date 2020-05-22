/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MassTagMessage
 * Author:   Chanus
 * Date:     2020-05-22 21:11:29
 * Description: 按标签群发的消息
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean.mass;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * 按标签群发的消息
 *
 * @author Chanus
 * @date 2020-05-22 21:11:29
 * @since 0.1.9
 */
public class MassTagMessage implements Serializable {
    private static final long serialVersionUID = -3586688431164347067L;

    /**
     * 用于设定图文消息的接收者
     */
    private Filter filter;
    /**
     * 用于设定即将发送的图文消息
     */
    private MpNews mpnews;
    /**
     * 用于设定即将发送的文本消息
     */
    private Text text;
    /**
     * 用于设定即将发送的语音/音频消息
     */
    private Voice voice;
    /**
     * 用于设定即将发送的图片消息
     */
    private Images images;
    /**
     * 用于设定即将发送的视频消息
     */
    private MpVideo mpvideo;
    /**
     * 用于设定即将发送的卡券消息
     */
    private WxCard wxcard;
    /**
     * 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为mpvideo，卡券为wxcard
     */
    private String msgtype;
    /**
     * 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0
     */
    private Integer send_ignore_reprint;

    public MassTagMessage() {
    }

    public MassTagMessage(String msgtype) {
        this.msgtype = msgtype;
    }

    public MassTagMessage(boolean is_to_all, Integer tag_id, String msgtype) {
        this.filter = new Filter(is_to_all, tag_id);
        this.msgtype = msgtype;
    }

    public MassTagMessage(Integer tag_id, String msgtype) {
        this.filter = new Filter(tag_id);
        this.msgtype = msgtype;
    }

    public Filter getFilter() {
        return filter;
    }

    public MassTagMessage setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public MassTagMessage setFilter(Integer tag_id) {
        this.filter = new Filter(tag_id);
        return this;
    }

    public MassTagMessage setFilter(boolean is_to_all, Integer tag_id) {
        this.filter = new Filter(is_to_all, tag_id);
        return this;
    }

    public MpNews getMpnews() {
        return mpnews;
    }

    public MassTagMessage setMpnews(MpNews mpnews) {
        this.mpnews = mpnews;
        return this;
    }

    public MassTagMessage setMpnews(String media_id) {
        this.mpnews = new MpNews(media_id);
        return this;
    }

    public Text getText() {
        return text;
    }

    public MassTagMessage setText(Text text) {
        this.text = text;
        return this;
    }

    public MassTagMessage setText(String content) {
        this.text = new Text(content);
        return this;
    }

    public Voice getVoice() {
        return voice;
    }

    public MassTagMessage setVoice(Voice voice) {
        this.voice = voice;
        return this;
    }

    public MassTagMessage setVoice(String media_id) {
        this.voice = new Voice(media_id);
        return this;
    }

    public Images getImages() {
        return images;
    }

    public MassTagMessage setImages(Images images) {
        this.images = images;
        return this;
    }

    public MassTagMessage setImages(List<String> media_ids) {
        this.images = new Images(media_ids);
        return this;
    }

    public MassTagMessage setImages(List<String> media_ids, String recommend) {
        this.images = new Images(media_ids, recommend);
        return this;
    }

    public MassTagMessage setImages(List<String> media_ids, String recommend, Integer need_open_comment, Integer only_fans_can_comment) {
        this.images = new Images(media_ids, recommend, need_open_comment, only_fans_can_comment);
        return this;
    }

    public MpVideo getMpvideo() {
        return mpvideo;
    }

    public MassTagMessage setMpvideo(MpVideo mpvideo) {
        this.mpvideo = mpvideo;
        return this;
    }

    public MassTagMessage setMpvideo(String media_id) {
        this.mpvideo = new MpVideo(media_id);
        return this;
    }

    public WxCard getWxcard() {
        return wxcard;
    }

    public MassTagMessage setWxcard(WxCard wxcard) {
        this.wxcard = wxcard;
        return this;
    }

    public MassTagMessage setWxcard(String card_id) {
        this.wxcard = new WxCard(card_id);
        return this;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public MassTagMessage setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }

    public Integer getSend_ignore_reprint() {
        return send_ignore_reprint;
    }

    public MassTagMessage setSend_ignore_reprint(Integer send_ignore_reprint) {
        this.send_ignore_reprint = send_ignore_reprint;
        return this;
    }

    /**
     * 创建一个群发消息的对象实例
     *
     * @return 群发消息的对象实例
     * @since 0.1.9
     */
    public static MassTagMessage create() {
        return new MassTagMessage();
    }

    /**
     * 创建一个群发消息的对象实例
     *
     * @param msgtype 群发的消息类型
     * @return 群发消息的对象实例
     * @since 0.1.9
     */
    public static MassTagMessage create(String msgtype) {
        return new MassTagMessage(msgtype);
    }

    /**
     * 创建一个群发消息的对象实例
     *
     * @param is_to_all 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param tag_id    群发到的标签的tag_id，若is_to_all值为true，可不填写tag_id
     * @param msgtype   群发消息的对象实例
     * @return 群发消息的对象实例
     * @since 0.1.9
     */
    public static MassTagMessage create(boolean is_to_all, Integer tag_id, String msgtype) {
        return new MassTagMessage(is_to_all, tag_id, msgtype);
    }

    /**
     * 创建一个群发消息的对象实例，is_to_all 默认为false
     *
     * @param tag_id  群发到的标签的tag_id
     * @param msgtype 群发消息的对象实例
     * @return 群发消息的对象实例
     * @since 0.1.9
     */
    public static MassTagMessage create(Integer tag_id, String msgtype) {
        return new MassTagMessage(tag_id, msgtype);
    }

    /**
     * 将当前对象转化成 json 字符串
     *
     * @return json 字符串
     * @since 0.1.9
     */
    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    /**
     * 用于设定图文消息的接收者
     */
    public static class Filter {
        /**
         * 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
         */
        private boolean is_to_all;
        /**
         * 群发到的标签的tag_id，参见用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
         */
        private Integer tag_id;

        public Filter() {
            this.is_to_all = false;
        }

        public Filter(Integer tag_id) {
            this.tag_id = tag_id;
        }

        public Filter(boolean is_to_all, Integer tag_id) {
            this.is_to_all = is_to_all;
            this.tag_id = tag_id;
        }

        public boolean isIs_to_all() {
            return is_to_all;
        }

        public Filter setIs_to_all(boolean is_to_all) {
            this.is_to_all = is_to_all;
            return this;
        }

        public Integer getTag_id() {
            return tag_id;
        }

        public Filter setTag_id(Integer tag_id) {
            this.tag_id = tag_id;
            return this;
        }
    }

    /**
     * 用于设定即将发送的图文消息
     */
    public static class MpNews {
        /**
         * 用于群发的消息的media_id
         */
        private String media_id;

        public MpNews(String media_id) {
            this.media_id = media_id;
        }

        public String getMedia_id() {
            return media_id;
        }

        public MpNews setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }

    /**
     * 用于设定即将发送的文本消息
     */
    public static class Text {
        /**
         * 文本消息内容
         */
        private String content;

        public Text(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public Text setContent(String content) {
            this.content = content;
            return this;
        }
    }

    /**
     * 用于设定即将发送的语音/音频消息
     */
    public static class Voice {
        /**
         * 语音/音频素材ID
         */
        private String media_id;

        public Voice(String media_id) {
            this.media_id = media_id;
        }

        public String getMedia_id() {
            return media_id;
        }

        public Voice setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }

    /**
     * 用于设定即将发送的图片消息
     */
    public static class Images {
        /**
         * 图片素材ID
         */
        private List<String> media_ids;
        /**
         * 推荐语，不填则默认为“分享图片”
         */
        private String recommend;
        /**
         * Uint32 是否打开评论，0不打开，1打开
         */
        private Integer need_open_comment;
        /**
         * Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
         */
        private Integer only_fans_can_comment;

        public Images(List<String> media_ids) {
            this.media_ids = media_ids;
        }

        public Images(List<String> media_ids, String recommend) {
            this.media_ids = media_ids;
            this.recommend = recommend;
            this.need_open_comment = 1;
            this.only_fans_can_comment = 0;
        }

        public Images(List<String> media_ids, String recommend, Integer need_open_comment, Integer only_fans_can_comment) {
            this.media_ids = media_ids;
            this.recommend = recommend;
            this.need_open_comment = need_open_comment;
            this.only_fans_can_comment = only_fans_can_comment;
        }

        public List<String> getMedia_ids() {
            return media_ids;
        }

        public Images setMedia_ids(List<String> media_ids) {
            this.media_ids = media_ids;
            return this;
        }

        public String getRecommend() {
            return recommend;
        }

        public Images setRecommend(String recommend) {
            this.recommend = recommend;
            return this;
        }

        public Integer getNeed_open_comment() {
            return need_open_comment;
        }

        public Images setNeed_open_comment(Integer need_open_comment) {
            this.need_open_comment = need_open_comment;
            return this;
        }

        public Integer getOnly_fans_can_comment() {
            return only_fans_can_comment;
        }

        public Images setOnly_fans_can_comment(Integer only_fans_can_comment) {
            this.only_fans_can_comment = only_fans_can_comment;
            return this;
        }
    }

    /**
     * 用于设定即将发送的视频消息
     */
    public static class MpVideo {
        /**
         * 视频素材ID
         */
        private String media_id;

        public MpVideo(String media_id) {
            this.media_id = media_id;
        }

        public String getMedia_id() {
            return media_id;
        }

        public MpVideo setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }

    /**
     * 用于设定即将发送的卡券消息
     */
    public static class WxCard {
        /**
         * 卡券ID
         */
        private String card_id;

        public WxCard(String card_id) {
            this.card_id = card_id;
        }

        public String getCard_id() {
            return card_id;
        }

        public WxCard setCard_id(String card_id) {
            this.card_id = card_id;
            return this;
        }
    }
}
