package com.kyn.springbatch_study.common.utils;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author Kangyanan
 * @Description: 日期工具类
 * @date 2021/2/26
 */
public class DateUtil {

    private final static String pattern1 = "yyyyMMdd";
    private final static String pattern2 = "yyyy-MM-dd";
    private final static String pattern3 = "yyyy-MM-dd HH:mm:ss";
    private final static String pattern4 = "yyyyMMddHHmmss";
    private final static String pattern5 = "yyyyMMddHHmmssSSS";

    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurrentDateTime() {
        return DateTime.now().toDate();
    }
}
