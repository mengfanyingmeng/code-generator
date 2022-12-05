package resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeatherResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市中文
     */
    private String location;
    /**
     * 温度，单位为c摄氏度
     */
    private String temperature;
    /**
     * 天气现象代码
     */
    private String code;
    /**
     * 天气现象代码中文描述
     */
    private String codeDesc;
    /**
     * 数据更新时间（该城市的本地时间）
     */
    private String lastUpdate;

    /**
     * 城市中文名称
     */
    private String cityName;

    /**
     * 国家编码
     */
    private String country;

}
