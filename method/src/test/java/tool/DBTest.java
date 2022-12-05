package tool;

import tool.code.GetCode;
import tool.config.Feign;
import tool.config.RequestDto;

import java.util.ArrayList;
import java.util.List;

public class DBTest {

    private static GetCode getCode;

    public static void main(String[] args) {
        Feign feign = new Feign();
        feign.setDb("soa-system");
        feign.setTableNote("维修宝版本控制表");
        feign.setTableName("t_bike_battery_and_mileage");
        feign.setTname("BikeBatteryAndMileage");

        List<RequestDto> list = new ArrayList<>();
        list.add(new RequestDto("id", 3, "主键","1",null));
        list.add(new RequestDto("type", 2, "所属设备 1:安卓  0:ios","1",null));
        list.add(new RequestDto("version", 1, "版本号","1",null));
        list.add(new RequestDto("latest", 2, "是否最新版本,0：不是； 1：是","0","0"));
        list.add(new RequestDto("creatTime", 4, "创建时间","1","CURRENT_TIMESTAMP"));

        feign.setList(list);

        getCode.getCreateTable(feign);
        getCode.getEntity(feign);
    }
}
