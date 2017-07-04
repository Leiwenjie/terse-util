package com.leiwenjie.terse.util.bean;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

/**
 * test for BeanValidatorUtil
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年7月3日 下午1:35:48
 */
public class BeanValidatorUtilTest {

    static final Logger logger = LoggerFactory.getLogger(BeanValidatorUtilTest.class);
    public static final int threadCount = 20; // 线程数

    @Test
    public void multiThreadTest() {
        TestRunnable runner = new TestRunnable() {
            @Override
            public void runTest() throws Throwable {
                beanValidatorUtilTest();
                beanValidatorUtilTest1();
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
    public void beanValidatorUtilTest() {
        TestBean test1 = new TestBean();
        test1.setName("张三");
        test1.setAge(150);
        test1.setSex(11);

        logger.debug(BeanValidatorUtil.validate(test1));
        Assert.assertTrue(!"".equals(BeanValidatorUtil.validate(test1)));
    }

    @Test
    public void beanValidatorUtilTest1() {
        TestBean test2 = new TestBean();
        test2.setName("李四");
        test2.setAge(10);

        logger.debug(BeanValidatorUtil.validate(test2, new String[] { "name", "age" }));
        Assert.assertTrue("".equals(BeanValidatorUtil.validate(test2, new String[] { "name", "age" })));
    }
}
