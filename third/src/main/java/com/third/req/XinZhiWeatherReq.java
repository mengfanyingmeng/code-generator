package com.third.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class XinZhiWeatherReq {

    @NotBlank
    private String location;
}
