/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期时间通用方法
 * 
 * @author Chanus
 * @date 2018-08-30 15:26:15
 * @since 0.0.1
 */
public class DateUtils {
	/** 日期格式 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	/** 日期时间格式 */
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/** 
     * 获取SimpleDateFormat对象
     * 
     * @param format 日期格式
     * @return {@code SimpleDateFormat}对象
     * @since 0.0.1
     */  
	private static SimpleDateFormat getDateFormat(String format) {
		return new SimpleDateFormat(format);
	}
	
	/**
	 * 格式化时间对象
	 * 
	 * @param date	时间
	 * @param format	时间格式
	 * @return 格式化后的时间字符串
	 * @since 0.0.1
	 */
	public static String formatDate(Date date, String format) {
		return (date == null || StringUtils.isBlank(format)) ? null : getDateFormat(format).format(date);
	}
	
	/**
	 * 时间字符串转换成时间对象
	 * 
	 * @param datestr	时间字符串
	 * @param format	时间格式
	 * @return 转换后的时间对象
	 * @throws ParseException
	 * @since 0.0.1
	 */
	public static Date parseDate(String datestr, String format) {
		if (StringUtils.isBlank(datestr) || StringUtils.isBlank(format))
			return null;
			
		try {
			return getDateFormat(format).parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将时间转换成yyyy-MM-dd HH:mm:ss格式的字符串
	 * 
	 * @param date 时间
	 * @return yyyy-MM-dd HH:mm:ss格式的字符串，若{@code date}为空，则返回null
	 * @see DateUtils#formatDate(Date, String)
	 * @since 0.0.1
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, DATETIME_FORMAT);
	}
	
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的时间字符串转换成时间
	 * 
	 * @param datestr 时间字符串
	 * @return 时间对象，若{@code datestr}为空，则返回null
	 * @see DateUtils#parseDate(String, String)
	 * @since 0.0.1
	 */
	public static Date parseDateTime(String datestr) {
		return parseDate(datestr, DATETIME_FORMAT);
	}
	
	/**
	 * 将时间转换成yyyy-MM-dd格式的字符串
	 * 
	 * @param date 时间
	 * @return yyyy-MM-dd格式的字符串，若{@code date}为空，则返回null
	 * @see DateUtils#formatDate(Date, String)
	 * @since 0.0.1
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATE_FORMAT);
	}
	
	/**
	 * 将yyyy-MM-dd格式的字符串转换成时间
	 * 
	 * @param datestr 时间字符串
	 * @return 时间对象，若{@code datestr}为空，则返回null
	 * @see DateUtils#parseDate(String, String)
	 * @since 0.0.1
	 */
	public static Date parseDate(String datestr) {
		return parseDate(datestr, DATE_FORMAT);
	}
	
	/**
	 * 获取当天的yyyy-MM-dd格式字符串
	 * 
	 * @return 当天的yyyy-MM-dd格式字符串
	 * @see DateUtils#formatDate(Date, String)
	 * @since 0.0.1
	 */
	public static String getDateToday() {
		return formatDate(new Date(), DATE_FORMAT);
	}
	
	/**
	 * 获取当天的yyyy-MM-dd HH:mm:ss格式字符串
	 * 
	 * @return 当天的yyyy-MM-dd HH:mm:ss格式字符串
	 * @see DateUtils#formatDateTime(Date)
	 * @since 0.0.8
	 */
	public static String getDateTimeToday() {
		return formatDateTime(new Date());
	}
	
	/**
	 * 获取昨天的yyyy-MM-dd格式字符串
	 * 
	 * @return 昨天的yyyy-MM-dd格式字符串
	 * @see DateUtils#getYesterdayDateStr()
	 * @since 0.0.1
	 */
	@Deprecated
	public static String getYdayDateStr() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		return formatDate(calendar.getTime(), DATE_FORMAT);
	}
	
	/**
	 * 获取昨天的yyyy-MM-dd格式字符串
	 * 
	 * @return 昨天的yyyy-MM-dd格式字符串
	 * @see DateUtils#getDateYesterday()
	 * @since 0.0.5
	 */
	@Deprecated
	public static String getYesterdayDateStr() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		return formatDate(calendar.getTime(), DATE_FORMAT);
	}
	
	/**
	 * 获取昨天的yyyy-MM-dd格式字符串
	 * 
	 * @return 昨天的yyyy-MM-dd格式字符串
	 * @see DateUtils#formatDate(Date, String)
	 * @since 0.0.8
	 */
	public static String getDateYesterday() {
		return formatDate(getDateTimeBeforeDay(new Date(), -1), DATE_FORMAT);
	}
	
	/**
	 * 获取昨天的yyyy-MM-dd HH:mm:ss格式字符串
	 * 
	 * @return 昨天的yyyy-MM-dd HH:mm:ss格式字符串
	 * @see DateUtils#formatDateTime(Date)
	 * @since 0.0.8
	 */
	public static String getDateTimeYesterday() {
		return formatDateTime(getDateTimeBeforeDay(new Date(), -1));
	}
	
	/**
	 * 获取明天的yyyy-MM-dd格式字符串
	 * 
	 * @return 明天的yyyy-MM-dd格式字符串
	 * @see DateUtils#formatDate(Date, String)
	 * @since 0.0.8
	 */
	public static String getDateTomorrow() {
		return formatDate(getDateTimeBeforeDay(new Date(), 1), DATE_FORMAT);
	}
	
	/**
	 * 获取明天的yyyy-MM-dd HH:mm:ss格式字符串
	 * 
	 * @return 明天的yyyy-MM-dd HH:mm:ss格式字符串
	 * @see DateUtils#formatDateTime(Date)
	 * @since 0.0.8
	 */
	public static String getDateTimeTomorrow() {
		return formatDateTime(getDateTimeBeforeDay(new Date(), 1));
	}
	
	/**
	 * 获取当天00:00:00时刻时间
	 * 
	 * @return 当天00:00:00时刻时间
	 * @see DateUtils#parseDate(String, String)
	 * @since 0.0.1
	 */
	public static Date getStartDateToday() {
		return parseDate(getDateToday() + " 00:00:00", DATETIME_FORMAT);
	}
	
	/**
	 * 获取当天23:59:59时刻时间
	 * 
	 * @return 当天23:59:59时刻时间
	 * @see DateUtils#parseDate(String, String)
	 * @since 0.0.1
	 */
	public static Date getEndDateToday() {
		return parseDate(getDateToday() + " 23:59:59", DATETIME_FORMAT);
	}
	
	/**
	 * 获取指定时间之前或之后{@code year}年的时间
	 * 
	 * @param date 时间
	 * @param year 年数，正数是{@code date}之后，负数是{@code date}之前
	 * @return 指定时间之前或之后{@code year}年的时间
	 * @since 0.0.1
	 */
	public static Date getDateTimeBeforeYear(Date date, int year) {
		if (date == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间之前或之后{@code month}月的时间
	 * 
	 * @param date 时间
	 * @param month 月数，正数是{@code date}之后，负数是{@code date}之前
	 * @return 指定时间之前或之后{@code month}月的时间
	 * @since 0.0.1
	 */
	public static Date getDateTimeBeforeMonth(Date date, int month) {
		if (date == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间之前或之后{@code day}天的时间
	 * 
	 * @param date 时间
	 * @param day 天数，正数是{@code date}之后，负数是{@code date}之前
	 * @return 指定时间之前或之后{@code day}天的时间
	 * @since 0.0.1
	 */
	public static Date getDateTimeBeforeDay(Date date, int day) {
		if (date == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间之前或之后{@code hour}个小时的时间
	 * 
	 * @param date 时间
	 * @param hour 小时数，正数是{@code date}之后，负数是{@code date}之前
	 * @return 指定时间之前或之后{@code hour}个小时的时间
	 * @since 0.0.1
	 */
	public static Date getDateTimeBeforeHour(Date date, int hour) {
		if (date == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间之前或之后{@code minute}分钟的时间
	 * 
	 * @param date 时间
	 * @param minute 分钟数，正数是{@code date}之后，负数是{@code date}之前
	 * @return 指定时间之前或之后{@code minute}分钟的时间
	 * @since 0.0.1
	 */
	public static Date getDateTimeBeforeMinute(Date date, int minute) {
		if (date == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取指定时间之前或之后{@code second}秒的时间
	 * 
	 * @param date	时间
	 * @param second	秒数，正数是{@code date}之后，负数是{@code date}之前
	 * @return 指定时间之前或之后{@code second}秒的时间
	 * @since 0.0.5
	 */
	public static Date getDateTimeBeforeSecond(Date date, int second) {
		if (date == null)
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		
		return calendar.getTime();
	}
	
	/**
	 * 计算时间差
	 * 
	 * @param sourceDate	源时间
	 * @param targetDate	目标时间
	 * @return {@code sourceDate}和{@code targetDate}相差的毫秒数
	 * @since 0.0.1
	 */
	public static long getDiffTimeInMillis(Date sourceDate, Date targetDate) {
		Calendar sourceCalendar = Calendar.getInstance();
		Calendar targetCalendar = Calendar.getInstance();
		sourceCalendar.setTime(sourceDate);
		targetCalendar.setTime(targetDate);
		return targetCalendar.getTimeInMillis() - sourceCalendar.getTimeInMillis();
	}
	
	/**
	 * 获取两个日期相隔的年数，忽略时分秒
	 * 
	 * @param sourceDate	源时间
	 * @param targetDate	目标时间
	 * @return {@code sourceDate}和{@code targetDate}相隔的年数
	 * @since 0.0.1
	 */
	public static int getDiffTimeInYears(Date sourceDate, Date targetDate) {
		Calendar sourceCalendar = Calendar.getInstance();
		Calendar targetCalendar = Calendar.getInstance();
		if (compare(sourceDate, targetDate) >= 0) {
			sourceCalendar.setTime(sourceDate);
			targetCalendar.setTime(targetDate);
		} else {
			sourceCalendar.setTime(targetDate);
			targetCalendar.setTime(sourceDate);
		}
		
		
		int n = sourceCalendar.get(Calendar.YEAR) - targetCalendar.get(Calendar.YEAR);
		if (n == 0)
			return n;
		
		int sourceMonth = sourceCalendar.get(Calendar.MONTH);
		int sourceDay = sourceCalendar.get(Calendar.DAY_OF_MONTH);
		int targetMonth = targetCalendar.get(Calendar.MONTH);
		int targetDay = targetCalendar.get(Calendar.DAY_OF_MONTH);
		
		return (sourceMonth > targetMonth || (sourceMonth == targetMonth && sourceDay >= targetDay)) ? n : (n - 1);
	}
	
	/**
	 * 获取两个日期相隔的月数，忽略时分秒
	 * 
	 * @param sourceDate	源时间
	 * @param targetDate	目标时间
	 * @return {@code sourceDate}和{@code targetDate}相隔的月数
	 * @since 0.0.1
	 */
	public static int getDiffTimeInMonths(Date sourceDate, Date targetDate) {
		Calendar sourceCalendar = Calendar.getInstance();
		Calendar targetCalendar = Calendar.getInstance();
		if (compare(sourceDate, targetDate) >= 0) {
			sourceCalendar.setTime(sourceDate);
			targetCalendar.setTime(targetDate);
		} else {
			sourceCalendar.setTime(targetDate);
			targetCalendar.setTime(sourceDate);
		}
		
		int n = (sourceCalendar.get(Calendar.YEAR) - targetCalendar.get(Calendar.YEAR)) * 12 + sourceCalendar.get(Calendar.MONTH) - targetCalendar.get(Calendar.MONTH);
		
		return sourceCalendar.get(Calendar.DAY_OF_MONTH) >= targetCalendar.get(Calendar.DAY_OF_MONTH) ? n : (n - 1);
	}
	
	/**
	 * 获取两个日期相隔的天数，忽略时分秒
	 * 
	 * @param sourceDate	源时间
	 * @param targetDate	目标时间
	 * @return {@code sourceDate}和{@code targetDate}相隔的天数
	 * @since 0.0.1
	 */
	public static int getDiffTimeInDays(Date sourceDate, Date targetDate) {
		Calendar sourceCalendar = Calendar.getInstance();
		Calendar targetCalendar = Calendar.getInstance();
		if (compare(sourceDate, targetDate) >= 0) {
			sourceCalendar.setTime(sourceDate);
			targetCalendar.setTime(targetDate);
		} else {
			sourceCalendar.setTime(targetDate);
			targetCalendar.setTime(sourceDate);
		}
		
		int n = 0;
		while (sourceCalendar.get(Calendar.YEAR) != targetCalendar.get(Calendar.YEAR) || sourceCalendar.get(Calendar.MONTH) != targetCalendar.get(Calendar.MONTH) || sourceCalendar.get(Calendar.DAY_OF_MONTH) != targetCalendar.get(Calendar.DAY_OF_MONTH)) {
			targetCalendar.add(Calendar.DATE, 1);
			n++;
		}
		
		return n;
	}
	
	/**
	 * 比较两个时间的大小<br>
	 * 若sourceDate > targetDate 则返回 1<br>
	 * 若sourceDate = targetDate 则返回 0<br>
	 * 若sourceDate < targetDate 则返回 -1
	 * 
	 * @param sourceDate	需要比较的时间
	 * @param targetDate	被比较的时间
	 * @return 比较结果
	 * @since 0.0.1
	 */
	public static int compare(Date sourceDate, Date targetDate) {
		if (sourceDate.getTime() > targetDate.getTime()) {
			return 1;
		} else if (sourceDate.getTime() < targetDate.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * 比较两个指定格式的时间字符串的大小<br>
	 * 若sourceDateStr > targetDateStr 则返回 1<br>
	 * 若sourceDateStr = targetDateStr 则返回 0<br>
	 * 若sourceDateStr < targetDateStr 则返回 -1
	 * 
	 * @param sourceDateStr	需要比较的时间字符串
	 * @param targetDateStr	被比较的时间字符串
	 * @param format	待转换的时间格式
	 * @return 比较结果
	 * @since 0.0.1
	 */
	public static int compare(String sourceDateStr, String targetDateStr, String format) {
		SimpleDateFormat sdf = getDateFormat(format);
		try {
			return compare(sdf.parse(sourceDateStr), sdf.parse(targetDateStr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 判断某个时间是否在某一时间段内
	 * 
	 * @param date	时间
	 * @param start	时间段开始时间
	 * @param end	时间段结束时间
	 * @return {@code true} date在start和end之间；{@code false} date不在start和end之间
	 * @since 0.0.5
	 */
	public static boolean between(Date date, Date start, Date end) {
		return date.after(start) && date.before(end);
	}
	
	/**
	 * 将当前时区的时间转换成指定时区的时间
	 * 
	 * @param sourceDate	时间
	 * @param sourceTimeZone	时间时区
	 * @param targetTimeZone	待转换时区
	 * @return 转换后的时间
	 * @since 0.0.1
	 */
	public static Date convertDateByTimeZone(Date sourceDate, TimeZone sourceTimeZone, TimeZone targetTimeZone) {
		if (sourceTimeZone == null || targetTimeZone == null)
			return sourceDate;
		
		return new Date(sourceDate.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset());
	}
}
