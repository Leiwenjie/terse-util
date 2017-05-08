package com.leiwenjie.terse.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * test for StringUtil
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年5月8日 下午10:53:53
 */
public class StringUtilTest {

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
