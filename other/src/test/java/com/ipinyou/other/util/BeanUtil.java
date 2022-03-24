package com.ipinyou.other.util;


import org.apache.commons.lang3.StringUtils;

import java.time.ZonedDateTime;

public class BeanUtil {

    /**
     * 把String类型转为相应的对象
     */
    @SuppressWarnings("all")
    public static <T> T turnToObject(String str, Class<T> tClass) {
        if (StringUtils.isBlank(str) || tClass == null) {
            return null;
        }
        if (tClass == String.class) {
            return (T) str;
        } else if (tClass == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (tClass == ZonedDateTime.class) {
            return (T) ZonedDateTime.parse(str);
        } else {
            throw new RuntimeException("没有对应的转换类型");
        }
    }

}
