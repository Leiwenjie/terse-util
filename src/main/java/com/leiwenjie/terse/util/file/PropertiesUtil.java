package com.leiwenjie.terse.util.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * propertie util
 *
 * @author leiwenjie leiwenjie.cn@gmail.com
 * @version 1.0
 * @date 2017年5月11日 下午9:06:48
 */
public class PropertiesUtil {

    public static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    @SuppressWarnings("rawtypes")
    public final static Map<String, String> properties(InputStream in) {
        Map<String, String> map = new HashMap<String, String>();
        Properties pps = new Properties();
        try {
            pps.load(in);
        } catch (IOException e) {
            logger.error("load properties error:", e);
        }
        Enumeration en = pps.propertyNames();
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            map.put(strKey, strValue);
        }
        return map;
    }

    /**
     * 获取所有的配置集合
     *
     * @param filePath
     * @return Map<String,String>
     ** @throws
     */
    public static Map<String, String> getAllProperties(String filePath) {
        Map<String, String> map = new HashMap<String, String>();
        try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
            return properties(in);
        } catch (IOException e) {
            logger.error("load properties error");
        }
        return map;
    }

}
