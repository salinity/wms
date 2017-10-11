package com.salinity.wms.common.untils;

import java.util.ArrayList;
import java.util.List;

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

}
