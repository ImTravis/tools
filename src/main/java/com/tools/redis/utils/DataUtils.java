package com.tools.redis.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xcc.
 * @data 2019/2/11.
 * @time 15:13.
 */
public class DataUtils {
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)
            throws Exception {
        if (map == null)
            return null;
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }

    public static Map<String, Object> Obj2Map(Object obj) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == Date.class) {
                    field.setAccessible(true);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if(field.get(obj)!=null){
                        map.put(field.getName(), sdf.format(field.get(obj)));
                    }else{
                        map.put(field.getName(), "");
                    }
                } else if (field.getType() == BigDecimal.class || field.getType() == int.class) {
                    field.setAccessible(true);
                    if(field.get(obj)!=null){
                        map.put(field.getName(),field.get(obj).toString());
                    }else{
                        map.put(field.getName(), "");
                    }
                } else {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(obj));
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object map2Obj(Map<String, Object> map, Class<?> clz) throws Exception {
        Object obj = clz.newInstance();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

}
