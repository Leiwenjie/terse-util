package com.leiwenjie.terse.util;

/**
 * ArrayUtil类
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年7月4日 上午10:57:08
 */
public class ArrayUtil {

    /**
     * 数组中是否包含targetValue
     *
     * @param array
     * @param targetValue
     * @return int -1表示不包含,否则返回targetValue在array中首次出现的下标位置
     */
    public static <T> int contains(T[] array, Object targetValue) {
        if (array == null) {
            return -1;
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(targetValue)) {
                    return i;
                }
            }
            return -1;
        }
    }
}
