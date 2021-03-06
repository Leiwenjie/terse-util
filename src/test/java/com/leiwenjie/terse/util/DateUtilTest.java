package com.leiwenjie.terse.util;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

/**
 * test for DateUtil
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年7月3日 下午1:35:48
 */
public class DateUtilTest {

    static final Logger logger = LoggerFactory.getLogger(DateUtilTest.class);
    public static final int threadCount = 20; // 线程数

    @Test
    public void multiThreadTest() {
        TestRunnable runner = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                dateUtilTest();
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
    public void dateUtilTest() {
        Date date1 = ThreadSafeDateUtil.getCurrentDate();
        String date2 = ThreadSafeDateUtil.getCurrentDateStr();
        String date3 = ThreadSafeDateUtil.getCurrentDateStr(ThreadSafeDateUtil.SIMPLE_TIME_PARRERN);

        String date4 = ThreadSafeDateUtil.dateToString(date1);
        String date5 = ThreadSafeDateUtil.dateToString(date1, ThreadSafeDateUtil.SIMPLE_TIME_PARRERN);
        Date date6 = ThreadSafeDateUtil.stringToDate(date3, ThreadSafeDateUtil.SIMPLE_TIME_PARRERN);
        Date date7 = ThreadSafeDateUtil.stringToDate(date2, ThreadSafeDateUtil.SIMPLE_DATE_TIME_PARRERN);

        logger.debug(date1.toString());
        logger.debug(date2);
        logger.debug(date3);
        logger.debug(date4);
        logger.debug(date5);
        logger.debug(date6.toString());
        logger.debug(date7.toString());
    }
}
