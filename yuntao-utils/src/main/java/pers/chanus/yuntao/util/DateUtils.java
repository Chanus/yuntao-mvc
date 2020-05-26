/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期时间通用方法
 *
 * @author Chanus
 * @date 2018-08-30 15:26:15
 * @since 0.0.1
 */
public class DateUtils {
    /**
     * 日期格式
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期时间格式
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式
     */
    private static final String TIME_FORMAT = "HH:mm:ss";
    /**
     * java.util.Date原始格式
     */
    private static final String ORIGINAL_DATETIME_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
    /**
     * 每日开始时刻00:00:00
     */
    private static final String START_TIME = " 00:00:00";
    /**
     * 每日结束时刻23:59:59
     */
    private static final String END_TIME = " 23:59:59";

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
     * @param date   时间
     * @param format 时间格式
     * @return 格式化后的时间字符串
     * @since 0.0.1
     */
    public static String formatDate(Date date, String format) {
        return (date == null || StringUtils.isBlank(format)) ? null : getDateFormat(format).format(date);
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
     * 将时间转换成yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param datetime 时间
     * @return yyyy-MM-dd HH:mm:ss格式的字符串，若{@code datetime}为空，则返回null
     * @see DateUtils#formatDate(Date, String)
     * @since 0.0.1
     */
    public static String formatDateTime(Date datetime) {
        return formatDate(datetime, DATETIME_FORMAT);
    }

    /**
     * 将时间转换成HH:mm:ss格式的字符串
     *
     * @param time 时间
     * @return HH:mm:ss格式的字符串，若{@code time}为空，则返回null
     * @see DateUtils#formatDate(Date, String)
     * @since 0.1.9
     */
    public static String formatTime(Date time) {
        return formatDate(time, TIME_FORMAT);
    }

    /**
     * 时间字符串转换成时间对象
     *
     * @param date   时间字符串
     * @param format 时间格式
     * @return 转换后的时间对象
     * @since 0.0.1
     */
    public static Date parseDate(String date, String format) {
        if (StringUtils.isBlank(date) || StringUtils.isBlank(format))
            return null;

        try {
            return getDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将yyyy-MM-dd格式的字符串转换成时间
     *
     * @param date 时间字符串
     * @return 时间对象，若{@code date}为空，则返回null
     * @see DateUtils#parseDate(String, String)
     * @since 0.0.1
     */
    public static Date parseDate(String date) {
        return parseDate(date, DATE_FORMAT);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的时间字符串转换成时间
     *
     * @param datetime 时间字符串
     * @return 时间对象，若{@code datetime}为空，则返回null
     * @see DateUtils#parseDate(String, String)
     * @since 0.0.1
     */
    public static Date parseDateTime(String datetime) {
        return parseDate(datetime, DATETIME_FORMAT);
    }

    /**
     * 将HH:mm:ss格式的时间字符串转换成时间
     *
     * @param time 时间字符串
     * @return 时间对象，若{@code time}为空，则返回null
     * @see DateUtils#parseDate(String, String)
     * @since 0.1.9
     */
    public static Date parseTime(String time) {
        return parseDate(time, TIME_FORMAT);
    }

    /**
     * 将EEE MMM dd HH:mm:ss zzz yyyy格式的时间字符串转换成时间
     *
     * @param originalDateTime EEE MMM dd HH:mm:ss zzz yyyy格式时间字符串
     * @return 时间对象，若{@code datetime}为空，则返回null
     * @since 0.1.7
     */
    public static Date parseOriginalDateTime(String originalDateTime) {
        if (StringUtils.isBlank(originalDateTime))
            return null;

        try {
            return new SimpleDateFormat(ORIGINAL_DATETIME_FORMAT, Locale.ENGLISH).parse(originalDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
        return parseDate(getDateToday() + START_TIME, DATETIME_FORMAT);
    }

    /**
     * 获取当天23:59:59时刻时间
     *
     * @return 当天23:59:59时刻时间
     * @see DateUtils#parseDate(String, String)
     * @since 0.0.1
     */
    public static Date getEndDateToday() {
        return parseDate(getDateToday() + END_TIME, DATETIME_FORMAT);
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
     * @param date  时间
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
     * @param day  天数，正数是{@code date}之后，负数是{@code date}之前
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
     * @param date   时间
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
     * @param date   时间
     * @param second 秒数，正数是{@code date}之后，负数是{@code date}之前
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
     * @param sourceDate 源时间
     * @param targetDate 目标时间
     * @return {@code sourceDate}和{@code targetDate}相差的毫秒数
     * @since 0.0.1
     */
    public static long getDiffTimeInMillis(Date sourceDate, Date targetDate) {
        return Math.abs(sourceDate.getTime() - targetDate.getTime());
    }

    /**
     * 获取两个日期相隔的年数，忽略时分秒
     *
     * @param sourceDate 源时间
     * @param targetDate 目标时间
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
     * @param sourceDate 源时间
     * @param targetDate 目标时间
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
     * @param sourceDate 源时间
     * @param targetDate 目标时间
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
     * @param sourceDate 需要比较的时间
     * @param targetDate 被比较的时间
     * @return 比较结果
     * @since 0.0.1
     */
    public static int compare(Date sourceDate, Date targetDate) {
        return Long.compare(sourceDate.getTime(), targetDate.getTime());
    }

    /**
     * 比较两个指定格式的时间字符串的大小<br>
     * 若sourceDateStr > targetDateStr 则返回 1<br>
     * 若sourceDateStr = targetDateStr 则返回 0<br>
     * 若sourceDateStr < targetDateStr 则返回 -1
     *
     * @param sourceDateStr 需要比较的时间字符串
     * @param targetDateStr 被比较的时间字符串
     * @param format        待转换的时间格式
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
     * @param date  时间
     * @param start 时间段开始时间
     * @param end   时间段结束时间
     * @return {@code true} date在start和end之间；{@code false} date不在start和end之间
     * @since 0.0.5
     */
    public static boolean between(Date date, Date start, Date end) {
        return !date.before(start) && !date.after(end);
    }

    /**
     * 将当前时区的时间转换成指定时区的时间
     *
     * @param sourceDate     时间
     * @param sourceTimeZone 时间时区
     * @param targetTimeZone 待转换时区
     * @return 转换后的时间
     * @since 0.0.1
     */
    public static Date convertDateByTimeZone(Date sourceDate, TimeZone sourceTimeZone, TimeZone targetTimeZone) {
        if (sourceTimeZone == null || targetTimeZone == null)
            return sourceDate;

        return new Date(sourceDate.getTime() - sourceTimeZone.getRawOffset() + targetTimeZone.getRawOffset());
    }

    /**
     * 获取指定日期是星期几，1表示周一，2表示周二，3表示周三，4表示周四，5表示周五，6表示周六，7表示周日
     *
     * @param date 日期时间
     * @return {@code date}是星期几
     * @since 0.1.9
     */
    public static int dayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        return dayOfWeek == 0 ? 7 : dayOfWeek;
    }

    /**
     * 获取指定日期是星期几，1表示周一，2表示周二，3表示周三，4表示周四，5表示周五，6表示周六，7表示周日
     *
     * @param dateStr 日期时间字符串
     * @return {@code dateStr}是星期几
     * @since 0.1.9
     */
    public static int dayOfWeek(String dateStr) {
        return dayOfWeek(parseDate(dateStr));
    }

    /**
     * 获取今天是星期几，1表示周一，2表示周二，3表示周三，4表示周四，5表示周五，6表示周六，7表示周日
     *
     * @return 今天是星期几
     * @since 0.1.9
     */
    public static int dayOfWeek() {
        return dayOfWeek(new Date());
    }

    /**
     * 获取指定日期是指定时间周期内的第几天，1表示第一天，0表示指定时间小于周期开始时间
     *
     * @param date      日期时间
     * @param cycle     时间周期的天数
     * @param beginDate 时间周期的开始时间
     * @return 指定日期是指定时间周期内的第几天，如果指定时间小于周期开始时间，则返回0
     * @since 0.1.9
     */
    public static int dayOfCycle(Date date, int cycle, Date beginDate) {
        if (DateUtils.compare(date, beginDate) == -1)
            return 0;
        return getDiffTimeInDays(beginDate, date) % cycle + 1;
    }

    /**
     * 获取指定日期是指定时间周期内的第几天，1表示第一天，0表示指定时间小于周期开始时间
     *
     * @param dateStr      日期时间字符串
     * @param cycle        时间周期的天数
     * @param beginDateStr 时间周期的开始时间字符串
     * @return 指定日期是指定时间周期内的第几天，如果指定时间小于周期开始时间，则返回0
     * @since 0.1.9
     */
    public static int dayOfCycle(String dateStr, int cycle, String beginDateStr) {
        return dayOfCycle(DateUtils.parseDate(dateStr), cycle, DateUtils.parseDate(beginDateStr));
    }

    /**
     * 获取今天是指定时间周期内的第几天，1表示第一天，0表示今天小于周期开始时间
     *
     * @param cycle     时间周期的天数
     * @param beginDate 时间周期的开始时间
     * @return 今天是指定时间周期内的第几天，如果今天小于周期开始时间，则返回0
     * @since 0.1.9
     */
    public static int dayOfCycle(int cycle, Date beginDate) {
        return dayOfCycle(new Date(), cycle, beginDate);
    }

    /**
     * 获取今天是指定时间周期内的第几天，1表示第一天，0表示今天小于周期开始时间
     *
     * @param cycle        时间周期的天数
     * @param beginDateStr 时间周期的开始时间字符串
     * @return 今天是指定时间周期内的第几天，如果今天小于周期开始时间，则返回0
     * @since 0.1.9
     */
    public static int dayOfCycle(int cycle, String beginDateStr) {
        return dayOfCycle(new Date(), cycle, DateUtils.parseDate(beginDateStr));
    }
}
