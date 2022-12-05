package util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Slf4j
public class JacksonUtil {
    /**
     * 默认日期格式
     */
    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String TIME_PATTERN = "HH:mm:ss";

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 取消Date类型的时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 设置时间格式
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_PATTERN));

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        /** 序列化配置,针对java8 时间 **/
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_PATTERN)));

        /** 反序列化配置,针对java8 时间 **/
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_PATTERN)));

        /** 声明自定义模块,配置double类型序列化配置 **/
        SimpleModule module = new SimpleModule("DoubleSerializer", new Version(1, 0, 0, ""));
        // 注意Double和double需要分配配置
        module.addSerializer(Double.class, new NumberSerializers.DoubleSerializer(JsonParser.NumberType.DOUBLE.getClass()));
        module.addSerializer(double.class, new NumberSerializers.DoubleSerializer(JsonParser.NumberType.DOUBLE.getClass()));


        objectMapper.registerModule(javaTimeModule)
                .registerModule(module)
                .registerModule(new Jdk8Module())
                .registerModule(new ParameterNamesModule());
    }

    public static ObjectMapper getMapper() {
        return objectMapper;
    }

    public static String toJsonStr(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("[JacksonUtil]toJsonStr error:", e);
        }
        return null;
    }

    public static String toJSONStringWithPrettyPrinter(Object object) {
        // 注意此处不要校验
//        Assert.notNull(object, "转换的对象不能为空");
        String s = null;
        try {
            s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static <T> T readValue (String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("[JacksonUtil]readValue error, json={}, clazz={}", json, clazz, e);
        }
        return null;
    }

    public static JsonNode readTree(String json) {
        Assert.isTrue(StringUtils.isNotBlank(json), "JSON字符串不能为空");

        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    /**
     * 校验json字符串是否是合法的json格式
     * @param: [jsonStr]
     * @return: boolean
     * @author: lijian
     * @date: 2022/5/17 10:01
     */
    public static boolean isJSONValid(String jsonStr) {
        try {
            objectMapper.readTree(jsonStr);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     *
     * @param: [json, fieldName]
     * @return: java.lang.String
     * @author: lijian
     * @date: 2022/5/17 10:03
     */
    public static String getStringValue(String json, String fieldName) {
        Assert.isTrue(StringUtils.isNotBlank(json), "JSON字符串不能为空");
        Assert.hasLength(fieldName, "指定JSON字符串中的属性名字不能为空");
        JsonNode jsonNode = readTree(json);
        if (null != jsonNode) {
            JsonNode value = jsonNode.findValue(fieldName);
            if (null != value) {
                return value.asText();
            }
        }
        return "";
    }

}