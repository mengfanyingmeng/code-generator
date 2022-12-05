package req;

import lombok.Data;

@Data
public class AmapProperties {

    protected String key = "7242b97a6e5688fa09725b2d1a05e83b";

    /**
     * 逆地理编码API
     **/
    private String regeoUrl;

    /**
     * 基站坐标转换API
     **/
    private String positionUrl;

    /**
     * 天气查询API
     **/
    protected String weatherUrl = "https://restapi.amap.com/v3/weather/weatherInfo";

    /**
     * 轨迹纠偏
     */
    private String grasproadUrl;
}
