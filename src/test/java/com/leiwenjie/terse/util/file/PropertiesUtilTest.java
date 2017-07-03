package com.leiwenjie.terse.util.file;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

/**
 * test for PropertiesUtil
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年5月11日 下午9:22:24
 */
public class PropertiesUtilTest {

    public static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static final int threadCount = 10; // 线程数

    // @Test
    public void multiThreadTest() {
        TestRunnable runner = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                readPropertiesTest();
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
    public void readPropertiesTest() {
        Map<String, String> props = PropertiesUtil.getAllProperties("/log4j");
        System.out.println(props.size());
        Assert.assertTrue(props.size() > 0);

    }
}
