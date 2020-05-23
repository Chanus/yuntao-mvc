/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MassTagMessage
 * Author:   Chanus
 * Date:     2020-05-22 21:11:29
 * Description: 群发消息预览接口数据
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 群发消息预览接口数据
 *
 * @author Chanus
 * @date 2020-05-22 21:11:29
 * @since 0.1.9
 */
public class MassPreviewMessage implements Serializable {
    private static final long serialVersionUID = -3586688431164347067L;

    /**
     * 接收消息用户对应该公众号的openid
     */
    private String touser;
    /**
     * 接收消息用户对应该公众号的openid，以实现对微信号的预览
     */
    private String towxname;
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
    private Image image;
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

    public MassPreviewMessage() {
    }

    public MassPreviewMessage(String touser) {
        this.touser = touser;
    }

    public String getTouser() {
        return touser;
    }

    public MassPreviewMessage setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public String getTowxname() {
        return towxname;
    }

    public MassPreviewMessage setTowxname(String towxname) {
        this.towxname = towxname;
        return this;
    }

    public MpNews getMpnews() {
        return mpnews;
    }

    public MassPreviewMessage setMpnews(MpNews mpnews) {
        this.mpnews = mpnews;
        this.msgtype = "mpnews";
        return this;
    }

    public MassPreviewMessage setMpnews(String media_id) {
        this.mpnews = new MpNews(media_id);
        this.msgtype = "mpnews";
        return this;
    }

    public Text getText() {
        return text;
    }

    public MassPreviewMessage setText(Text text) {
        this.text = text;
        this.msgtype = "text";
        return this;
    }

    public MassPreviewMessage setText(String content) {
        this.text = new Text(content);
        this.msgtype = "text";
        return this;
    }

    public Voice getVoice() {
        return voice;
    }

    public MassPreviewMessage setVoice(Voice voice) {
        this.voice = voice;
        this.msgtype = "voice";
        return this;
    }

    public MassPreviewMessage setVoice(String media_id) {
        this.voice = new Voice(media_id);
        this.msgtype = "voice";
        return this;
    }

    public Image getImage() {
        return image;
    }

    public MassPreviewMessage setImage(Image image) {
        this.image = image;
        this.msgtype = "image";
        return this;
    }

    public MassPreviewMessage setImage(String media_id) {
        this.image = new Image(media_id);
        this.msgtype = "image";
        return this;
    }

    public MpVideo getMpvideo() {
        return mpvideo;
    }

    public MassPreviewMessage setMpvideo(MpVideo mpvideo) {
        this.mpvideo = mpvideo;
        this.msgtype = "mpvideo";
        return this;
    }

    public MassPreviewMessage setMpvideo(String media_id) {
        this.mpvideo = new MpVideo(media_id);
        this.msgtype = "mpvideo";
        return this;
    }

    public WxCard getWxcard() {
        return wxcard;
    }

    public MassPreviewMessage setWxcard(WxCard wxcard) {
        this.wxcard = wxcard;
        this.msgtype = "wxcard";
        return this;
    }

    public MassPreviewMessage setWxcard(String card_id, String card_ext) {
        this.wxcard = new WxCard(card_id, card_ext);
        this.msgtype = "wxcard";
        return this;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public MassPreviewMessage setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }

    /**
     * 创建一个群发预览消息的对象实例
     *
     * @return 群发预览消息的对象实例
     * @since 0.1.9
     */
    public static MassPreviewMessage create() {
        return new MassPreviewMessage();
    }

    /**
     * 创建一个群发预览消息的对象实例
     *
     * @param touser  接收消息用户对应该公众号的openid
     * @return 群发预览消息的对象实例
     * @since 0.1.9
     */
    public static MassPreviewMessage create(String touser) {
        return new MassPreviewMessage(touser);
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
     * 用于设定即将发送的图文消息
     */
    public static class MpNews {
        /**
         * 用于群发的消息的media_id
         */
        private String media_id;

        public MpNews() {
        }

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

        public Text() {
        }

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

        public Voice() {
        }

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
    public static class Image {
        /**
         * 图片素材ID
         */
        private String media_id;

        public Image() {
        }

        public Image(String media_id) {
            this.media_id = media_id;
        }

        public String getMedia_id() {
            return media_id;
        }

        public Image setMedia_id(String media_id) {
            this.media_id = media_id;
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

        public MpVideo() {
        }

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
        /**
         * 可扩展的卡券的附加信息，用于投放卡券是附带卡券基本信息
         */
        private String card_ext;

        public WxCard() {
        }

        public WxCard(String card_id) {
            this.card_id = card_id;
        }

        public WxCard(String card_id, String card_ext) {
            this.card_id = card_id;
            this.card_ext = card_ext;
        }

        public String getCard_id() {
            return card_id;
        }

        public WxCard setCard_id(String card_id) {
            this.card_id = card_id;
            return this;
        }

        public String getCard_ext() {
            return card_ext;
        }

        public WxCard setCard_ext(String card_ext) {
            this.card_ext = card_ext;
            return this;
        }
    }
}
