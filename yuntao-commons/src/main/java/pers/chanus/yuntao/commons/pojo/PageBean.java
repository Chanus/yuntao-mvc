/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.commons.pojo;

import java.util.List;

/**
 * 分页查询返回数据
 *
 * @author Chanus
 * @date 2018-08-31 17:01:15
 * @since 0.0.1
 */
public class PageBean {
    /**
     * 默认分页查询时每页显示的数据条数
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 信息代码
     */
    private int code;

    /**
     * 信息内容
     */
    private String msg;

    /**
     * 记录条数
     */
    private int count;

    /**
     * 记录对象
     */
    private Object object;

    /**
     * 记录列表
     */
    private List<?> data;

    /**
     * 记录统计行
     *
     * @since 0.1.5
     */
    private Object totalRow;

    /**
     * 构造方法，初始化{@code code}和{@code count}
     *
     * @since 0.0.1
     */
    public PageBean() {
        super();
        this.code = 0;
        this.count = 0;
    }

    /**
     * 构造方法，初始化{@code code}、{@code count}和{@code data}
     *
     * @param count 记录条数
     * @param data  记录列表
     * @since 0.0.1
     */
    public PageBean(Integer count, List<?> data) {
        super();
        this.code = 0;
        this.count = count;
        this.data = data;
    }

    /**
     * 构造方法，初始化{@code code}、{@code count}、{@code data}和{@code totalRow}
     *
     * @param count    记录条数
     * @param data     记录列表
     * @param totalRow 记录统计行
     * @since 0.1.5
     */
    public PageBean(Integer count, List<?> data, Object totalRow) {
        super();
        this.code = 0;
        this.count = count;
        this.data = data;
        this.totalRow = totalRow;
    }

    /**
     * 设置{@code count}和{@code data}
     *
     * @param count 记录条数
     * @param data  记录列表
     * @return 当前{@code PageBean}实例
     * @since 0.0.1
     */
    public PageBean init(Integer count, List<?> data) {
        this.count = count;
        this.data = data;
        return this;
    }

    /**
     * 设置{@code count}、{@code data}和{@code totalRow}
     *
     * @param count    记录条数
     * @param data     记录列表
     * @param totalRow 记录统计行
     * @return 当前{@code PageBean}实例
     * @since 0.1.5
     */
    public PageBean init(Integer count, List<?> data, Object totalRow) {
        this.count = count;
        this.data = data;
        this.totalRow = totalRow;
        return this;
    }

    /**
     * 分页
     *
     * @param count 记录条数
     * @param data  记录列表
     * @return {@code PageBean}实例
     * @since 0.0.1
     */
    public static PageBean pagination(Integer count, List<?> data) {
        return new PageBean(count, data);
    }

    /**
     * 分页，带统计行
     *
     * @param count    记录条数
     * @param data     记录列表
     * @param totalRow 记录统计行
     * @return {@code PageBean}实例
     * @since 0.1.5
     */
    public static PageBean pagination(Integer count, List<?> data, Object totalRow) {
        return new PageBean(count, data, totalRow);
    }

    public int getCode() {
        return code;
    }

    public PageBean setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public PageBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCount() {
        return count;
    }

    public PageBean setCount(int count) {
        this.count = count;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public PageBean setObject(Object object) {
        this.object = object;
        return this;
    }

    public List<?> getData() {
        return data;
    }

    public PageBean setData(List<?> data) {
        this.data = data;
        return this;
    }

    public Object getTotalRow() {
        return totalRow;
    }

    public PageBean setTotalRow(Object totalRow) {
        this.totalRow = totalRow;
        return this;
    }

    @Override
    public String toString() {
        return "PageBean [" +
                "code=" + code +
                ", msg=" + msg +
                ", count=" + count +
                ", object=" + object +
                ", data=" + data +
                ", totalRow=" + totalRow +
                "]";
    }
}
