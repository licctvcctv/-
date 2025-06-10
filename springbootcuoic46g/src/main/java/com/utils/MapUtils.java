
package com.utils;

import cn.hutool.core.date.DateUtil;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Map工具类
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }


    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T obj = clazz.newInstance();
        for (Entry<String, Object> entry : map.entrySet()) {
            Field field = null;
            try {
                field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                if (field.getType() == Date.class && entry.getValue() != null) {
                    field.set(obj, DateUtil.parseDate(entry.getValue().toString()));
                } else {
                    field.set(obj, entry.getValue());
                }
            } catch (NoSuchFieldException ignored) {
            }
        }
        return obj;
    }
}

