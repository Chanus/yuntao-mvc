/*
 * Copyright (c) 2018, Chanus and/or its affiliates. All rights reserved.
 */
package pers.chanus.yuntao.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 字符串工具类
 *
 * @author Chanus
 * @date 2018-08-30 15:00:50
 * @since 0.0.1
 */
public class StringUtils {
    public static final char C_SPACE = CharUtils.SPACE;
    public static final char C_TAB = CharUtils.TAB;
    public static final char C_DOT = CharUtils.DOT;
    public static final char C_SLASH = CharUtils.SLASH;
    public static final char C_BACKSLASH = CharUtils.BACKSLASH;
    public static final char C_CR = CharUtils.CR;
    public static final char C_LF = CharUtils.LF;
    public static final char C_UNDERLINE = CharUtils.UNDERLINE;
    public static final char C_COMMA = CharUtils.COMMA;
    public static final char C_DELIM_START = CharUtils.DELIM_START;
    public static final char C_DELIM_END = CharUtils.DELIM_END;
    public static final char C_BRACKET_START = CharUtils.BRACKET_START;
    public static final char C_BRACKET_END = CharUtils.BRACKET_END;
    public static final char C_COLON = CharUtils.COLON;

    public static final String SPACE = " ";
    public static final String TAB = "	";
    public static final String DOT = ".";
    public static final String DOUBLE_DOT = "..";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String EMPTY = "";
    public static final String NULL = "null";
    public static final String CR = "\r";
    public static final String LF = "\n";
    public static final String CRLF = "\r\n";
    public static final String UNDERLINE = "_";
    public static final String DASHED = "-";
    public static final String COMMA = ",";
    public static final String DELIM_START = "{";
    public static final String DELIM_END = "}";
    public static final String BRACKET_START = "[";
    public static final String BRACKET_END = "]";
    public static final String COLON = ":";

    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp;";
    public static final String HTML_QUOTE = "&quot;";
    public static final String HTML_APOS = "&apos;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";

    public static final String EMPTY_JSON = "{}";

    /**
     * 字符串编码格式
     *
     * @since 0.0.7
     */
    public static final String CHARSET = "UTF-8";

    /**
     * 判断字符串是否为空或空白，空白的定义如下：<br>
     * <pre>
     *     1、为null
     *     2、为不可见字符（如空格）
     *     3、""
     * </pre>
     *
     * @param s 源字符串
     * @return {@code true} 字符串为空；{@code false} 字符串不为空
     * @since 0.0.1
     */
    public static boolean isBlank(final String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * 判断字符串是否为空或空白，空白的定义如下：<br>
     * <pre>
     *     1、为null
     *     2、为不可见字符（如空格）
     *     3、""
     * </pre>
     *
     * @param s 源字符串
     * @return {@code true} 字符串为空；{@code false} 字符串不为空
     * @since 0.1.8
     */
    public static boolean isBlank(CharSequence s) {
        int length;

        if ((s == null) || ((length = s.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (!CharUtils.isBlankChar(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串是否为非空或非空白，空白的定义如下：<br>
     * <pre>
     *     1、为null
     *     2、为不可见字符（如空格）
     *     3、""
     * </pre>
     *
     * @param s 源字符串
     * @return {@code true} 字符串不为空；{@code false} 字符串为空
     * @since 0.0.1
     */
    public static boolean isNotBlank(final String s) {
        return !isBlank(s);
    }

    /**
     * 判断字符串是否为非空或非空白，空白的定义如下：<br>
     * <pre>
     *     1、为null
     *     2、为不可见字符（如空格）
     *     3、""
     * </pre>
     *
     * @param s 源字符串
     * @return {@code true} 字符串不为空；{@code false} 字符串为空
     * @since 0.0.1
     */
    public static boolean isNotBlank(CharSequence s) {
        return !isBlank(s);
    }

    /**
     * 字符串是否为空，空的定义如下:
     * <pre>
     *     1、为null
     *     2、为""
     * </pre>
     *
     * @param s 源字符串
     * @return {@code true} 字符串为空；{@code false} 字符串不为空
     * @since 0.1.8
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 字符串是否不为空，空的定义如下:
     * <pre>
     *     1、为null
     *     2、为""
     * </pre>
     *
     * @param s 源字符串
     * @return {@code true} 字符串不为空；{@code false} 字符串为空
     * @since 0.1.8
     */
    public static boolean isNotEmpty(CharSequence s) {
        return !isEmpty(s);
    }

    /**
     * 去除字符串首尾的空格
     *
     * @param s 源字符串
     * @return 去除首尾空格后的字符串
     * @since 0.0.1
     */
    public static String trim(String s) {
        return isBlank(s) ? null : s.trim();
    }

    /**
     * 判断字符串是否包含某字符串
     *
     * @param s 源字符串
     * @param t 目标字符串
     * @return {@code true} s包含字符串t；{@code false} s不包含字符串t
     * @since 0.0.1
     */
    public static boolean contains(String s, String t) {
        return isNotBlank(s) && isNotBlank(t) && s.contains(t);
    }

    /**
     * 安全的比较两个字符串是否相等
     *
     * @param s 字符串1
     * @param t 字符串2
     * @return {@code true} 两个字符串相等；{@code false} 两个字符串不相等
     * @since 0.0.1
     */
    public static boolean equals(String s, String t) {
        return Objects.equals(s, t);
    }

    /**
     * 安全的比较两个字符串是否相等，忽略大小写
     *
     * @param s 字符串1
     * @param t 字符串2
     * @return {@code true} 两个字符串相等；{@code false} 两个字符串不相等
     * @since 0.0.3
     */
    public static boolean equalsIgnoreCase(String s, String t) {
        return s == null ? t == null : s.equalsIgnoreCase(t);
    }

    /**
     * 判断字符串是否为纯数字
     *
     * @param s 源字符串
     * @return {@code true} 字符串是纯数字；{@code false} 字符串不是纯数字
     * @since 0.0.1
     */
    public static boolean isNumeric(String s) {
        return isNotBlank(s) && Pattern.compile("[0-9]*").matcher(s).matches();
    }

    /**
     * 判断字符串是否为数字
     *
     * @param s 源字符串
     * @return {@code true} 字符串是数字；{@code false} 字符串不是数字
     * @since 0.0.4
     */
    public static boolean isNumber(String s) {
        return isNotBlank(s) && Pattern.compile("^([+|-]?0\\.\\d+)|^([+|-]?[1-9]\\d*(\\.\\d+)?)$").matcher(s).matches();
    }

    /**
     * 将布尔型数据转换为字符串
     *
     * @param b 布尔型数据
     * @return b为{@code true}时返回"1"，b为空或者{@code false}时返回"0"
     * @since 0.1.7
     */
    public static String boolean2String(Boolean b) {
        return (b != null && b) ? "1" : "0";
    }

    /**
     * 验证字符串是否由数字和分隔符组成的
     *
     * @param str       由指定分隔符分割的字符串，如1,02,3,4,55，不能为空
     * @param count     分隔符分割的数字个数，若为null则不验证
     * @param length    由分隔符分割的数字最大长度，若为null则不验证
     * @param min       由分隔符分割的数字最小值，null不验证
     * @param max       由分隔符分割的数字最大值，null不验证
     * @param separator 分隔符，不能为空
     * @param fixed     是否固定长度length，true则固定，false不固定
     * @param ignore0   是否忽略0，若为null则不验证，true则02合法，false则02不合法
     * @param repeat    是否允许重复，true则允许，false则不允许
     * @return {@code true} 字符串符合指定格式；{@code false} 字符串不符合指定格式
     * @since 0.0.1
     */
    public static boolean isNumericWithSeparator(String str, Integer count, Integer length, Integer min, Integer max, String separator, boolean fixed, Boolean ignore0, boolean repeat) {
        if (isBlank(str) || isBlank(separator))
            return false;
        if (!isNumeric(str.substring(0, 1)) || !isNumeric(str.substring(str.length() - 1)))// 第一个或最后一个字符不为数字，如,1,02,3,4,55,
            return false;
        String[] arr = str.split(separator);
        if (count != null && arr.length != count)// 分隔符分割的数字个数不匹配
            return false;
        Set<String> set = new HashSet<>();
        if (length == null) {// 不限制数字长度
            for (String s : arr) {
                if (!isNumeric(s))
                    return false;
                if (ignore0 != null && !ignore0 && s.charAt(0) == '0')
                    return false;
                if (min != null && Integer.parseInt(s) < min)
                    return false;
                if (max != null && Integer.parseInt(s) > max)
                    return false;
                set.add(s);
            }
        } else {
            for (String s : arr) {
                if (!isNumeric(s) || s.length() > length)// 非数字或数字长度大于最大长度
                    return false;
                if (fixed && s.length() != length)// 数字固定长度length
                    return false;
                if (ignore0 != null && !ignore0 && s.length() > 1 && s.charAt(0) == '0')
                    return false;
                if (min != null && Integer.parseInt(s) < min)
                    return false;
                if (max != null && Integer.parseInt(s) > max)
                    return false;
                set.add(s);
            }
        }
        return repeat || (set.size() == arr.length);
    }

    /**
     * 压缩字符串
     *
     * @param s 待压缩的字符串内容
     * @return 压缩后的字节数组
     * @since 0.0.1
     */
    public static byte[] compress(String s) {
        if (isBlank(s))
            return null;

        ByteArrayOutputStream baos = null;
        GZIPOutputStream gzos = null;
        try {
            baos = new ByteArrayOutputStream();
            gzos = new GZIPOutputStream(baos);
            gzos.write(s.getBytes(CHARSET));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (gzos != null) {
                try {
                    gzos.finish();
                    gzos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }

    /**
     * 解压流
     *
     * @param b 待解压的内容
     * @return 解压后的字节数组
     * @since 0.0.1
     */
    public static byte[] decompress(byte[] b) {
        if (b == null || b.length == 0)
            return null;

        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = null;
        GZIPInputStream gzis = null;
        try {
            baos = new ByteArrayOutputStream();
            bais = new ByteArrayInputStream(b);
            gzis = new GZIPInputStream(bais);
            byte[] buffer = new byte[1024];
            int i;
            while ((i = gzis.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (gzis != null) {
                try {
                    gzis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.flush();
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }

    /**
     * 将源字符串重复生成{@code num}次组成新的字符串
     *
     * @param s   源字符串
     * @param num 重复生成次数
     * @return 返回已生成的重复字符串
     * @since 0.0.1
     */
    public static String repeat(final String s, final int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++)
            sb.append(s);
        return sb.toString();
    }

    /**
     * 将源字符串指定位置的字符替换成指定个数的目标字符串
     *
     * @param source 源字符串
     * @param target 目标字符串
     * @param begin  开始替换的位置
     * @param end    结束替换的位置
     * @param num    目标字符串重复次数
     * @return 返回替换后的字符串
     * @since 0.0.1
     */
    public static String replace(final String source, final String target, final int begin, final int end, final int num) {
        return source.substring(0, begin) + repeat(target, num) + source.substring(end);
    }

    /**
     * 将源字符串指定位置的字符替换成"*"
     *
     * @param source 源字符串
     * @param begin  开始替换的位置
     * @param end    结束替换的位置
     * @return 返回替换后的字符串
     * @since 0.0.1
     */
    public static String replace(final String source, final int begin, final int end) {
        return source.substring(0, begin) + repeat("*", end - begin) + source.substring(end);
    }

    /**
     * 将字符串的首字母转为大写
     *
     * @param str 字符串
     * @return 首字母转为大写后的字符串
     * @since 0.0.3
     */
    public static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        return Character.toTitleCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 将字符串的首字母转为小写
     *
     * @param str 字符串
     * @return 首字母转为小写后的字符串
     * @since 0.0.3
     */
    public static String uncapitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 字符串转Unicode码
     *
     * @param string 待转换的字符串
     * @return 转换后的Unicode码
     * @since 0.0.5
     */
    public static String string2Unicode(String string) {
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            unicode.append("\\u").append(Integer.toHexString(string.charAt(i)));
        }

        return unicode.toString();
    }

    /**
     * Unicode码转字符串
     *
     * @param unicode 待转换的Unicode码
     * @return 转换后的字符串
     * @since 0.0.5
     */
    public static String unicode2String(String unicode) {
        StringBuilder string = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            string.append((char) Integer.parseInt(hex[i], 16));
        }

        return string.toString();
    }
}
