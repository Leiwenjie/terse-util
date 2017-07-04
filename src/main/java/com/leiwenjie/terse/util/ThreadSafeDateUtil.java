package com.leiwenjie.terse.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程安全的日期公共类
 * 
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年5月10日 上午12:48:53
 */
public class ThreadSafeDateUtil {

    static final Logger logger = LoggerFactory.getLogger(ThreadSafeDateUtil.class);

    public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd";
    public static final String SIMPLE_DATE_TIME_PARRERN = "yyyy-MM-dd HH:mm:ss";
    public static final String SIMPLE_TIME_PARRERN = "HH:mm:ss";

    /*
     * 写锁
     */
    private final static Object writeLock = new Object();

    /*
     * 将DateFormat存放在ThreadLocal中
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> threadLocal_dateFormat = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern
     *            转换格式
     * @return SimpleDateFormat
     ** @throws
     */
    private static SimpleDateFormat getSimpleDateFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl_sdformat = threadLocal_dateFormat.get(pattern);
        if (tl_sdformat == null) {
            tl_sdformat = new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    logger.debug(
                            "init SimpleDateFormat, thread : " + Thread.currentThread() + " , pattern : " + pattern);
                    return new SimpleDateFormat(pattern);
                }
            };
            synchronized (writeLock) {
                if (threadLocal_dateFormat.get(pattern) == null) {
                    threadLocal_dateFormat.put(pattern, tl_sdformat);
                }
            }
        }
        return tl_sdformat.get();
    }

    /**
     * to see
     * 
     * @param date
     * @param pattern
     * @return String
     ** @throws
     */
    public static final String dateToString(Date date, String pattern) {
        return getSimpleDateFormat(pattern).format(date);
    }

    /**
     * to see
     *
     * @param date
     * @return String
     */
    public static final String dateToString(Date date) {
        return dateToString(date, SIMPLE_DATE_TIME_PARRERN);
    }

    /**
     * to see
     *
     * @param dateStr
     * @param pattern
     * @return Date 如果转换失败将返回null
     ** @throws
     */
    public static final Date stringToDate(String dateStr, String pattern) {
        Date targetDate = null;
        try {
            targetDate = getSimpleDateFormat(pattern).parse(dateStr);
        } catch (ParseException e) {
            logger.error("parse string to date error, source string :[ " + dateStr + " ], pattern :[ " + pattern + " ]",
                    e);
        }
        return targetDate;
    }

    /**
     * 获取当前时间
     *
     * @return Date 日期对象
     ** @throws
     */
    public static final Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取当前时间
     *
     * @param pattern
     * @return String
     ** @throws
     */
    public static final String getCurrentDateStr(String pattern) {
        return getSimpleDateFormat(pattern).format(getCurrentDate());
    }

    /**
     * 获取当前时间
     *
     * @return String 日期字符串对象
     ** @throws
     */
    public static final String getCurrentDateStr() {
        return getCurrentDateStr(SIMPLE_DATE_TIME_PARRERN);
    }

}
