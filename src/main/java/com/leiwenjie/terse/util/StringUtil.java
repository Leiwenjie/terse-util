package com.leiwenjie.terse.util;

/**
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年5月8日 下午10:48:11
 */
public class StringUtil {

    /**
     * 判断传入的字符串对象是否为空，为null或者""都判定为true
     *
     * @param obj
     * @return boolean
     ** @throws
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
