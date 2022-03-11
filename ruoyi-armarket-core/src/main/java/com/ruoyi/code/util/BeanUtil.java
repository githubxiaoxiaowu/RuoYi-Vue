package com.ruoyi.code.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 17:47
 **/
public class BeanUtil extends BeanUtils {
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            if(pd.getReadMethod()!=null){
                Object srcValue = src.getPropertyValue(pd.getName());
                if (srcValue == null) emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target){
        copyProperties(src, target, getNullPropertyNames(src));
    }
}
