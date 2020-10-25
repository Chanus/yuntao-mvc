/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.commons.pojo;

import pers.chanus.yuntao.commons.constant.MsgCodeConstants;

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
     * 构造方法，初始化 {@code code}
     */
    public Message() {
        super();
        this.code = MsgCodeConstants.SUCCESS;
    }

    /**
     * 构造方法，初始化 {@code code} 和 {@code msg}
     *
     * @param code 信息代码
     * @param msg  信息内容
     */
    public Message(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    /**
     * 设置 {@code code} 和 {@code msg}
     *
     * @param code 信息代码
     * @param msg  信息内容
     * @return {@link Message}
     */
    public static Message init(int code, String msg) {
        return new Message(code, msg);
    }

    /**
     * 执行成功
     *
     * @param msg 信息内容
     * @return {@link Message}
     */
    public static Message success(String msg) {
        return new Message(MsgCodeConstants.SUCCESS, msg);
    }

    /**
     * 执行失败
     *
     * @param code 信息代码
     * @param msg  信息内容
     * @return {@link Message}
     */
    public static Message fail(int code, String msg) {
        return new Message(code, msg);
    }

    /**
     * 执行失败
     *
     * @param msg 信息内容
     * @return {@link Message}
     */
    public static Message fail(String msg) {
        return new Message(MsgCodeConstants.FAIL, msg);
    }

    /**
     * 添加成功
     *
     * @return {@link Message}
     */
    public static Message addSuccess() {
        return success(MsgCodeConstants.ADD_SUCCESS);
    }

    /**
     * 添加失败
     *
     * @return {@link Message}
     */
    public static Message addFail() {
        return fail(MsgCodeConstants.ADD_FAIL);
    }

    /**
     * 删除成功
     *
     * @return {@link Message}
     */
    public static Message deleteSuccess() {
        return success(MsgCodeConstants.DELETE_SUCCESS);
    }

    /**
     * 删除失败
     *
     * @return {@link Message}
     */
    public static Message deleteFail() {
        return fail(MsgCodeConstants.DELETE_FAIL);
    }

    /**
     * 修改成功
     *
     * @return {@link Message}
     */
    public static Message updateSuccess() {
        return success(MsgCodeConstants.UPDATE_SUCCESS);
    }

    /**
     * 修改失败
     *
     * @return {@link Message}
     */
    public static Message updateFail() {
        return fail(MsgCodeConstants.UPDATE_FAIL);
    }

    /**
     * 操作成功
     *
     * @return {@link Message}
     */
    public static Message operateSuccess() {
        return success(MsgCodeConstants.OPERATE_SUCCESS);
    }

    /**
     * 操作失败
     *
     * @return {@link Message}
     */
    public static Message operateFail() {
        return fail(MsgCodeConstants.OPERATE_FAIL);
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
