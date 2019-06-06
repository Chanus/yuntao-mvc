/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 集合工具类
 *
 * @author Chanus
 * @date 2018-08-30 15:28:37
 * @since 0.0.1
 */
public class CollectionUtils {
    /**
     * 判断Collection集合是否为空
     *
     * @param collection Collection集合
     * @return {@code true} 集合为空；{@code false} 集合不为空
     * @since 0.0.1
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * 判断Map集合是否为空
     *
     * @param map Map集合
     * @return {@code true} Map集合为空；{@code false} Map集合不为空
     * @since 0.0.1
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param objects 数组
     * @return {@code true} 数组为空；{@code false} 数组不为空
     * @since 0.0.1
     */
    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * 将{@code map}按{@code key}进行排序，按ASCII码从小到大排序
     *
     * @param map 源Map集合
     * @return 排序后的Map集合
     * @since 0.0.1
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (isEmpty(map))
            return null;

        Map<String, Object> sortMap = new TreeMap<>(Comparator.naturalOrder());
        sortMap.putAll(map);

        return sortMap;
    }

    /**
     * 判断数组是否包含某元素
     *
     * @param objects 数据数组
     * @param object  数组元素
     * @return {@code true} array包含object；{@code false} array不包含object
     * @since 0.0.3
     */
    public static boolean contains(Object[] objects, Object object) {
        if (isEmpty(objects))
            return false;

        for (Object o : objects) {
            if ((o == null && object == null) || (o != null && o.equals(object))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 将集合以separator连接并以字符串的形式返回
     *
     * @param collection 集合
     * @param separator  连接符
     * @return 返回以{@code separator}连接后的字符串
     * @since 0.0.8
     */
    public static String join(Collection<?> collection, String separator) {
        if (isEmpty(collection))
            return null;

        StringBuilder sb = new StringBuilder();
        for (Object o : collection) {
            if (o != null && !"".equals(o.toString()))
                sb.append(o.toString()).append(separator);
        }

        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 将Map转换成指定字符连接和分割的字符串
     *
     * @param map       Map集合数据
     * @param link      key和value之间的连接符，如"="
     * @param separator 每组key和value之间的分割符，如"&"
     * @return {@code link}与{@code separator}连接和分割的字符串
     * @since 0.0.8
     */
    public static String join(Map<?, ?> map, String link, String separator) {
        if (isEmpty(map))
            return null;

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(link).append(entry.getValue()).append(separator);
        }

        return sb.substring(0, sb.length() - separator.length());
    }

    /**
     * 将Map转换成"="连接和"&"分割的字符串
     *
     * @param map Map集合数据
     * @return key1=value1&key2=value2...格式字符串
     * @since 0.0.8
     */
    public static String join(Map<?, ?> map) {
        return join(map, "=", "&");
    }

    /**
     * 将Map的key以separator连接并以字符串的形式返回
     *
     * @param map       Map集合数据
     * @param separator 连接符
     * @return {@code separator}连接的字符串
     * @since 0.0.8
     */
    public static String keyJoin(Map<?, ?> map, String separator) {
        if (isEmpty(map))
            return null;

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(separator);
        }

        return sb.substring(0, sb.length() - separator.length());
    }

    /**
     * 将Map的value以separator连接并以字符串的形式返回
     *
     * @param map       Map集合数据
     * @param separator 连接符
     * @return {@code separator}连接的字符串
     * @since 0.0.8
     */
    public static String valueJoin(Map<?, ?> map, String separator) {
        if (isEmpty(map))
            return null;

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            sb.append(entry.getValue()).append(separator);
        }

        return sb.substring(0, sb.length() - separator.length());
    }

    /**
     * 将数组以separator连接并以字符串的形式返回，排除空元素
     *
     * @param array     数据数组
     * @param separator 分割符
     * @return 以{@code separator}连接的字符串
     * @since 0.0.8
     */
    public static String join(Object[] array, String separator) {
        if (isEmpty(array))
            return null;

        StringBuilder sb = new StringBuilder();
        for (Object object : array) {
            if (object != null && !"".equals(object))
                sb.append(object.toString()).append(separator);
        }

        return sb.substring(0, sb.length() - separator.length());
    }

    /**
     * 将数组以逗号连接并以字符串的形式返回
     *
     * @param array 数据数组
     * @return value1, value2...格式字符串
     * @since 0.0.8
     */
    public static String join(Object[] array) {
        return join(array, ",");
    }
}
