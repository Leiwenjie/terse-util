package com.leiwenjie.terse.util;

import org.junit.Assert;
import org.junit.Test;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

/**
 * test for StringUtil
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年5月8日 下午10:53:53
 */
public class StringUtilTest {

    public static final int threadCount = 20; // 线程数

    @Test
    public void multiThreadTest() {
        TestRunnable runner = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                valiEmptyTest();
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
    public void valiEmptyTest() {
        final String nullStr = null;
        final String emptyStr = "";
        final String blankStr = "   ";
        final String normalStr = "hello word!";

        Assert.assertTrue(StringUtil.isEmpty(nullStr) == true);
        Assert.assertTrue(StringUtil.isEmpty(emptyStr) == true);
        Assert.assertTrue(StringUtil.isEmpty(blankStr) == true);
        Assert.assertTrue(StringUtil.isEmpty(normalStr) == false);

        Assert.assertTrue(StringUtil.isNotEmpty(nullStr) == false);
        Assert.assertTrue(StringUtil.isNotEmpty(emptyStr) == false);
        Assert.assertTrue(StringUtil.isNotEmpty(blankStr) == false);
        Assert.assertTrue(StringUtil.isNotEmpty(normalStr) == true);
    }
}
