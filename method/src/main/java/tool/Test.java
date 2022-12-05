package tool;


import tool.code.GetCode;
import tool.config.Feign;
import tool.config.RequestDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test {

    private static GetCode getCode;

    public static void main(String[] args) {
        Feign feign = new Feign();


        feign.setReq("VersionCtrl");
        feign.setRes("Void");
        feign.setMname("saveBikeBatteryAndMileage");
        feign.setPost("Post");
        //feign.setPath("/bikeBatteryAndMileage/saveBikeBatteryAndMileage");

        feign.setFile("soa.system");
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
        //getMethod(feign);
        getCodeClass(feign);
        getTableBaseMethod(feign);
        System.out.println(~-1);

    }
    //存储到文件的固定位置（去掉“}”和注释以及空行后的下一行 ）

    /**
     * 生成 方法
     */
    private static void getMethod(Feign feign) {
        getCode.getController(feign);
        getCode.getService(feign);
        getCode.getFeignService(feign);
    }

    /**
     * 生成类/接口
     */
    private static void getCodeClass(Feign feign) {
        getCode.getCreateTable(feign);
        getCode.getEntity(feign);
        getCode.getConClass(feign);
        getCode.getServiceClass(feign);
        getCode.getSerImplClass(feign);
        getCode.getMapperClass(feign);
        getCode.getMapperXml(feign);
        getCode.getFeignClass(feign);
        getCode.getFallbackClass(feign);
    }

    //生成 单表 增删改查
    private static void getTableBaseMethod(Feign feign) {
        getCode.getPageQuery(feign);
    }

//    //生成封装方法
//    private static void getManyMethod(Feign feign){
//        String tName = feign.gettName();
//        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
//        String req = feign.getReq();
//        String areq = req.substring(0,1).toLowerCase()+req.substring(1);
//        String res = feign.getRes();
//        String mName = feign.getmName();
//        String post = feign.getPost();
//        System.out.println(a+post+Mapping+lef+mao+mao+rig);
//        System.out.println(BaseResponseVo+"<"+res+"> "+mName+"("+ req +" "+ areq + ")" +"{");
//        System.out.println(tab+return1+ n + atName+"Service." +mName+lef+areq+rig+";");
//        System.out.println("}");
//        System.out.println();
//        // BaseResponseVo<List<BikeConfigVo>> selectConfigListByModelNo(SelectConfigListByModelNoReq req);
//        System.out.println(BaseResponseVo+"<"+res+"> " +mName+"("+ req +" "+ areq + ") ;");
//
//    }
//
//    private static void queryMyBatis(Feign feign){
//        String tName = feign.gettName();
//        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
//        System.out.println(public1 + n + BaseResponseVo+"<List<"+tName+"Vo>> select"+tName+"List("+tName+"QueryDto dto) {");
//        System.out.println(tab+"List<"+tName+">" + n +atName+" = "+atName+"Mapper.selectList(Wrappers.<"+tName+">lambdaQuery()" +
//                ".in("+atName+"::getPhenomenon, collect));");
//        System.out.println(tab + "return "+ BaseResponseVo +".success(BeanHelper.copyWithCollection("+atName+","+tName+"Vo::new));");
//        System.out.println("}");
//    }
}
