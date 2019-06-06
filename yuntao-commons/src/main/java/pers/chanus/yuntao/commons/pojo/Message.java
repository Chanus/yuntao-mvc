/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.commons.pojo;

import pers.chanus.yuntao.commons.constant.MsgCode;

import java.util.List;
import java.util.Map;

/**
 * 返回信息
 *
 * @author Chanus
 * @date 2018-08-31 17:00:51
 * @since 0.0.1
 */
public class Message {
    /**
     * 信息代码
     */
    private int code;

    /**
     * 信息内容
     */
    private String msg;

    /**
     * 数据对象
     */
    private Object data;

    /**
     * 数据列表
     */
    private List<?> datas;

    /**
     * Map集合数据
     */
    private Map<String, Object> map;

    /**
     * 构造方法，初始化{@code code}
     *
     * @since 0.0.1
     */
    public Message() {
        super();
        this.code = MsgCode.SUCCESS;
    }

    /**
     * 构造方法，初始化{@code code}和{@code msg}
     *
     * @param code 信息代码
     * @param msg  信息内容
     * @since 0.0.1
     */
    public Message(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    /**
     * 设置{@code code}和{@code msg}
     *
     * @param code 信息代码
     * @param msg  信息内容
     * @since 0.0.1
     */
    public void init(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 设置{@code code}和{@code msg}
     *
     * @param code 信息代码
     * @param msg  信息内容
     * @return {@code Message}实例
     * @since 0.0.1
     */
    public static Message initMsg(int code, String msg) {
        return new Message(code, msg);
    }

    /**
     * 设置{@code data}
     *
     * @param data 数据对象
     * @return 当前{@code Message}实例
     * @since 0.0.1
     */
    public Message initMsg(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 设置{@code datas}
     *
     * @param datas 数据列表
     * @return 当前{@code Message}实例
     * @since 0.0.1
     */
    public Message initMsg(List<?> datas) {
        this.datas = datas;
        return this;
    }

    /**
     * 设置{@code map}
     *
     * @param map Map集合数据
     * @return 当前{@code Message}实例
     * @since 0.0.1
     */
    public Message initMsg(Map<String, Object> map) {
        this.map = map;
        return this;
    }

    /**
     * 操作成功
     *
     * @param msg 信息内容
     * @return {@code Message}实例
     * @since 0.0.1
     */
    public static Message success(String msg) {
        return new Message(MsgCode.SUCCESS, msg);
    }

    /**
     * 操作失败
     *
     * @param code 信息代码
     * @param msg  信息内容
     * @return {@code Message}实例
     * @since 0.0.1
     */
    public static Message fail(int code, String msg) {
        return new Message(code, msg);
    }

    /**
     * 操作失败
     *
     * @param msg 信息内容
     * @return {@code Message}实例
     * @since 0.0.1
     */
    public static Message fail(String msg) {
        return new Message(MsgCode.FAIL, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<?> getDatas() {
        return datas;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Message [code=" + code + ", msg=" + msg + ", data=" + data + ", datas=" + datas + ", map=" + map + "]";
    }
}
