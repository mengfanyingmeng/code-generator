package com;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import req.*;
import resp.*;
import util.JacksonUtil;
import util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import static common.ExceptionEnum.WEATHER_NOW_RESULT_SIZE_ILLEGAL;


@Slf4j
public class Weather {

    private static String TIANQI_API_SECRET_KEY = "l69ka3qebirctpvj";
    private static String TIANQI_NOW_WEATHER_URL = "https://api.seniverse.com/v3/weather/now.json";

    private static String TIANQI_API_SECRET_KEY1 = "7242b97a6e5688fa09725b2d1a05e83b";
    private static String TIANQI_NOW_WEATHER_URL1 = "https://restapi.amap.com/v3/weather/weatherInfo";

    @Autowired
    private static AmapProperties amapProperties;
    private static final String SUCCESS_CODE = "1";

    public WeatherResp getWeatherNow(WeatherReq req){
        WeatherResp weatherResp = new WeatherResp();

        return weatherResp;
    }

    private static XinZhiWeatherResp getXinZhiWeather(XinZhiWeatherReq req){
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

    private static MapWeatherResp getMapWeather(MapWeatherReq req){
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

    public static void main(String[] args) {
        XinZhiWeatherReq req = new XinZhiWeatherReq();
        req.setLocation("wuxi");
        XinZhiWeatherResp xinZhiWeather = getXinZhiWeather(req);

        MapWeatherReq req1 = new MapWeatherReq();
        req1.setAdcode("320500");
        MapWeatherResp mapWeather = getMapWeather(req1);
    }
}
