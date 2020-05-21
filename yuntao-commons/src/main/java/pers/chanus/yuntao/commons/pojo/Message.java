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

    /**
     * 添加成功
     *
     * @return {@code Message}实例
     * @since 0.1.7
     */
    public static Message addSuccess() {
        return success(MsgCode.MSG_ADD_SUCCESS);
    }

    /**
     * 添加失败
     *
     * @return {@code Message}实例
     * @since 0.1.7
     */
    public static Message addFail() {
        return fail(MsgCode.MSG_ADD_FAIL);
    }

    /**
     * 删除成功
     *
     * @return {@code Message}实例
     * @since 0.1.7
     */
    public static Message deleteSuccess() {
        return success(MsgCode.MSG_DELETE_SUCCESS);
    }

    /**
     * 删除失败
     *
     * @return {@code Message}实例
     * @since 0.1.7
     */
    public static Message deleteFail() {
        return fail(MsgCode.MSG_DELETE_FAIL);
    }

    /**
     * 修改成功
     *
     * @return {@code Message}实例
     * @since 0.1.7
     */
    public static Message updateSuccess() {
        return success(MsgCode.MSG_UPDATE_SUCCESS);
    }

    /**
     * 修改失败
     *
     * @return {@code Message}实例
     * @since 0.1.7
     */
    public static Message updateFail() {
        return fail(MsgCode.MSG_UPDATE_FAIL);
    }

    /**
     * 操作成功
     *
     * @return {@code Message}实例
     * @since 0.1.7
     */
    public static Message operateSuccess() {
        return success(MsgCode.MSG_OPERATE_SUCCESS);
    }

    /**
     * 操作失败
     *
     * @return {@code Message}实例
     * @since 0.1.7
     */
    public static Message operateFail() {
        return fail(MsgCode.MSG_OPERATE_FAIL);
    }

    public int getCode() {
        return code;
    }

    public Message setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Message setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Message setData(Object data) {
        this.data = data;
        return this;
    }

    public List<?> getDatas() {
        return datas;
    }

    public Message setDatas(List<?> datas) {
        this.datas = datas;
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Message setMap(Map<String, Object> map) {
        this.map = map;
        return this;
    }

    @Override
    public String toString() {
        return "Message [" +
                "code=" + code +
                ", msg=" + msg +
                ", data=" + data +
                ", datas=" + datas +
                ", map=" + map +
                "]";
    }
}
