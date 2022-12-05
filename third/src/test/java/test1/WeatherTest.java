package test1;

import com.third.controller.Weather;
import com.third.req.MapWeatherReq;
import com.third.req.XinZhiWeatherReq;
import com.third.resp.MapWeatherResp;
import com.third.resp.XinZhiWeatherResp;

public class WeatherTest {

    public static void main(String[] args) {
        XinZhiWeatherReq req = new XinZhiWeatherReq();
        req.setLocation("wuxi");
        XinZhiWeatherResp xinZhiWeather = Weather.getXinZhiWeather(req);

        MapWeatherReq req1 = new MapWeatherReq();
        req1.setAdcode("320500");
        MapWeatherResp mapWeather = Weather.getMapWeather(req1);
    }


}
