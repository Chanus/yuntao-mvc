/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MassTagMessage
 * Author:   Chanus
 * Date:     2020-05-22 21:11:29
 * Description: 客服发消息数据
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 客服发消息数据
 *
 * @author Chanus
 * @date 2020-05-30 16:42:11
 * @since 0.1.9
 */
public class CustomMessage implements Serializable {
    private static final long serialVersionUID = -7106463458006202574L;

    /**
     * 普通用户 openid
     */
    private String touser;
    /**
     * 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息（点击跳转到外链）为news，图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard，小程序为miniprogrampage
     */
    private String msgtype;
    /**
     * 用于设定即将发送的文本消息
     */
    private Text text;
    /**
     * 用于设定即将发送的图片消息
     */
    private Image image;
    /**
     * 用于设定即将发送的语音消息
     */
    private Voice voice;
    /**
     * 用于设定即将发送的视频消息
     */
    private Video video;
    /**
     * 用于设定即将发送的音乐消息
     */
    private Music music;
    /**
     * 用于设定即将发送的图文消息（点击跳转到外链），图文消息条数限制在1条以内，注意，如果图文数超过1，则将会返回错误码45008
     */
    private News news;
    /**
     * 用于设定即将发送的图文消息（点击跳转到图文消息页面），图文消息条数限制在1条以内，注意，如果图文数超过1，则将会返回错误码45008
     */
    private MpNews mpnews;
    /**
     * 用于设定即将发送的卡券消息
     */
    private WxCard wxcard;
    /**
     * 用于设定即将发送的菜单消息
     */
    private MsgMenu msgmenu;
    /**
     * 用于设定即将发送的小程序卡片
     */
    private MiniProgramPage miniprogrampage;
    /**
     * 发消息的客服账号
     */
    private CustomService customservice;

    public CustomMessage() {
    }

    public CustomMessage(String touser) {
        this.touser = touser;
    }

    public CustomMessage(String touser, String kf_account) {
        this.touser = touser;
        this.customservice = new CustomService(kf_account);
    }

    public String getTouser() {
        return touser;
    }

    public CustomMessage setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public CustomMessage setMsgtype(String msgtype) {
        this.msgtype = msgtype;
        return this;
    }

    public Text getText() {
        return text;
    }

    public CustomMessage setText(Text text) {
        this.msgtype = "text";
        this.text = text;
        return this;
    }

    public CustomMessage setText(String content) {
        this.msgtype = "text";
        this.text = new Text(content);
        return this;
    }

    public Image getImage() {
        return image;
    }

    public CustomMessage setImage(Image image) {
        this.msgtype = "image";
        this.image = image;
        return this;
    }

    public CustomMessage setImage(String media_id) {
        this.msgtype = "image";
        this.image = new Image(media_id);
        return this;
    }

    public Voice getVoice() {
        return voice;
    }

    public CustomMessage setVoice(Voice voice) {
        this.msgtype = "voice";
        this.voice = voice;
        return this;
    }

    public CustomMessage setVoice(String media_id) {
        this.msgtype = "voice";
        this.voice = new Voice(media_id);
        return this;
    }

    public Video getVideo() {
        return video;
    }

    public CustomMessage setVideo(Video video) {
        this.msgtype = "video";
        this.video = video;
        return this;
    }

    public CustomMessage setVideo(String media_id, String thumb_media_id, String title, String description) {
        this.msgtype = "video";
        this.video = new Video(media_id, thumb_media_id, title, description);
        return this;
    }

    public Music getMusic() {
        return music;
    }

    public CustomMessage setMusic(Music music) {
        this.msgtype = "music";
        this.music = music;
        return this;
    }

    public CustomMessage setMusic(String title, String description, String musicurl, String hqmusicurl, String thumb_media_id) {
        this.msgtype = "music";
        this.music = new Music(title, description, musicurl, hqmusicurl, thumb_media_id);
        return this;
    }

    public News getNews() {
        return news;
    }

    public CustomMessage setNews(News news) {
        this.msgtype = "news";
        this.news = news;
        return this;
    }

    public CustomMessage setNews(List<Articles> articles) {
        this.msgtype = "news";
        this.news = new News(articles);
        return this;
    }

    public MpNews getMpnews() {
        return mpnews;
    }

    public CustomMessage setMpnews(MpNews mpnews) {
        this.msgtype = "mpnews";
        this.mpnews = mpnews;
        return this;
    }

    public CustomMessage setMpnews(String media_id) {
        this.msgtype = "mpnews";
        this.mpnews = new MpNews(media_id);
        return this;
    }

    public WxCard getWxcard() {
        return wxcard;
    }

    public CustomMessage setWxcard(WxCard wxcard) {
        this.msgtype = "wxcard";
        this.wxcard = wxcard;
        return this;
    }

    public CustomMessage setWxcard(String card_id, String card_ext) {
        this.msgtype = "wxcard";
        this.wxcard = new WxCard(card_id, card_ext);
        return this;
    }

    public MsgMenu getMsgmenu() {
        return msgmenu;
    }

    public CustomMessage setMsgmenu(MsgMenu msgmenu) {
        this.msgtype = "msgmenu";
        this.msgmenu = msgmenu;
        return this;
    }

    public MiniProgramPage getMiniprogrampage() {
        return miniprogrampage;
    }

    public CustomMessage setMiniprogrampage(MiniProgramPage miniprogrampage) {
        this.msgtype = "miniprogrampage";
        this.miniprogrampage = miniprogrampage;
        return this;
    }

    public CustomMessage setMiniprogrampage(String title, String appid, String pagepath, String thumb_media_id) {
        this.msgtype = "miniprogrampage";
        this.miniprogrampage = new MiniProgramPage(title, appid, pagepath, thumb_media_id);
        return this;
    }

    public CustomService getCustomservice() {
        return customservice;
    }

    public CustomMessage setCustomservice(CustomService customservice) {
        this.customservice = customservice;
        return this;
    }

    public CustomMessage setCustomservice(String kf_account) {
        this.customservice = new CustomService(kf_account);
        return this;
    }

    /**
     * 创建一个 CustomMessage 的对象实例
     *
     * @return CustomMessage 的对象实例
     * @since 0.1.9
     */
    public static CustomMessage create() {
        return new CustomMessage();
    }

    /**
     * 创建一个 CustomMessage 的对象实例
     *
     * @param touser 消息的接收者，普通用户 openid
     * @return CustomMessage 的对象实例
     * @since 0.1.9
     */
    public static CustomMessage create(String touser) {
        return new CustomMessage(touser);
    }

    /**
     * 创建一个 CustomMessage 的对象实例
     *
     * @param touser     消息的接收者，普通用户 openid
     * @param kf_account 发消息的客服账号
     * @return CustomMessage 的对象实例
     * @since 0.1.9
     */
    public static CustomMessage create(String touser, String kf_account) {
        return new CustomMessage(touser, kf_account);
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
     * 用于设定即将发送的语音消息
     */
    public static class Voice {
        /**
         * 语音素材ID
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
     * 用于设定即将发送的视频消息
     */
    public static class Video {
        /**
         * 视频素材ID
         */
        private String media_id;
        /**
         * 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
         */
        private String thumb_media_id;
        /**
         * 消息的标题
         */
        private String title;
        /**
         * 消息的描述
         */
        private String description;

        public Video() {
        }

        public Video(String media_id, String thumb_media_id, String title, String description) {
            this.media_id = media_id;
            this.thumb_media_id = thumb_media_id;
            this.title = title;
            this.description = description;
        }

        public String getMedia_id() {
            return media_id;
        }

        public Video setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }

        public String getThumb_media_id() {
            return thumb_media_id;
        }

        public Video setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Video setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Video setDescription(String description) {
            this.description = description;
            return this;
        }
    }

    /**
     * 用于设定即将发送的音乐消息
     */
    public static class Music {
        /**
         * 消息的标题
         */
        private String title;
        /**
         * 消息的描述
         */
        private String description;
        /**
         * 音乐链接
         */
        private String musicurl;
        /**
         * 高品质音乐链接，wifi环境优先使用该链接播放音乐
         */
        private String hqmusicurl;
        /**
         * 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
         */
        private String thumb_media_id;

        public Music() {
        }

        public Music(String title, String description, String musicurl, String hqmusicurl, String thumb_media_id) {
            this.title = title;
            this.description = description;
            this.musicurl = musicurl;
            this.hqmusicurl = hqmusicurl;
            this.thumb_media_id = thumb_media_id;
        }

        public String getTitle() {
            return title;
        }

        public Music setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Music setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getMusicurl() {
            return musicurl;
        }

        public Music setMusicurl(String musicurl) {
            this.musicurl = musicurl;
            return this;
        }

        public String getHqmusicurl() {
            return hqmusicurl;
        }

        public Music setHqmusicurl(String hqmusicurl) {
            this.hqmusicurl = hqmusicurl;
            return this;
        }

        public String getThumb_media_id() {
            return thumb_media_id;
        }

        public Music setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
            return this;
        }
    }

    /**
     * 用于设定即将发送的图文消息（点击跳转到外链）
     */
    public static class News {
        /**
         * 用于群发的消息的media_id
         */
        private List<Articles> articles;

        public News() {
            this.articles = new ArrayList<>();
        }

        public News(List<Articles> articles) {
            this.articles = articles;
        }

        public static News create() {
            return new News();
        }

        public List<Articles> getArticles() {
            return articles;
        }

        public News setArticles(List<Articles> articles) {
            this.articles = articles;
            return this;
        }

        public News addArticles(String title, String description, String url, String picurl) {
            this.articles.add(new Articles(title, description, url, picurl));
            return this;
        }
    }

    /**
     * 用于设定即将发送的图文消息（点击跳转到图文消息页面）
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

    public static class Articles {
        /**
         * 图文消息标题
         */
        private String title;
        /**
         * 图文消息表述
         */
        private String description;
        /**
         * 图文消息被点击后跳转的链接
         */
        private String url;
        /**
         * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
         */
        private String picurl;

        public Articles() {
        }

        public Articles(String title, String description, String url, String picurl) {
            this.title = title;
            this.description = description;
            this.url = url;
            this.picurl = picurl;
        }

        public String getTitle() {
            return title;
        }

        public Articles setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Articles setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Articles setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getPicurl() {
            return picurl;
        }

        public Articles setPicurl(String picurl) {
            this.picurl = picurl;
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
         * 本身是一个JSON字符串，是商户为该张卡券分配的唯一性信息<br>
         * 详情请见：http://caibaojian.com/wxwiki/0030551f015f01ecaa56d20b88ee3c6cb32503bf.html#.E9.99.84.E5.BD.954-.E5.8D.A1.E5.88.B8.E6.89.A9.E5.B1.95.E5.AD.97.E6.AE.B5.E5.8F.8A.E7.AD.BE.E5.90.8D.E7.94.9F.E6.88.90.E7.AE.97.E6.B3.95
         */
        private String card_ext;

        public WxCard() {
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

    /**
     * 用于设定即将发送的菜单消息
     */
    public static class MsgMenu {
        /**
         * 菜单消息的头部内容
         */
        private String head_content;
        /**
         * 菜单消息的菜单列表
         */
        private List<MsgMenuItem> list;
        /**
         * 菜单消息的底部内容
         */
        private String tail_content;

        public MsgMenu() {
            this.list = new ArrayList<>();
        }

        public MsgMenu(String head_content, String tail_content) {
            this.head_content = head_content;
            this.list = new ArrayList<>();
            this.tail_content = tail_content;
        }

        public static MsgMenu create() {
            return new MsgMenu();
        }

        public static MsgMenu create(String head_content, String tail_content) {
            return new MsgMenu(head_content, tail_content);
        }

        public String getHead_content() {
            return head_content;
        }

        public MsgMenu setHead_content(String head_content) {
            this.head_content = head_content;
            return this;
        }

        public List<MsgMenuItem> getList() {
            return list;
        }

        public MsgMenu setList(List<MsgMenuItem> list) {
            this.list = list;
            return this;
        }

        public String getTail_content() {
            return tail_content;
        }

        public MsgMenu setTail_content(String tail_content) {
            this.tail_content = tail_content;
            return this;
        }

        public MsgMenu addList(String id, String content) {
            this.list.add(new MsgMenuItem(id, content));
            return this;
        }
    }

    /**
     * 菜单消息的菜单列表
     */
    public static class MsgMenuItem {
        /**
         * 菜单消息的菜单ID
         */
        private String id;
        /**
         * 菜单消息的菜单内容
         */
        private String content;

        public MsgMenuItem() {
        }

        public MsgMenuItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public MsgMenuItem setId(String id) {
            this.id = id;
            return this;
        }

        public String getContent() {
            return content;
        }

        public MsgMenuItem setContent(String content) {
            this.content = content;
            return this;
        }
    }

    /**
     * 用于设定即将发送的小程序卡片
     */
    public static class MiniProgramPage {
        /**
         * 小程序卡片的标题
         */
        private String title;
        /**
         * 小程序的appid，要求小程序的appid需要与公众号有关联关系
         */
        private String appid;
        /**
         * 小程序的页面路径，跟app.json对齐，支持参数，比如pages/index/index?foo=bar
         */
        private String pagepath;
        /**
         * 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
         */
        private String thumb_media_id;

        public MiniProgramPage() {
        }

        public MiniProgramPage(String title, String appid, String pagepath, String thumb_media_id) {
            this.title = title;
            this.appid = appid;
            this.pagepath = pagepath;
            this.thumb_media_id = thumb_media_id;
        }

        public String getTitle() {
            return title;
        }

        public MiniProgramPage setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getAppid() {
            return appid;
        }

        public MiniProgramPage setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public String getPagepath() {
            return pagepath;
        }

        public MiniProgramPage setPagepath(String pagepath) {
            this.pagepath = pagepath;
            return this;
        }

        public String getThumb_media_id() {
            return thumb_media_id;
        }

        public MiniProgramPage setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
            return this;
        }
    }

    /**
     * 发消息的客服账号
     */
    public static class CustomService {
        private String kf_account;

        public CustomService() {
        }

        public CustomService(String kf_account) {
            this.kf_account = kf_account;
        }

        public String getKf_account() {
            return kf_account;
        }

        public CustomService setKf_account(String kf_account) {
            this.kf_account = kf_account;
            return this;
        }
    }
}
