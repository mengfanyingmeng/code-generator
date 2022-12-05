package com.third.common;

import lombok.Getter;

@Getter
public enum CachePrefixEnum {

    THIRD_WEATHER_NOW("third:weather:now:", "天气实况"),

    ;

    private String prefix;

    private String desc;


    CachePrefixEnum(String prefix, String desc) {
        this.prefix = prefix;
        this.desc = desc;
    }

}
