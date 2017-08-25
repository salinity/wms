package com.salinity.wms.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtils {

    public static <T> T copyBeanPropertyUtils(Object source,Class<T> desc){
        T obj = null;
        try {
            obj = desc.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source,obj);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return obj;
    }

    public static <T> List<T> copyBeanListPropertyUtils(List<Object> sources, Class<T> desc){
        List<T> objs = null;
        try {
            if(!sources.isEmpty()){
                objs = new ArrayList<>();
                for(Object source : sources){
                    T obj = desc.newInstance();
                    org.springframework.beans.BeanUtils.copyProperties(source,obj);
                    objs.add(obj);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return objs;
    }


    /***
     * Map 转换为Bean
     * @param map
     * @param obj
     */
    public static void transMapToBean2(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> transBeanToMap(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!"class".equals(key)) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
