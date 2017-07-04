package com.leiwenjie.terse.util.bean;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leiwenjie.terse.util.ArrayUtil;
import com.leiwenjie.terse.util.StringUtil;

/**
 * bean validator公共类
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年7月4日 上午9:53:25
 */
public class BeanValidatorUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanValidatorUtil.class);

    public final static String UNKNOW_MESSAGE = "未知的校验异常";

    private static final Object singletionLock = new Object();

    /**
     * 参数校验器
     */
    private static Validator validator;

    /**
     * 对象验证
     * 
     * @param object
     *            需要验证的对象
     * 
     * @return String 校验通过返回空串，不通过返回具体信息
     */
    public static String validate(final Object object) {
        // 用于存放结果
        StringBuffer resultStr = new StringBuffer();
        Set<ConstraintViolation<Object>> constraintViolations = getInstance().validate(object); // 验证某个对象

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<Object> violation : constraintViolations) {
                final String columnName = violation.getPropertyPath().toString();
                final String message = violation.getMessage();

                logger.debug("column : " + columnName + " , message : [" + message + " ]");

                // 如果未指定message，切无默认message，就是用自定义的默认值
                resultStr.append(columnName).append(" : ");
                resultStr.append(StringUtil.isNotEmpty(message) ? message : UNKNOW_MESSAGE).append("\r\n <br/>");
            }
        }
        return resultStr.toString();
    }

    /**
     * 校验部分属性
     *
     * @param object
     *            需要校验的对象
     * @param propertyNames
     *            需要校验的属性
     * @return String 校验通过返回空串，不通过返回具体信息
     */
    public static String validate(final Object object, final String[] propertyNames) {
        StringBuffer resultStr = new StringBuffer();

        Set<ConstraintViolation<Object>> constraintViolations = getInstance().validate(object); // 验证某个对象

        if (constraintViolations.size() > 0) {
            for (ConstraintViolation<Object> violation : constraintViolations) {
                final String columnName = violation.getPropertyPath().toString();

                if (ArrayUtil.contains(propertyNames, columnName) != -1) {
                    final String message = violation.getMessage();

                    logger.debug("column : " + columnName + " , message : [ " + message + " ]");
                    // 如果未指定message，切无默认message，就用自定义的默认值
                    resultStr.append(columnName).append(" : ");
                    resultStr.append(StringUtil.isNotEmpty(message) ? message : UNKNOW_MESSAGE).append("\r\n <br/>");
                }
            }
        }
        return resultStr.toString();
    }

    private static Validator getInstance() {
        synchronized (singletionLock) {
            if (validator == null) {
                logger.debug("--------initialization bean validator--------------");
                validator = Validation.buildDefaultValidatorFactory().getValidator();
            }
        }
        return validator;
    }

}
