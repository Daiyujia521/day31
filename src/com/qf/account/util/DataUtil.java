package com.qf.account.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataUtil {
    public static final SimpleDateFormat SDF= new SimpleDateFormat("yyyy-MM-dd");

    /**
     * util.Date对象转换成sql.Date对象的方法
     * @param utilDate 传入一个util.Date对象
     * @return  返回一个sql.Date对象
     */
    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
        //获取时间毫秒值
        long time = utilDate.getTime();
        //获取sql.Date对象
        return new java.sql.Date(time);
    }

    /**
     * sql.Date对象转换成util.Date对象的方法
     * @param sqlDate 传入一个sql.Date对象
     * @return 返回一个util.Date对象
     */
    public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate) {
        //将sql.Date对象转换成字符串
        String s = sqlDate.toString();
        java.util.Date utilData = null;
        try {
            //解析字符串
            utilData = SDF.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return utilData;
    }

    /**
     * 将字符串日期转换成sql.Date对象的方法
     * @param str 传入一个字符串日期
     * @return 返回一个sql.Date对象
     */
    public static java.sql.Date stringToSqlDate(String str) {
        java.util.Date utilDate = null;
        java.sql.Date sqlDate = null;
        try {
            //解析字符串
            utilDate = SDF.parse(str);
            //获取毫秒值
            long date = utilDate.getTime();
            //获取sql.Date对象
            sqlDate = new java.sql.Date(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    /**
     * 将sql.Date转换成字符串的方法
     * @param sqlDate 传入一个sql.Date时间对象
     * @return 返回一个字符串日期
     */
    public static String sqlDateToString(java.sql.Date sqlDate) {
        String str = sqlDate.toString();
        return str;
    }

    /**
     * 将字符串日期转换成util.Date时间对象的方法
     * @param str 传入一个字符串日期
     * @return 返回一个util.Date时间对象
     */
    public static java.util.Date stringToUtilDate(String str) {
        java.util.Date utilDate = null;
        try {
            utilDate = SDF.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return utilDate;
    }

    /**
     * 将util.Date时间对象转换成字符串日期的方法
     * @param utilDate 传入一个util.Date时间对象
     * @return 返回一个字符串日期
     */
    public static String utilDateToString(java.util.Date utilDate) {
        String str = SDF.format(utilDate);
        return str;
    }
}
