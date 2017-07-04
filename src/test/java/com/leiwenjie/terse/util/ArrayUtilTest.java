package com.leiwenjie.terse.util;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

/**
 * test for ArrayUtil
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年7月3日 下午1:35:48
 */
public class ArrayUtilTest {

    static final Logger logger = LoggerFactory.getLogger(DateUtilTest.class);
    public static final int threadCount = 20; // 线程数

    @Test
    public void multiThreadTest() {
        TestRunnable runner = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                intArrayUtilTest();
                strArrayUtilTest();
            }
        };

        TestRunnable[] trs = new TestRunnable[threadCount];
        for (int i = 0; i < threadCount; i++) {
            trs[i] = runner;
        }
        // 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);

        try {
            // 并发执行测试用例
            mttr.runTestRunnables();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Test
    public void intArrayUtilTest() {
        Integer[] intArray = { 1, -1, 2, 0, 3, 1 };
        Assert.assertTrue(ArrayUtil.contains(intArray, new Integer(1)) != -1);
        Assert.assertTrue(ArrayUtil.contains(intArray, new Integer(1111)) == -1);
    }

    @Test
    public void strArrayUtilTest() {
        String[] strArray = { "hello", "a", "b", "c" };
        Assert.assertTrue(ArrayUtil.contains(strArray, "hello") != -1);
        Assert.assertTrue(ArrayUtil.contains(strArray, "world") == -1);
    }
}
