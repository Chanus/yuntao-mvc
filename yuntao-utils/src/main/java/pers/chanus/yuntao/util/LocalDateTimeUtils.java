/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: LocalDateTimeUtils
 * Author:   Chanus
 * Date:     2019-06-12 15:12
 * Description: 云道日期时间工具类
 * History:
 * <author>          <time>             <version>          <desc>
 */
package pers.chanus.yuntao.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 日期时间工具类，适应于jdk 1.8及以上版本
 *
 * @author Chanus
 * @date 2019-06-12 15:12
 * @since 0.1.1
 */
public class LocalDateTimeUtils {
    /**
     * 日期时间格式
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 日期时间格式，带毫秒数
     */
    private static final DateTimeFormatter DATE_TIME_MILLIS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    /**
     * 日期格式
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 时间格式
     */
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    /**
     * 时间格式，带毫秒数
     */
    private static final DateTimeFormatter TIME_MILLIS_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    /**
     * 获取DateTimeFormatter对象
     *
     * @param pattern 日期时间格式字符串
     * @return {@code DateTimeFormatter}对象
     * @since 0.1.1
     */
    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * {@code LocalDateTime}日期时间对象转自定义格式{@code pattern}字符串
     * <p>
     * {@code pattern}可以包含年月日时分秒毫秒数，例如：yyyy-MM-dd HH:mm:ss.SSS，yyyy-MM-dd HH:mm:ss，yyyy-MM-dd，HH:mm:ss.SSS，HH:mm:ss
     *
     * @param localDateTime 日期时间，为空时返回{@code null}
     * @param pattern       日期时间格式字符串，不能为空
     * @return {@code pattern}格式字符串
     * @since 0.1.1
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime == null ? null : localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * {@code LocalDate}日期对象转自定义格式{@code pattern}字符串
     * <p>
     * {@code pattern}可以包含年月日，例如：yyyy-MM-dd，yyyy-MM
     *
     * @param localDate 日期，为空时返回{@code null}
     * @param pattern   日期格式字符串，不能为空
     * @return {@code pattern}格式字符串
     * @since 0.1.1
     */
    public static String format(LocalDate localDate, String pattern) {
        return localDate == null ? null : localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * {@code LocalTime}时间对象转自定义格式{@code pattern}字符串
     * <p>
     * {@code pattern}可以包含时分秒毫秒数，例如：HH:mm:ss.SSS，HH:mm:ss
     *
     * @param localTime 时间，为空时返回{@code null}
     * @param pattern   时间格式字符串，不能为空
     * @return {@code pattern}格式字符串
     * @since 0.1.1
     */
    public static String format(LocalTime localTime, String pattern) {
        return localTime == null ? null : localTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * {@code LocalDateTime}日期时间对象转yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param localDateTime 日期时间，为空时返回{@code null}
     * @return yyyy-MM-dd HH:mm:ss格式字符串
     * @since 0.1.1
     */
    public static String formatDateTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * {@code LocalDateTime}日期时间对象转yyyy-MM-dd HH:mm:ss.SSS格式字符串
     *
     * @param localDateTime 日期时间，为空时返回{@code null}
     * @return yyyy-MM-dd HH:mm:ss.SSS格式字符串
     * @since 0.1.1
     */
    public static String formatDateTimeMillis(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.format(DATE_TIME_MILLIS_FORMATTER);
    }

    /**
     * {@code LocalDateTime}日期时间对象转yyyy-MM-dd格式字符串
     *
     * @param localDateTime 日期时间，为空时返回{@code null}
     * @return yyyy-MM-dd格式字符串
     * @since 0.1.1
     */
    public static String formatDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.format(DATE_FORMATTER);
    }

    /**
     * {@code LocalDateTime}日期时间对象转HH:mm:ss格式字符串
     *
     * @param localDateTime 日期时间，为空时返回{@code null}
     * @return HH:mm:ss格式字符串
     * @since 0.1.1
     */
    public static String formatTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.format(TIME_FORMATTER);
    }

    /**
     * {@code LocalDateTime}日期时间对象转HH:mm:ss.SSS格式字符串
     *
     * @param localDateTime 日期时间，为空时返回{@code null}
     * @return HH:mm:ss格式字符串
     * @since 0.1.1
     */
    public static String formatTimeMillis(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.format(TIME_MILLIS_FORMATTER);
    }

    /**
     * 日期时间字符串转自定义格式{@code pattern}的{@code LocalDateTime}日期时间对象
     * <p>
     * {@code localDateTime}与{@code pattern}需对应，且必须包含时间
     * <p>
     * {@code pattern}可以包含年月日时分秒毫秒数，例如：yyyy-MM-dd HH:mm:ss.SSS，yyyy-MM-dd HH:mm:ss，yyyy-MM-dd HH:mm，yyyy年MM月dd日 HH时mm分ss秒
     *
     * @param localDateTime 日期时间字符串，为空时返回{@code null}
     * @param pattern       日期时间格式字符串，不能为空
     * @return {@code LocalDateTime}日期时间对象
     * @since 0.1.1
     */
    public static LocalDateTime parseDateTime(String localDateTime, String pattern) {
        return StringUtils.isBlank(localDateTime) ? null : LocalDateTime.parse(localDateTime, getDateTimeFormatter(pattern));
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式字符串转{@code LocalDateTime}日期时间对象
     *
     * @param localDateTime 日期时间字符串，为空时返回{@code null}
     * @return {@code LocalDateTime}日期时间对象
     * @since 0.1.1
     */
    public static LocalDateTime parseDateTime(String localDateTime) {
        return StringUtils.isBlank(localDateTime) ? null : LocalDateTime.parse(localDateTime, DATE_TIME_FORMATTER);
    }

    /**
     * yyyy-MM-dd HH:mm:ss.SSS格式字符串转{@code LocalDateTime}日期时间对象
     *
     * @param localDateTimeMillis 日期时间字符串，为空时返回{@code null}
     * @return {@code LocalDateTime}日期时间对象
     * @since 0.1.1
     */
    public static LocalDateTime parseDateTimeMillis(String localDateTimeMillis) {
        return StringUtils.isBlank(localDateTimeMillis) ? null : LocalDateTime.parse(localDateTimeMillis, DATE_TIME_MILLIS_FORMATTER);
    }

    /**
     * 日期字符串转自定义格式{@code pattern}的{@code LocalDate}日期对象
     * <p>
     * {@code localDate}与{@code pattern}需对应，且不能包含时间
     * <p>
     * {@code pattern}可以包含年月日，例如：yyyy-MM-dd，yyyy年MM月dd日
     *
     * @param localDate 日期字符串，为空时返回{@code null}
     * @param pattern   日期格式字符串，不能为空
     * @return {@code LocalDate}日期对象
     * @since 0.1.1
     */
    public static LocalDate parseDate(String localDate, String pattern) {
        return StringUtils.isBlank(localDate) ? null : LocalDate.parse(localDate, getDateTimeFormatter(pattern));
    }

    /**
     * yyyy-MM-dd格式字符串转{@code LocalDate}日期对象
     *
     * @param localDate 日期字符串，为空时返回{@code null}
     * @return {@code LocalDate}日期对象
     * @since 0.1.1
     */
    public static LocalDate parseDate(String localDate) {
        return StringUtils.isBlank(localDate) ? null : LocalDate.parse(localDate, DATE_FORMATTER);
    }

    /**
     * 时间字符串转自定义格式{@code pattern}的{@code LocalTime}日期时间对象
     * <p>
     * {@code localTime}与{@code pattern}需对应，且不能包含年月日
     * <p>
     * {@code pattern}可以包含时分秒毫秒数，例如：HH:mm:ss.SSS，HH:mm:ss，HH时mm分ss秒
     *
     * @param localTime 时间字符串，为空时返回{@code null}
     * @param pattern   时间格式字符串，不能为空
     * @return {@code LocalTime}时间对象
     * @since 0.1.1
     */
    public static LocalTime parseTime(String localTime, String pattern) {
        return StringUtils.isBlank(localTime) ? null : LocalTime.parse(localTime, getDateTimeFormatter(pattern));
    }

    /**
     * HH:mm:ss格式字符串转{@code LocalTime}时间对象
     *
     * @param localTime 时间字符串，为空时返回{@code null}
     * @return {@code LocalTime}时间对象
     * @since 0.1.1
     */
    public static LocalTime parseTime(String localTime) {
        return StringUtils.isBlank(localTime) ? null : LocalTime.parse(localTime, TIME_FORMATTER);
    }

    /**
     * HH:mm:ss.SSS格式字符串转{@code LocalTime}时间对象
     *
     * @param localTimeMillis 时间字符串，为空时返回{@code null}
     * @return {@code LocalTime}时间对象
     * @since 0.1.1
     */
    public static LocalTime parseTimeMillis(String localTimeMillis) {
        return StringUtils.isBlank(localTimeMillis) ? null : LocalTime.parse(localTimeMillis, TIME_MILLIS_FORMATTER);
    }

    /**
     * 获取当前日期时间的自定义格式{@code pattern}字符串
     *
     * @param pattern 时间格式字符串，不能为空
     * @return {@code pattern}格式字符串
     * @since 0.1.1
     */
    public static String now(String pattern) {
        return LocalDateTime.now().format(getDateTimeFormatter(pattern));
    }

    /**
     * 获取当前日期时间的yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @return yyyy-MM-dd HH:mm:ss格式字符串
     * @since 0.1.1
     */
    public static String nowDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

    /**
     * 获取当前日期时间的yyyy-MM-dd HH:mm:ss.SSS格式字符串
     *
     * @return yyyy-MM-dd HH:mm:ss.SSS格式字符串
     * @since 0.1.1
     */
    public static String nowDateTimeMillis() {
        return LocalDateTime.now().format(DATE_TIME_MILLIS_FORMATTER);
    }

    /**
     * 获取当前日期的yyyy-MM-dd格式字符串
     *
     * @return yyyy-MM-dd格式字符串
     * @since 0.1.1
     */
    public static String nowDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * 获取当前时间的HH:mm:ss格式字符串
     *
     * @return HH:mm:ss格式字符串
     * @since 0.1.1
     */
    public static String nowTime() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    /**
     * 获取当前时间的HH:mm:ss.SSS格式字符串
     *
     * @return HH:mm:ss.SSS格式字符串
     * @since 0.1.1
     */
    public static String nowTimeMillis() {
        return LocalTime.now().format(TIME_MILLIS_FORMATTER);
    }

    /**
     * 获取昨日的yyyy-MM-dd格式字符串
     *
     * @return yyyy-MM-dd格式字符串
     * @since 0.1.1
     */
    public static String yesterday() {
        return minus(LocalDate.now(), 1, ChronoUnit.DAYS).format(DATE_FORMATTER);
    }

    /**
     * 获取明日的yyyy-MM-dd格式字符串
     *
     * @return yyyy-MM-dd格式字符串
     * @since 0.1.1
     */
    public static String tomorrow() {
        return plus(LocalDate.now(), 1, ChronoUnit.DAYS).format(DATE_FORMATTER);
    }

    /**
     * {@code LocalDateTime}转{@code Date}
     *
     * @param localDateTime {@code LocalDateTime}对象
     * @return {@code Date}对象
     * @since 0.1.1
     */
    public static Date convertToDate(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * {@code LocalDate}转{@code Date}
     *
     * @param localDate {@code LocalDate}对象
     * @return {@code Date}对象
     * @since 0.1.1
     */
    public static Date convertToDate(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * {@code Date}转{@code LocalDateTime}
     *
     * @param date {@code Date}对象
     * @return {@code LocalDateTime}对象
     * @since 0.1.1
     */
    public static LocalDateTime convertToLocalDateTimeFromDate(Date date) {
        return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * {@code Date}转{@code LocalDate}
     *
     * @param date {@code Date}对象
     * @return {@code LocalDate}对象
     * @since 0.1.1
     */
    public static LocalDate convertToLocalDateFromDate(Date date) {
        return date == null ? null : LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 时间戳转{@code LocalDateTime}
     *
     * @param timestamp 时间戳
     * @return {@code LocalDateTime}对象
     * @since 0.1.1
     */
    public static LocalDateTime convertToLocalDateTimeFromTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    /**
     * 时间戳转{@code LocalDate}
     *
     * @param timestamp 时间戳
     * @return {@code LocalDate}对象
     * @since 0.1.1
     */
    public static LocalDate convertToLocalDateFromTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 获取指定时间{@code localDateTime}的秒数
     *
     * @param localDateTime 日期时间
     * @return 指定时间{@code localDateTime}的秒数
     * @since 0.1.1
     */
    public static long getSeconds(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间{@code localDateTime}的毫秒数
     *
     * @param localDateTime 日期时间
     * @return 指定时间{@code localDateTime}的毫秒数
     * @since 0.1.1
     */
    public static long getMillis(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取某一天的开始时间
     *
     * @param localDateTime 日期时间
     * @return 某一天的00:00:00.000000000时刻
     * @since 0.1.1
     */
    public static LocalDateTime getDayStartTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.with(LocalTime.MIN);
    }

    /**
     * 获取某一天的结束时间
     *
     * @param localDateTime 日期时间
     * @return 某一天的23:59:59.999999999时刻
     * @since 0.1.1
     */
    public static LocalDateTime getDayEndTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.with(LocalTime.MAX);
    }

    /**
     * 获取当天的开始时间
     *
     * @return 当天的00:00:00.000000000时刻
     * @since 0.1.1
     */
    public static LocalDateTime getTodayStartTime() {
        return LocalDateTime.now().with(LocalTime.MIN);
    }

    /**
     * 获取当天的结束时间
     *
     * @return 当天的23:59:59.999999999时刻
     * @since 0.1.1
     */
    public static LocalDateTime getTodayEndTime() {
        return LocalDateTime.now().with(LocalTime.MAX);
    }

    /**
     * 获取某月的开始时间
     *
     * @param localDateTime 日期时间
     * @return 某月的00:00:00.000000000时刻
     * @since 0.1.1
     */
    public static LocalDateTime getMonthStartTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
    }

    /**
     * 获取某月的结束时间
     *
     * @param localDateTime 日期时间
     * @return 某月的23:59:59.999999999时刻
     * @since 0.1.1
     */
    public static LocalDateTime getMonthEndTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
    }

    /**
     * 获取当月的开始时间
     *
     * @return 当月的00:00:00.000000000时刻
     * @since 0.1.1
     */
    public static LocalDateTime getThisMonthStartTime() {
        return LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
    }

    /**
     * 获取当月的结束时间
     *
     * @return 当月的23:59:59.999999999时刻
     * @since 0.1.1
     */
    public static LocalDateTime getThisMonthEndTime() {
        return LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
    }

    /**
     * 获取某年的开始时间
     *
     * @param localDateTime 日期时间
     * @return 某年的00:00:00.000000000时刻
     * @since 0.1.1
     */
    public static LocalDateTime getYearStartTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
    }

    /**
     * 获取某年的结束时间
     *
     * @param localDateTime 日期时间
     * @return 某年的23:59:59.999999999时刻
     * @since 0.1.1
     */
    public static LocalDateTime getYearEndTime(LocalDateTime localDateTime) {
        return localDateTime == null ? null : localDateTime.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
    }

    /**
     * 获取当年的开始时间
     *
     * @return 当年的00:00:00.000000000时刻
     * @since 0.1.1
     */
    public static LocalDateTime getThisYearStartTime() {
        return LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
    }

    /**
     * 获取当年的结束时间
     *
     * @return 当年的23:59:59.999999999时刻
     * @since 0.1.1
     */
    public static LocalDateTime getThisYearEndTime() {
        return LocalDateTime.now().with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
    }

    /**
     * 日期时间根据{@code field}加上一个值{@code number}
     *
     * @param localDateTime 日期时间
     * @param number        增加的数值
     * @param field         操作区域，年、月、日、时、分、秒、毫秒、星期等
     * @return {@code localDateTime}根据{@code field}加上{@code number}后的日期时间
     * @since 0.1.1
     */
    public static LocalDateTime plus(LocalDateTime localDateTime, long number, ChronoUnit field) {
        return localDateTime == null ? null : localDateTime.plus(number, field);
    }

    /**
     * 日期根据{@code field}加上一个值{@code number}
     *
     * @param localDate 日期
     * @param number    增加的数值
     * @param field     操作区域，年、月、日、星期等
     * @return {@code localDate}根据{@code field}加上{@code number}后的日期
     * @since 0.1.1
     */
    public static LocalDate plus(LocalDate localDate, long number, ChronoUnit field) {
        return localDate == null ? null : localDate.plus(number, field);
    }

    /**
     * 时间根据{@code field}加上一个值{@code number}
     *
     * @param localTime 时间
     * @param number    增加的数值
     * @param field     操作区域，时、分、秒、毫秒等
     * @return {@code localTime}根据{@code field}加上{@code number}后的时间
     * @since 0.1.1
     */
    public static LocalTime plus(LocalTime localTime, long number, ChronoUnit field) {
        return localTime == null ? null : localTime.plus(number, field);
    }

    /**
     * 日期时间根据{@code field}减去一个值{@code number}
     *
     * @param localDateTime 日期时间
     * @param number        减去的数值
     * @param field         操作区域，年、月、日、时、分、秒、毫秒、星期等
     * @return {@code localDateTime}根据{@code field}减去{@code number}后的日期时间
     * @since 0.1.1
     */
    public static LocalDateTime minus(LocalDateTime localDateTime, long number, ChronoUnit field) {
        return localDateTime == null ? null : localDateTime.minus(number, field);
    }

    /**
     * 日期根据{@code field}减去一个值{@code number}
     *
     * @param localDate 日期
     * @param number    减去的数值
     * @param field     操作区域，年、月、日、星期等
     * @return {@code localDate}根据{@code field}减去{@code number}后的日期
     * @since 0.1.1
     */
    public static LocalDate minus(LocalDate localDate, long number, ChronoUnit field) {
        return localDate == null ? null : localDate.minus(number, field);
    }

    /**
     * 时间根据{@code field}减去一个值{@code number}
     *
     * @param localTime 时间
     * @param number    减去的数值
     * @param field     操作区域，时、分、秒、毫秒等
     * @return {@code localTime}根据{@code field}减去{@code number}后的时间
     * @since 0.1.1
     */
    public static LocalTime minus(LocalTime localTime, long number, ChronoUnit field) {
        return localTime == null ? null : localTime.minus(number, field);
    }

    /**
     * 根据{@code field}获取两个日期时间相间隔的数值，如相间隔的年数，月数，天数，小时数，分钟数，秒数，毫秒数，星期数等，{@code start}和{@code end}不区分先后顺序
     * <p>
     * 其中，计算相间隔的年数和月数时忽略时间部分
     *
     * @param start 开始日期时间
     * @param end   结束日期时间
     * @param field 操作区域，年、月、日、时、分、秒、毫秒、星期等
     * @return {@code start}与{@code end}根据{@code field}相间的数值
     * @since 0.1.1
     */
    public static long interval(LocalDateTime start, LocalDateTime end, ChronoUnit field) {
        if (field == ChronoUnit.YEARS || field == ChronoUnit.MONTHS) {
            Period period = Period.between(LocalDate.from(start), LocalDate.from(end));
            return field == ChronoUnit.YEARS ? Math.abs(period.getYears()) : Math.abs(period.getYears() * 12 + period.getMonths());
        }

        return Math.abs(field.between(start, end));
    }

    /**
     * 根据{@code field}获取两个日期相间隔的数值，如相间隔的年数，月数，天数，星期数，{@code start}和{@code end}不区分先后顺序
     *
     * @param start 开始日期
     * @param end   结束日期
     * @param field 操作区域，年、月、日、星期
     * @return {@code start}与{@code end}根据{@code field}相间的数值
     * @since 0.1.1
     */
    public static long interval(LocalDate start, LocalDate end, ChronoUnit field) {
        if (field == ChronoUnit.YEARS || field == ChronoUnit.MONTHS) {
            Period period = Period.between(LocalDate.from(start), LocalDate.from(end));
            return field == ChronoUnit.YEARS ? Math.abs(period.getYears()) : Math.abs(period.getYears() * 12 + period.getMonths());
        }

        return Math.abs(field.between(start, end));
    }

    /**
     * 比较两个日期时间的大小
     *
     * @param source 源日期时间
     * @param target 目标日期时间
     * @return {@code source}大于{@code target}返回1，{@code source}小于{@code target}返回-1，{@code source}等于{@code target}返回0
     * @since 0.1.1
     */
    public static int compare(LocalDateTime source, LocalDateTime target) {
        if (source.isBefore(target))
            return -1;
        else if (source.isAfter(target))
            return 1;
        else
            return 0;
    }

    /**
     * 比较两个日期的大小
     *
     * @param source 源日期
     * @param target 目标日期
     * @return {@code source}大于{@code target}返回1，{@code source}小于{@code target}返回-1，{@code source}等于{@code target}返回0
     * @since 0.1.1
     */
    public static int compare(LocalDate source, LocalDate target) {
        if (source.isBefore(target))
            return -1;
        else if (source.isAfter(target))
            return 1;
        else
            return 0;
    }

    /**
     * 比较两个时间的大小
     *
     * @param source 源时间
     * @param target 目标时间
     * @return {@code source}大于{@code target}返回1，{@code source}小于{@code target}返回-1，{@code source}等于{@code target}返回0
     * @since 0.1.1
     */
    public static int compare(LocalTime source, LocalTime target) {
        if (source.isBefore(target))
            return -1;
        else if (source.isAfter(target))
            return 1;
        else
            return 0;
    }

    /**
     * 比较日期时间是否在指定日期时间范围之类
     *
     * @param localDateTime 待比较日期时间
     * @param start         开始日期时间
     * @param end           结束日期时间
     * @return {@code localDateTime}在{@code start}与{@code end}之间返回{@code true}，否则返回{@code false}
     * @since 0.1.1
     */
    public static boolean between(LocalDateTime localDateTime, LocalDateTime start, LocalDateTime end) {
        return !localDateTime.isBefore(start) && !localDateTime.isAfter(end);
    }

    /**
     * 比较日期是否在指定日期范围之类
     *
     * @param localDate 待比较日期
     * @param start     开始日期
     * @param end       结束日期
     * @return {@code localDate}在{@code start}与{@code end}之间返回{@code true}，否则返回{@code false}
     * @since 0.1.1
     */
    public static boolean between(LocalDate localDate, LocalDate start, LocalDate end) {
        return !localDate.isBefore(start) && !localDate.isAfter(end);
    }

    /**
     * 比较时间是否在指定时间范围之类
     *
     * @param localTime 待比较时间
     * @param start     开始时间
     * @param end       结束时间
     * @return {@code localTime}在{@code start}与{@code end}之间返回{@code true}，否则返回{@code false}
     * @since 0.1.1
     */
    public static boolean between(LocalTime localTime, LocalTime start, LocalTime end) {
        return !localTime.isBefore(start) && !localTime.isAfter(end);
    }
}
