package common;

import lombok.Getter;

@Getter
public enum ExceptionEnum implements BaseEnumResult{

    WEATHER_NOW_RESULT_SIZE_ILLEGAL("190010", "天气实况查询结果json解析异常"),
    ;

    // 响应码
    private String code;
    // 响应码
    private String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
