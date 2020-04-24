/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: LocalDateTimeUtilsTest
 * Author:   Chanus
 * Date:     2019-06-12 16:40
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test;

import org.junit.Test;
import pers.chanus.yuntao.util.LocalDateTimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * LocalDateTimeUtils工具类测试
 *
 * @author Chanus
 * @date 2019-06-12 16:40
 * @since 0.1.2
 */
public class LocalDateTimeUtilsTest {
    @Test
    public void formatTest() {
        System.out.println("-------------------format(LocalDateTime localDateTime, String pattern)-------------------");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(LocalDateTimeUtils.format(localDateTime, "yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(LocalDateTimeUtils.format(localDateTime, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(LocalDateTimeUtils.format(localDateTime, "yyyy-MM-dd hh:mm:ss"));
        System.out.println(LocalDateTimeUtils.format(localDateTime, "yyyy-MM-dd"));
        System.out.println(LocalDateTimeUtils.format(localDateTime, "yyyy-MM"));
        System.out.println(LocalDateTimeUtils.format(localDateTime, "HH:mm:ss.SSS"));
        System.out.println(LocalDateTimeUtils.format(localDateTime, "HH:mm:ss"));
        System.out.println(LocalDateTimeUtils.format(localDateTime, "hh:mm:ss"));
        System.out.println(LocalDateTimeUtils.format(localDateTime, "HH:mm"));

        System.out.println("-------------------format(LocalDate localDate, String pattern)-------------------");
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(LocalDateTimeUtils.format(localDate, "yyyy-MM-dd"));
        System.out.println(LocalDateTimeUtils.format(localDate, "yyyy-MM"));

        System.out.println("-------------------format(LocalTime localTime, String pattern)-------------------");
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println(LocalDateTimeUtils.format(localTime, "HH:mm:ss.SSS"));
        System.out.println(LocalDateTimeUtils.format(localTime, "HH:mm:ss"));
        System.out.println(LocalDateTimeUtils.format(localTime, "hh:mm:ss"));
        System.out.println(LocalDateTimeUtils.format(localTime, "HH:mm"));

        System.out.println("-------------------formatDateTime(LocalDateTime localDateTime)-------------------");
        System.out.println(LocalDateTimeUtils.formatDateTime(localDateTime));

        System.out.println("-------------------formatDateTimeMillis(LocalDateTime localDateTime)-------------------");
        System.out.println(LocalDateTimeUtils.formatDateTimeMillis(localDateTime));

        System.out.println("-------------------formatDate(LocalDateTime localDateTime)-------------------");
        System.out.println(LocalDateTimeUtils.formatDate(localDateTime));

        System.out.println("-------------------formatTime(LocalDateTime localDateTime)-------------------");
        System.out.println(LocalDateTimeUtils.formatTime(localDateTime));

        System.out.println("-------------------formatTimeMillis(LocalDateTime localDateTime)-------------------");
        System.out.println(LocalDateTimeUtils.formatTimeMillis(localDateTime));
    }

    @Test
    public void parseTest() {
        System.out.println("-------------------parseDateTime(String localDateTime, String pattern)-------------------");
        System.out.println(LocalDateTimeUtils.parseDateTime("2019-06-12 20:11:33.123", "yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(LocalDateTimeUtils.parseDateTime("2019-06-12 20:11:33", "yyyy-MM-dd HH:mm:ss"));
        System.out.println(LocalDateTimeUtils.parseDateTime("2019年06月12日 20时11分33秒", "yyyy年MM月dd日 HH时mm分ss秒"));
        System.out.println(LocalDateTimeUtils.parseDateTime("2019-06-12 20:11", "yyyy-MM-dd HH:mm"));

        System.out.println("-------------------parseDateTime(String localDateTime)-------------------");
        System.out.println(LocalDateTimeUtils.parseDateTime("2019-06-12 20:11:33"));

        System.out.println("-------------------parseDateTimeMillis(String localDateTimeMillis)-------------------");
        System.out.println(LocalDateTimeUtils.parseDateTimeMillis("2019-06-12 20:11:33.123"));

        System.out.println("-------------------parseDate(String localDate, String pattern)-------------------");
        System.out.println(LocalDateTimeUtils.parseDate("2019-06-12", "yyyy-MM-dd"));
        System.out.println(LocalDateTimeUtils.parseDate("2019年06月12日", "yyyy年MM月dd日"));

        System.out.println("-------------------parseDate(String localDate)-------------------");
        System.out.println(LocalDateTimeUtils.parseDate("2019-06-12"));

        System.out.println("-------------------parseTime(String localTime, String pattern)-------------------");
        System.out.println(LocalDateTimeUtils.parseTime("20:11:33.123", "HH:mm:ss.SSS"));
        System.out.println(LocalDateTimeUtils.parseTime("20:11:33", "HH:mm:ss"));
        System.out.println(LocalDateTimeUtils.parseTime("20时11分33秒", "HH时mm分ss秒"));
        System.out.println(LocalDateTimeUtils.parseTime("20:11", "HH:mm"));

        System.out.println("-------------------parseTime(String localTime)-------------------");
        System.out.println(LocalDateTimeUtils.parseTime("20:11:33"));
        System.out.println(LocalDateTimeUtils.parseTimeMillis("20:11:33.123"));
    }

    @Test
    public void nowTest() {
        System.out.println("nowDateTimeMillis: " + LocalDateTimeUtils.now("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("nowDateTime: " + LocalDateTimeUtils.now("yyyy-MM-dd HH:mm:ss"));
        System.out.println("nowDate: " + LocalDateTimeUtils.now("yyyy-MM-dd"));
        System.out.println("nowTime: " + LocalDateTimeUtils.now("HH:mm:ss"));
    }

    @Test
    public void nowDateTimeTest() {
        System.out.println("nowDateTime: " + LocalDateTimeUtils.nowDateTime());
    }

    @Test
    public void nowDateTimeMillisTest() {
        System.out.println("nowDateTimeMillis: " + LocalDateTimeUtils.nowDateTimeMillis());
    }

    @Test
    public void nowDateTest() {
        System.out.println("nowDate: " + LocalDateTimeUtils.nowDate());
    }

    @Test
    public void nowTimeTest() {
        System.out.println("nowTime: " + LocalDateTimeUtils.nowTime());
    }

    @Test
    public void nowTimeMillisTest() {
        System.out.println("nowTimeMillis: " + LocalDateTimeUtils.nowTimeMillis());
    }

    @Test
    public void yesterdayTest() {
        System.out.println("yesterday: " + LocalDateTimeUtils.yesterday());
    }

    @Test
    public void tomorrowTest() {
        System.out.println("tomorrow: " + LocalDateTimeUtils.tomorrow());
    }

    @Test
    public void convertTest() {
        Date date = new Date();
        System.out.println("date：" + date);
        System.out.println("LocalDateTime 转 Date：" + LocalDateTimeUtils.convertToDate(LocalDateTime.now()));
        System.out.println("LocalDate 转 Date：" + LocalDateTimeUtils.convertToDate(LocalDate.now()));

        System.out.println("Date 转 LocalDateTime：" + LocalDateTimeUtils.convertToLocalDateTimeFromDate(date));
        System.out.println("Date 转 LocalDate：" + LocalDateTimeUtils.convertToLocalDateFromDate(date));

        long timestamp = System.currentTimeMillis();
        System.out.println("timestamp：" + timestamp);
        System.out.println("timestamp 转 LocalDateTime：" + LocalDateTimeUtils.convertToLocalDateTimeFromTimestamp(timestamp));
        System.out.println("timestamp 转 LocalDate：" + LocalDateTimeUtils.convertToLocalDateFromTimestamp(timestamp));
    }

    @Test
    public void getSecondsTest() {
        System.out.println("seconds: " + LocalDateTimeUtils.getSeconds(LocalDateTime.now()));
    }

    @Test
    public void getMillisTest() {
        System.out.println("millis: " + LocalDateTimeUtils.getMillis(LocalDateTime.now()));
    }

    @Test
    public void getDayTimeTest() {
        LocalDateTime localDateTime = LocalDateTimeUtils.parseDateTime("2019-05-12 20:11:33");
        System.out.println("指定某一天：" + localDateTime);
        System.out.println("指定某一天开始时间：" + LocalDateTimeUtils.getDayStartTime(localDateTime));
        System.out.println("指定某一天结束时间：" + LocalDateTimeUtils.getDayEndTime(localDateTime));

        System.out.println("当天开始时间：" + LocalDateTimeUtils.getTodayStartTime());
        System.out.println("当天结束时间：" + LocalDateTimeUtils.getTodayEndTime());

        System.out.println("指定某月开始时间：" + LocalDateTimeUtils.getMonthStartTime(localDateTime));
        System.out.println("指定某月结束时间：" + LocalDateTimeUtils.getMonthEndTime(localDateTime));

        System.out.println("当月开始时间：" + LocalDateTimeUtils.getThisMonthStartTime());
        System.out.println("当月结束时间：" + LocalDateTimeUtils.getThisMonthEndTime());

        LocalDateTime lastYear = LocalDateTimeUtils.parseDateTime("2018-05-12 20:11:33");
        System.out.println("指定某年开始时间：" + LocalDateTimeUtils.getYearStartTime(lastYear));
        System.out.println("指定某年结束时间：" + LocalDateTimeUtils.getYearEndTime(lastYear));

        System.out.println("当年开始时间：" + LocalDateTimeUtils.getThisYearStartTime());
        System.out.println("当年结束时间：" + LocalDateTimeUtils.getThisYearEndTime());
    }

    @Test
    public void plusTest() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("当前日期时间：" + nowDateTime);
        System.out.println("当前日期时间加一年：" + LocalDateTimeUtils.plus(nowDateTime, 1, ChronoUnit.YEARS));
        System.out.println("当前日期时间加一月：" + LocalDateTimeUtils.plus(nowDateTime, 1, ChronoUnit.MONTHS));
        System.out.println("当前日期时间加一天：" + LocalDateTimeUtils.plus(nowDateTime, 1, ChronoUnit.DAYS));
        System.out.println("当前日期时间加一小时：" + LocalDateTimeUtils.plus(nowDateTime, 1, ChronoUnit.HOURS));
        System.out.println("当前日期时间加一分钟：" + LocalDateTimeUtils.plus(nowDateTime, 1, ChronoUnit.MINUTES));
        System.out.println("当前日期时间加一秒钟：" + LocalDateTimeUtils.plus(nowDateTime, 1, ChronoUnit.SECONDS));
        System.out.println("当前日期时间加一毫秒：" + LocalDateTimeUtils.plus(nowDateTime, 1, ChronoUnit.MILLIS));
        System.out.println("当前日期时间加一星期：" + LocalDateTimeUtils.plus(nowDateTime, 1, ChronoUnit.WEEKS));

        LocalDate nowDate = LocalDate.now();
        System.out.println("当前日期：" + nowDate);
        System.out.println("当前日期加一年：" + LocalDateTimeUtils.plus(nowDate, 1, ChronoUnit.YEARS));
        System.out.println("当前日期加一月：" + LocalDateTimeUtils.plus(nowDate, 1, ChronoUnit.MONTHS));
        System.out.println("当前日期加一天：" + LocalDateTimeUtils.plus(nowDate, 1, ChronoUnit.DAYS));
        System.out.println("当前日期加一星期：" + LocalDateTimeUtils.plus(nowDate, 1, ChronoUnit.WEEKS));

        LocalTime nowTime = LocalTime.now();
        System.out.println("当前时间：" + nowTime);
        System.out.println("当前时间加一小时：" + LocalDateTimeUtils.plus(nowTime, 1, ChronoUnit.HOURS));
        System.out.println("当前时间加一分钟：" + LocalDateTimeUtils.plus(nowTime, 1, ChronoUnit.MINUTES));
        System.out.println("当前时间加一秒钟：" + LocalDateTimeUtils.plus(nowTime, 1, ChronoUnit.SECONDS));
        System.out.println("当前时间加一毫秒：" + LocalDateTimeUtils.plus(nowTime, 1, ChronoUnit.MILLIS));
    }

    @Test
    public void minusTest() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("当前日期时间：" + nowDateTime);
        System.out.println("当前日期时间减一年：" + LocalDateTimeUtils.minus(nowDateTime, 1, ChronoUnit.YEARS));
        System.out.println("当前日期时间减一月：" + LocalDateTimeUtils.minus(nowDateTime, 1, ChronoUnit.MONTHS));
        System.out.println("当前日期时间减一天：" + LocalDateTimeUtils.minus(nowDateTime, 1, ChronoUnit.DAYS));
        System.out.println("当前日期时间减一小时：" + LocalDateTimeUtils.minus(nowDateTime, 1, ChronoUnit.HOURS));
        System.out.println("当前日期时间减一分钟：" + LocalDateTimeUtils.minus(nowDateTime, 1, ChronoUnit.MINUTES));
        System.out.println("当前日期时间减一秒钟：" + LocalDateTimeUtils.minus(nowDateTime, 1, ChronoUnit.SECONDS));
        System.out.println("当前日期时间减一毫秒：" + LocalDateTimeUtils.minus(nowDateTime, 1, ChronoUnit.MILLIS));
        System.out.println("当前日期时间减一星期：" + LocalDateTimeUtils.minus(nowDateTime, 1, ChronoUnit.WEEKS));

        LocalDate nowDate = LocalDate.now();
        System.out.println("当前日期：" + nowDate);
        System.out.println("当前日期减一年：" + LocalDateTimeUtils.minus(nowDate, 1, ChronoUnit.YEARS));
        System.out.println("当前日期减一月：" + LocalDateTimeUtils.minus(nowDate, 1, ChronoUnit.MONTHS));
        System.out.println("当前日期减一天：" + LocalDateTimeUtils.minus(nowDate, 1, ChronoUnit.DAYS));
        System.out.println("当前日期减一星期：" + LocalDateTimeUtils.minus(nowDate, 1, ChronoUnit.WEEKS));

        LocalTime nowTime = LocalTime.now();
        System.out.println("当前时间：" + nowTime);
        System.out.println("当前时间减一小时：" + LocalDateTimeUtils.minus(nowTime, 1, ChronoUnit.HOURS));
        System.out.println("当前时间减一分钟：" + LocalDateTimeUtils.minus(nowTime, 1, ChronoUnit.MINUTES));
        System.out.println("当前时间减一秒钟：" + LocalDateTimeUtils.minus(nowTime, 1, ChronoUnit.SECONDS));
        System.out.println("当前时间减一毫秒：" + LocalDateTimeUtils.minus(nowTime, 1, ChronoUnit.MILLIS));
    }

    @Test
    public void intervalTest() {
        LocalDateTime start = LocalDateTimeUtils.parseDateTimeMillis("2018-05-12 20:11:33.123");
        LocalDateTime end = LocalDateTimeUtils.parseDateTimeMillis("2019-05-12 20:11:33.123");
        System.out.println("相差年数：" + LocalDateTimeUtils.interval(start, end, ChronoUnit.YEARS));// 忽略时间
        System.out.println("相差月数：" + LocalDateTimeUtils.interval(start, end, ChronoUnit.MONTHS));// 忽略时间
        System.out.println("相差天数：" + LocalDateTimeUtils.interval(start, end, ChronoUnit.DAYS));
        System.out.println("相差小时数：" + LocalDateTimeUtils.interval(start, end, ChronoUnit.HOURS));
        System.out.println("相差分钟数：" + LocalDateTimeUtils.interval(start, end, ChronoUnit.MINUTES));
        System.out.println("相差秒钟数：" + LocalDateTimeUtils.interval(start, end, ChronoUnit.SECONDS));
        System.out.println("相差毫秒数：" + LocalDateTimeUtils.interval(start, end, ChronoUnit.MILLIS));
        System.out.println("相差星期数：" + LocalDateTimeUtils.interval(start, end, ChronoUnit.WEEKS));

        LocalDate start2 = LocalDateTimeUtils.parseDate("2019-05-12");
        LocalDate end2 = LocalDateTimeUtils.parseDate("2020-05-12");
        System.out.println("相差年数：" + LocalDateTimeUtils.interval(start2, end2, ChronoUnit.YEARS));
        System.out.println("相差月数：" + LocalDateTimeUtils.interval(start2, end2, ChronoUnit.MONTHS));
        System.out.println("相差天数：" + LocalDateTimeUtils.interval(start2, end2, ChronoUnit.DAYS));
        System.out.println("相差星期数：" + LocalDateTimeUtils.interval(start2, end2, ChronoUnit.WEEKS));
    }

    @Test
    public void compareTest() {
        LocalDateTime source = LocalDateTimeUtils.parseDateTimeMillis("2018-05-12 20:11:33.123");
        LocalDateTime target = LocalDateTimeUtils.parseDateTimeMillis("2019-05-12 20:11:33.123");
        System.out.println("比较日期时间：" + LocalDateTimeUtils.compare(source, target));

        LocalDate source2 = LocalDateTimeUtils.parseDate("2021-05-12");
        LocalDate target2 = LocalDateTimeUtils.parseDate("2020-05-12");
        System.out.println("比较日期：" + LocalDateTimeUtils.compare(source2, target2));

        LocalTime source3 = LocalDateTimeUtils.parseTimeMillis("20:11:33.123");
        LocalTime target3 = LocalDateTimeUtils.parseTimeMillis("20:11:33.123");
        System.out.println("比较时间：" + LocalDateTimeUtils.compare(source3, target3));
    }

    @Test
    public void betweenTest() {
        LocalDateTime start = LocalDateTimeUtils.parseDateTimeMillis("2018-05-12 20:11:33.123");
        LocalDateTime end = LocalDateTimeUtils.parseDateTimeMillis("2019-05-12 20:11:33.123");
        System.out.println("判断日期时间范围：" + LocalDateTimeUtils.between(LocalDateTime.now(), start, end));

        LocalDate start2 = LocalDateTimeUtils.parseDate("2019-06-12");
        LocalDate end2 = LocalDateTimeUtils.parseDate("2019-06-13");
        System.out.println("判断日期范围：" + LocalDateTimeUtils.between(LocalDate.now(), start2, end2));

        LocalTime start3 = LocalDateTimeUtils.parseTimeMillis("20:11:33.123");
        LocalTime end3 = LocalDateTimeUtils.parseTimeMillis("22:11:33.123");
        System.out.println("判断时间范围：" + LocalDateTimeUtils.between(LocalTime.now(), start3, end3));
    }

    @Test
    public void parseOriginalDateTimeTest() {
        String originalDateTime = "Fri Apr 24 22:50:23 CST 2020";
        LocalDateTime localDateTime = LocalDateTimeUtils.parseOriginalDateTime(originalDateTime);
        System.out.println(localDateTime);
    }
}
