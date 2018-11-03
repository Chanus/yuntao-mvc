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
	 * @param map	Map集合
	 * @return {@code true} Map集合为空；{@code false} Map集合不为空
	 * @since 0.0.1
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.size() == 0;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param objects	数组
	 * @return {@code true} 数组为空；{@code false} 数组不为空
	 * @since 0.0.1
	 */
	public static boolean isEmpty(Object[] objects) {
		return objects == null || objects.length == 0;
	}
	
	/**
	 * 将{@code map}按{@code key}进行排序
	 * 
	 * @param map	源Map集合
	 * @return 排序后的Map集合
	 * @since 0.0.1
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (isEmpty(map))
			return null;
		Map<String, Object> sortMap = new TreeMap<String, Object>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		sortMap.putAll(map);
		return sortMap;
	}

	/**
	 * 判断数组是否包含某元素
	 * 
	 * @param array	数据数组
	 * @param object	数组元素
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
}
