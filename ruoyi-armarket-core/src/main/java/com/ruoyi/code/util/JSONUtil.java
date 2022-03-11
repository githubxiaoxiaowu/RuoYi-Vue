package com.ruoyi.code.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @program: com.spring.boot
 * @description:
 * @author: zhendong.wu
 * @create: 2019-04-20 17:02
 **/
public class JSONUtil {
    private static volatile ObjectMapper mapper = new ObjectMapper();
    static {
        // 属性为NULL 不序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //忽略没有的字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    private JSONUtil() {};

    public static ObjectMapper getInstance() {
        return mapper;
    }

    public static byte[] toJsonBytes(Object obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String writeValueAsString(Object obj) {
            return toJsonString(obj);
    }
    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null) return null;
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> T readValue(String json, Class<T> clazz) {
            return toObject(json, clazz);
    }
    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        if (json == null) return null;
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(byte[] json, Class<T> clazz) {
        if (json == null) return null;
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(byte[] json, TypeReference<T> typeReference) {
        if (json == null) return null;
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toJsonToObject(Object from, Class<T> clazz) {
        return JSONUtil.toObject(JSONUtil.toJsonString(from), clazz);
    }
}
