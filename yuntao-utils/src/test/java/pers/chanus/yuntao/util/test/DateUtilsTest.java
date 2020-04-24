/*
 * Copyright (c), 2019-2019, Chanus and/or its affiliates. All rights reserved.
 * FileName: DateUtilsTest
 * Author:   Chanus
 * Date:     2019-06-11 18:46
 * Description: 工具类测试
 * History:
 * <author>          <time>          <version>          <desc>
 */
package pers.chanus.yuntao.util.test;

import org.junit.Test;
import pers.chanus.yuntao.util.DateUtils;

import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * DateUtils工具类测试
 *
 * @author Chanus
 * @date 2019-06-11 18:46
 * @since 0.1.2
 */
public class DateUtilsTest {
    @Test
    public void formatTest() {
        Date date = new Date();
        System.out.println(DateUtils.formatDate(date, "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"));
        System.out.println(DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(DateUtils.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.formatDate(date, "yyyy-MM-dd"));
        System.out.println(DateUtils.formatDate(date, "HH:mm:ss"));

        System.out.println(DateUtils.formatDate(date));

        System.out.println(DateUtils.formatDateTime(date));
    }

    @Test
    public void parseTest() {
        System.out.println(DateUtils.parseDate("2019年06月13日 22时47分33秒547毫秒", "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒"));
        System.out.println(DateUtils.parseDate("2019-06-13 22:47:33.547", "yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(DateUtils.parseDate("2019-06-13 22:47:33", "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.parseDate("2019-06-13", "yyyy-MM-dd"));
        System.out.println(DateUtils.parseDate("22:47:33", "HH:mm:ss"));

        System.out.println(DateUtils.parseDate("2019-06-13"));

        System.out.println(DateUtils.parseDateTime("2019-06-13 22:47:33"));
    }

    @Test
    public void getDateTodayTest() {
        System.out.println("当天日期：" + DateUtils.getDateToday());
    }

    @Test
    public void getDateTimeTodayTest() {
        System.out.println("当天日期时间：" + DateUtils.getDateTimeToday());
    }

    @Test
    public void getDateYesterdayTest() {
        System.out.println("昨天日期：" + DateUtils.getDateYesterday());
    }

    @Test
    public void getDateTimeYesterdayTest() {
        System.out.println("昨天日期时间：" + DateUtils.getDateTimeYesterday());
    }

    @Test
    public void getDateTomorrowTest() {
        System.out.println("明天日期：" + DateUtils.getDateTomorrow());
    }

    @Test
    public void getDateTimeTomorrowTest() {
        System.out.println("明天日期时间：" + DateUtils.getDateTimeTomorrow());
    }

    @Test
    public void getStartDateTodayTest() {
        System.out.println("今天开始时间：" + DateUtils.getStartDateToday());
    }

    @Test
    public void getEndDateTodayTest() {
        System.out.println("今天结束时间：" + DateUtils.getEndDateToday());
    }

    @Test
    public void getDateTimeBeforeYearTest() {
        Date date = new Date();
        System.out.println("当前时间：" + date);
        System.out.println("两年前：" + DateUtils.getDateTimeBeforeYear(date, -2));
        System.out.println("两年后：" + DateUtils.getDateTimeBeforeYear(date, 2));
    }

    @Test
    public void getDateTimeBeforeMonthTest() {
        Date date = new Date();
        System.out.println("当前时间：" + date);
        System.out.println("两月前：" + DateUtils.getDateTimeBeforeMonth(date, -2));
        System.out.println("两月后：" + DateUtils.getDateTimeBeforeMonth(date, 2));
    }

    @Test
    public void getDateTimeBeforeDayTest() {
        Date date = new Date();
        System.out.println("当前时间：" + date);
        System.out.println("两天前：" + DateUtils.getDateTimeBeforeDay(date, -2));
        System.out.println("两天后：" + DateUtils.getDateTimeBeforeDay(date, 2));
    }

    @Test
    public void getDateTimeBeforeHourTest() {
        Date date = new Date();
        System.out.println("当前时间：" + date);
        System.out.println("两小时前：" + DateUtils.getDateTimeBeforeHour(date, -2));
        System.out.println("两小时后：" + DateUtils.getDateTimeBeforeHour(date, 2));
    }

    @Test
    public void getDateTimeBeforeMinuteTest() {
        Date date = new Date();
        System.out.println("当前时间：" + date);
        System.out.println("两分钟前：" + DateUtils.getDateTimeBeforeMinute(date, -2));
        System.out.println("两分钟后：" + DateUtils.getDateTimeBeforeMinute(date, 2));
    }

    @Test
    public void getDateTimeBeforeSecondTest() {
        Date date = new Date();
        System.out.println("当前时间：" + date);
        System.out.println("两秒钟前：" + DateUtils.getDateTimeBeforeSecond(date, -2));
        System.out.println("两秒钟后：" + DateUtils.getDateTimeBeforeSecond(date, 2));
    }

    @Test
    public void getDiffTimeInMillisTest() {
        System.out.println(DateUtils.getDiffTimeInMillis(DateUtils.parseDateTime("2019-06-13 22:48:33"), DateUtils.parseDateTime("2019-06-13 22:47:33")));
    }

    @Test
    public void getDiffTimeInYearsTest() {
        System.out.println(DateUtils.getDiffTimeInYears(DateUtils.parseDateTime("2020-06-13 22:47:33"), DateUtils.parseDateTime("2019-06-13 22:47:33")));
    }

    @Test
    public void getDiffTimeInMonthsTest() {
        System.out.println(DateUtils.getDiffTimeInMonths(DateUtils.parseDateTime("2020-06-13 22:47:33"), DateUtils.parseDateTime("2019-06-13 22:47:33")));
    }

    @Test
    public void getDiffTimeInDaysTest() {
        System.out.println(DateUtils.getDiffTimeInDays(DateUtils.parseDateTime("2018-06-13 22:47:33"), DateUtils.parseDateTime("2019-06-13 22:47:33")));
    }

    @Test
    public void compareTest() {
        System.out.println(DateUtils.compare(DateUtils.parseDateTime("2019-06-13 12:47:33"), DateUtils.parseDateTime("2019-06-13 22:47:33")));
        System.out.println(DateUtils.compare(DateUtils.parseDateTime("2019-06-13 23:47:33"), DateUtils.parseDateTime("2019-06-13 22:47:33")));
        System.out.println(DateUtils.compare(DateUtils.parseDateTime("2019-06-13 22:47:33"), DateUtils.parseDateTime("2019-06-13 22:47:33")));

        System.out.println(DateUtils.compare("2019-06-13 12:47:33", "2019-06-13 22:47:33", "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.compare("2019-06-13 23:47:33", "2019-06-13 22:47:33", "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.compare("2019-06-13 22:47:33", "2019-06-13 22:47:33", "yyyy-MM-dd HH:mm:ss"));

        System.out.println(DateUtils.compare("2019-06-13 12:47:33", "2019-06-13 22:47:33", "yyyy-MM-dd"));
        System.out.println(DateUtils.compare("2019-06-13 23:47:33", "2019-06-13 22:47:33", "yyyy-MM-dd"));
        System.out.println(DateUtils.compare("2019-06-13 22:47:33", "2019-06-13 22:47:33", "yyyy-MM-dd"));
    }

    @Test
    public void betweenTest() {
        Date date = DateUtils.parseDateTime("2019-06-13 22:47:33");
        System.out.println(DateUtils.between(date, DateUtils.parseDateTime("2019-06-13 12:47:33"), DateUtils.parseDateTime("2019-06-13 22:47:33")));
        System.out.println(DateUtils.between(date, DateUtils.parseDateTime("2019-06-13 22:47:33"), DateUtils.parseDateTime("2019-06-14 22:47:33")));
        System.out.println(DateUtils.between(date, DateUtils.parseDateTime("2019-06-12 22:47:33"), DateUtils.parseDateTime("2019-06-13 22:47:32")));
    }

    @Test
    public void convertDateByTimeZoneTest() {
        Date date = DateUtils.parseDateTime("2019-06-13 22:47:33");
        System.out.println(DateUtils.convertDateByTimeZone(date, TimeZone.getDefault(), TimeZone.getTimeZone("Asia/Shanghai")));
        System.out.println(DateUtils.convertDateByTimeZone(date, TimeZone.getTimeZone("Asia/Shanghai"), TimeZone.getDefault()));
        System.out.println(DateUtils.convertDateByTimeZone(date, TimeZone.getTimeZone(ZoneId.of("GMT+10:00")), TimeZone.getTimeZone(ZoneId.of("GMT+12:00"))));
    }

    @Test
    public void parseCSTTest() throws ParseException {
        String originalDateTime = "Fri Apr 24 22:50:23 CST 2020";
        System.out.println(DateUtils.parseOriginalDateTime(originalDateTime));
    }
}
