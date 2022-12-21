package com.third.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.third.common.BizException;
import com.third.req.AmapProperties;
import com.third.req.MapWeatherReq;
import com.third.req.WeatherReq;
import com.third.req.XinZhiWeatherReq;
import com.third.resp.MapWeatherResp;
import com.third.resp.WeatherResp;
import com.third.resp.XinZhiWeatherResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.third.util.JacksonUtil;
import com.third.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import static com.third.common.ExceptionEnum.WEATHER_NOW_RESULT_SIZE_ILLEGAL;

@Slf4j
@RestController
public class Weather {
//    @Value("${xinzhi.weather.seniverse.secret.key}")
//    private static String TIANQI_API_SECRET_KEY;
//    @Value("${xinzhi.weather.seniverse.url.now}")
//    private static String TIANQI_NOW_WEATHER_URL;
//
//    @Value("${map.weather.seniverse.secret.key}")
//    private String TIANQI_API_SECRET_KEY1;
//    @Value("${map.weather.seniverse.url.now}")
//    private String TIANQI_NOW_WEATHER_URL1;

    private static String TIANQI_API_SECRET_KEY = "";
    private static String TIANQI_NOW_WEATHER_URL = "https://api.seniverse.com/v3/weather/now.json";

    private static String TIANQI_API_SECRET_KEY1 = "";
    private static String TIANQI_NOW_WEATHER_URL1 = "https://restapi.amap.com/v3/weather/weatherInfo";

    @Autowired
    private static AmapProperties amapProperties;
    private static final String SUCCESS_CODE = "1";

    public WeatherResp getWeatherNow(WeatherReq req){
        WeatherResp weatherResp = new WeatherResp();
        return weatherResp;
    }

    @PostMapping("/weather/xinzhi/queryWeather")
    public static XinZhiWeatherResp getXinZhiWeather(@RequestBody XinZhiWeatherReq req){
        String location = req.getLocation();
        XinZhiWeatherResp resp = new XinZhiWeatherResp();
        String params = "key=" + TIANQI_API_SECRET_KEY + "&location=" + location + "&language=zh-Hans&unit=c";
        String fullUrl = TIANQI_NOW_WEATHER_URL + "?" + params;
        log.info("---【心知天气】天气实况request url: {}", fullUrl);
        String result = OkHttpUtil.get(fullUrl);
        log.info("---【心知天气】天气实况result: {}", result);
        if(StringUtils.isBlank(result)){
            return resp;
        }
        try {
            resp = parseWeatherJson(result);
            if(StringUtils.isBlank(resp.getTemperature())){
                return resp;
            }
            resp.setLocation(location);
            return resp;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BizException(WEATHER_NOW_RESULT_SIZE_ILLEGAL);
        }
    }

    @PostMapping("/weather/gaode/queryWeather")
    public static MapWeatherResp getMapWeather(@RequestBody MapWeatherReq req){
        MapWeatherResp mapWeatherResp = new MapWeatherResp();
        String adcode = req.getAdcode();
        try {
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("key", TIANQI_API_SECRET_KEY1);
            //城市编码---输入城市的adcode
            queryMap.put("city", adcode);
            //气象类型---可选值：base/all
            //base:返回实况天气
            //all:返回预报天气
            queryMap.put("extensions", "base");
            //返回格式---可选值：JSON,XML
            queryMap.put("output", "JSON");
            String resStr = OkHttpUtil.get(TIANQI_NOW_WEATHER_URL1, queryMap);
            JSONObject jsonObject = JSONObject.parseObject(resStr);
            if (SUCCESS_CODE.equals(jsonObject.getString("status"))){
                MapWeatherResp vo = JSONArray.parseArray(jsonObject.getJSONArray("lives").toJSONString(),MapWeatherResp.class).get(0);
                log.info(resStr);
                return vo;
            }
            log.error("天气查询失败, adcode={}, response={}", adcode, resStr);
        } catch (Exception e) {
            log.error("天气查询失败, adcode={}", adcode, e);
        }
        return mapWeatherResp;
    }

    private static XinZhiWeatherResp parseWeatherJson(String result) throws JsonProcessingException {
        XinZhiWeatherResp resp = new XinZhiWeatherResp();
        ObjectMapper objectMapper = JacksonUtil.getMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        JsonNode results = jsonNode.get("results");
        if(results != null && results.size() > 0){
            JsonNode child = results.get(0);
            JsonNode nowNode = child.get("now");
            String text = nowNode.get("text").asText();
            String code = nowNode.get("code").asText();
            String temperature = nowNode.get("temperature").asText();
            String lastUpdate = child.get("last_update").asText();

            JsonNode locationNode = child.get("location");
            String cityName = locationNode.get("name").asText();
            String country = locationNode.get("country").asText();
            resp.setTemperature(temperature);
            resp.setCode(code);
            resp.setCodeDesc(text);
            resp.setLastUpdate(lastUpdate);
            resp.setCityName(cityName);
            resp.setCountry(country);
        }
        return resp;
    }
}
