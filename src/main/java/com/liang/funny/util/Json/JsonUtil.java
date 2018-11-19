package com.liang.funny.util.Json;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JsonUtil {

    private final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static SerializeConfig CONFIG = new SerializeConfig();
    private static SimpleDateFormatSerializer formatSerializer = new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss");
    private static final SerializerFeature[] FEATURES;

    static {
        FEATURES = new SerializerFeature[]{
                SerializerFeature.WriteMapNullValue,  //是否输出值为null的字段,默认为false
                SerializerFeature.WriteNullListAsEmpty, //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullNumberAsZero, //数值字段如果为null,输出为0,而非null
                SerializerFeature.WriteNullBooleanAsFalse, //Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteNullStringAsEmpty}; //字符类型字段如果为null,输出为”“,而非null
    }

    /**
     * Object 转换成字符串
     *
     * @param object
     * @return
     */
    public static String toJSONFeaturesString(Object object) {
        return JSON.toJSONString(object, CONFIG, FEATURES);
    }

    /**
     * Object 转换成字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, CONFIG);
    }

    public static Object toBean(String text) {
        return JSON.parse(text);
    }


    /**
     * json字符串转化为map
     *
     * @param s
     * @return
     */
    public static Map stringToCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * 将map转化为string
     *
     * @param m
     * @return
     */
    public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     * 用fastjson 将json字符串解析为一个 JavaBean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T getJson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> getArrayJson(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getArrayJson(String jsonString) {
        List<T> list = new ArrayList<T>();
        try {
            list = (List<T>) JSON.parseArray(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * json字符串转类对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseToClass(String jsonStr, Class<?> clazz) {
        T javaObject = (T)JSON.toJavaObject(JSON.parseObject(jsonStr), clazz);
        return javaObject;
    }

    /**
     * 类对象转json字符串
     * @param object
     * @return
     */
    public static String parseToJSON(Object object) {
        return JSON.toJSONString(object, configMapping(), new SerializerFeature[0]);
    }

    /**
     * 类对象转json字符串UNICODE
     * @param object
     * @return
     */
    public static String parseUnicodeJSON(Object object) {
        return JSON.toJSONString(object, new SerializerFeature[]{SerializerFeature.BrowserCompatible});
    }

    /**
     * JSON转字符
     * @param object
     * @param filter
     * @return
     */
    public static String parseJSONString(Object object, SimplePropertyPreFilter filter) {
        return JSON.toJSONString(object, filter, new SerializerFeature[0]);
    }

    /**
     * JSON转字符 带日期
     * @param object
     * @param formatDate
     * @return
     */
    public static String parseJSONString(Object object, String formatDate) {
        return JSON.toJSONString(object, configMapping(formatDate), new SerializerFeature[0]);
    }

    /**
     * 类对象 转list
     * @param jsonString
     * @param pojoClass
     * @return
     */
    public static List<?> getListJSONToJava(String jsonString, Class<?> pojoClass) {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<Object> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Object pojoValue = JSON.toJavaObject(jsonObject, pojoClass);
            list.add(pojoValue);
        }

        return list;
    }

    /**
     * json转map
     * @param jsonString
     * @return
     */
    public static Map<Object, Object> getMapJSON(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Map<Object, Object> parseJSONMap = new LinkedHashMap<>(jsonObject);
        return parseJSONMap;
    }

    private static SerializeConfig configMapping() {
        CONFIG.put(Date.class, formatSerializer);
        return CONFIG;
    }

    private static SerializeConfig configMapping(String format) {
        SerializeConfig mapping = new SerializeConfig();
        mapping.put(Date.class, new SimpleDateFormatSerializer(format));
        return mapping;
    }

    public static SimplePropertyPreFilter filterProperty(Class<?> className, String... param) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(className, param);
        return filter;
    }
}
