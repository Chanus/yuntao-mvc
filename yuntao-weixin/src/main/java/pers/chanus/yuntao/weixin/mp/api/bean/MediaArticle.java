/*
 * Copyright (c), 2020-persent, Chanus and/or its affiliates. All rights reserved.
 * FileName: MediaArticle
 * Author:   Chanus
 * Date:     2020-06-07 12:21:39
 * Description: 永久图文素材数据对象
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.weixin.mp.api.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 永久图文素材数据对象
 *
 * @author Chanus
 * @date 2020-06-07 12:21:39
 * @since 0.1.9
 */
public class MediaArticle implements Serializable {
    private static final long serialVersionUID = -7320261626453900607L;

    private List<Article> articles;

    public MediaArticle() {
        this.articles = new ArrayList<>();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public MediaArticle addArticle(Article article) {
        this.articles.add(article);
        return this;
    }

    /**
     * 创建一个永久图文素材数据对象实例
     *
     * @return 永久图文素材数据对象实例
     * @since 0.1.9
     */
    public static MediaArticle create() {
        return new MediaArticle();
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
     * 永久图文素材数据对象
     */
    public static class Article {
        /**
         * 标题
         */
        private String title;
        /**
         * 图文消息的封面图片素材id（必须是永久mediaID）
         */
        private String thumb_media_id;
        /**
         * 作者
         */
        private String author;
        /**
         * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字
         */
        private String digest;
        /**
         * 是否显示封面，0为false，即不显示，1为true，即显示
         */
        private Integer show_cover_pic;
        /**
         * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS，涉及图片url必须来源"上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤
         */
        private String content;
        /**
         * 图文消息的原文地址，即点击“阅读原文”后的URL
         */
        private String content_source_url;
        /**
         * Uint32 是否打开评论，0不打开，1打开
         */
        private Integer need_open_comment;
        /**
         * Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
         */
        private Integer only_fans_can_comment;

        /**
         * 创建一个永久图文素材数据对象实例
         *
         * @return 永久图文素材数据对象实例
         * @since 0.1.9
         */
        public static Article create() {
            return new Article();
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

        public String getTitle() {
            return title;
        }

        public Article setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getThumb_media_id() {
            return thumb_media_id;
        }

        public Article setThumb_media_id(String thumb_media_id) {
            this.thumb_media_id = thumb_media_id;
            return this;
        }

        public String getAuthor() {
            return author;
        }

        public Article setAuthor(String author) {
            this.author = author;
            return this;
        }

        public String getDigest() {
            return digest;
        }

        public Article setDigest(String digest) {
            this.digest = digest;
            return this;
        }

        public Integer getShow_cover_pic() {
            return show_cover_pic;
        }

        public Article setShow_cover_pic(Integer show_cover_pic) {
            this.show_cover_pic = show_cover_pic;
            return this;
        }

        public String getContent() {
            return content;
        }

        public Article setContent(String content) {
            this.content = content;
            return this;
        }

        public String getContent_source_url() {
            return content_source_url;
        }

        public Article setContent_source_url(String content_source_url) {
            this.content_source_url = content_source_url;
            return this;
        }

        public Integer getNeed_open_comment() {
            return need_open_comment;
        }

        public Article setNeed_open_comment(Integer need_open_comment) {
            this.need_open_comment = need_open_comment;
            return this;
        }

        public Integer getOnly_fans_can_comment() {
            return only_fans_can_comment;
        }

        public Article setOnly_fans_can_comment(Integer only_fans_can_comment) {
            this.only_fans_can_comment = only_fans_can_comment;
            return this;
        }
    }
}
