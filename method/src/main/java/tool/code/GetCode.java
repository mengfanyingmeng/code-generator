package tool.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tool.config.Feign;
import tool.config.RequestDto;

import java.util.List;

import static tool.constant.AccessControl.*;
import static tool.constant.Defined.*;
import static tool.constant.Supply.*;
import static tool.enums.TypeEnum.getMsg1;

public class GetCode {

    public static void getService(Feign feign) {
        String req = feign.getReq();
        String areq = req.substring(0,1).toLowerCase()+req.substring(1);
        String res = feign.getRes();
        String mName = feign.getMname();
        System.out.println(BaseResponseVo+"<"+res+"> " +mName+"("+ req +" "+ areq + ") ;");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getController(Feign feign) {
        String tName = feign.getTname();
        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
        String req = feign.getReq();
        String areq = req.substring(0,1).toLowerCase()+req.substring(1);
        String res = feign.getRes();
        String mName = feign.getMname();
        String post = feign.getPost();
        String path = feign.getPath();
        if (path==null||path == ""){
            path ="/" + atName+"/"+mName;
        }
        System.out.println(a+Override);
        System.out.println(a+post+Mapping+lef+mao+path+mao+rig);
        System.out.println(public1+n+BaseResponseVo+"<"+res+"> "+mName+"("+ req +" "+ areq + ") {");
        System.out.println(tab+return1+ n + atName+"Service." +mName+lef+areq+rig+";");
        System.out.println("}");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getFeignService(Feign feign) {
        String tName = feign.getTname();
        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
        String req = feign.getReq();
        String areq = req.substring(0,1).toLowerCase()+req.substring(1);
        String res = feign.getRes();
        String mName = feign.getMname();
        String post = feign.getPost();
        String path = feign.getPath();
        if (path==null||path == ""){
            path ="/" + atName+"/"+mName;
        }
        System.out.println(a+post+Mapping+lef+mao+path+mao+rig);
        System.out.println(BaseResponseVo+"<"+res+"> "+mName+"("+ req +" "+ areq + ");" );
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

//    public static void getFallback(Feign feign) {
//
//    }
//
//    public static void getWebServiceImpl(Feign feign) {
//
//    }
//
//    public static void getWebService(Feign feign) {
//
//    }

    public static void getWebController(Feign feign) {
        String tName = feign.getTname();
        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
        String req = feign.getReq();
        String areq = req.substring(0,1).toLowerCase()+req.substring(1);
        String res = feign.getRes();
        String mName = feign.getMname();
        String post = feign.getPost();
        System.out.println(a+post+Mapping+lef+mao+mao+rig);
        System.out.println(public1+n+BaseResponseVo+"<"+res+"> "+mName+"("+ req +" "+ areq + ") {");
        System.out.println(tab+return1+ n + atName+"FeignService." +mName+lef+areq+rig+";");
        System.out.println("}");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

//    public static void getAppServiceImpl(Feign feign) {
//
//    }
//
//    public static void getAppService(Feign feign) {
//
//    }

    public static void getAppController(Feign feign) {
        String tName = feign.getTname();
        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
        String req = feign.getReq();
        String areq = req.substring(0,1).toLowerCase()+req.substring(1);
        String res = feign.getRes();
        String mName = feign.getMname();
        String post = feign.getPost();
        System.out.println(a+post+Mapping+lef+mao+mao+rig);
        System.out.println(public1+n+BaseResponseVo+"<"+res+"> "+mName+"("+ req +" "+ areq + ") {");
        System.out.println(tab+return1+ n + atName+"FeignService." +mName+lef+areq+rig+";");
        System.out.println("}");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getEntity(Feign feign) {
        String tableName = feign.getTableName();
        String tname = feign.getTname();
        List<RequestDto> list = feign.getList();
        System.out.println("@Data\n@EqualsAndHashCode(callSuper = false)\n@TableName(\""+tableName+"\")");
        System.out.println(public1+n+ class1+n +tname+n+imp1+n+serial +n+lef1);
        System.out.println();
        System.out.println(tab+private1+n+static1+n+final1+n+"long" +serial1 +"="+n+"1L;");
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(tab+"/**\n"+tab+" * "+list.get(i).getAnnotation()+"\n"+tab+" */");
            System.out.println(tab+private1+n+getMsg1(list.get(i).getType()) +n+list.get(i).getName()+";");
            System.out.println();
        }
        System.out.println(rig1);
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getCreateTable(Feign feign) {
        System.out.println();
        String tableName = feign.getTableName();
        String tableNote = feign.getTableNote();
        List<RequestDto> list = feign.getList();
        System.out.println("\ncreate table `"+tableName+"` ( ");
        for (int i = 0; i < list.size(); i++) {
            String line = tab+tab+"`"+list.get(i).getName()+"` ";
            if(list.get(i).getType()==1) {
                line=line +"varchar(64) COLLATE utf8_general_ci ";
                if (list.get(i).getNotnull().equals("1") ){
                    line = line + "NOT NULL ";
                }
                if (list.get(i).getDefaultValue()!=null){
                    line = line +"DEFAULT '"+list.get(i).getDefaultValue()+"' ";
                }
                line = line +"COMMENT \"" + list.get(i).getAnnotation()+"\", ";
            } else if (list.get(i).getType() == 2) {
                line = line+"tinyint(2) ";
                if (list.get(i).getNotnull().equals("1") ){
                    line = line + "NOT NULL ";
                }
                if (list.get(i).getDefaultValue()!=null){
                    line = line +"DEFAULT "+list.get(i).getDefaultValue()+" ";
                }
                line = line +"COMMENT \"" + list.get(i).getAnnotation()+"\", ";
            } else if (list.get(i).getType() == 3) {
                if(list.get(i).getName() == "id"){
                    line = line + "bigint(19) NOT NULL /*T![auto_rand] AUTO_RANDOM(5) */ COMMENT '" + list.get(i).getAnnotation() + "',";
                }else {
                    line = line + "bigint(19) NOT NULL COMMENT '" + list.get(i).getAnnotation() + "',";
                }
            } else if (list.get(i).getType() == 4) {
                line = line +"datetime ";
                if (list.get(i).getNotnull().equals("1") ){
                    line = line + "NOT NULL ";
                }
                if (list.get(i).getDefaultValue()!=null){
                    line = line +"DEFAULT "+list.get(i).getDefaultValue()+" ";
                }
                line = line +"COMMENT \"" + list.get(i).getAnnotation()+"\", ";
            }
            System.out.println(line);
        }
        System.out.println(tab+tab+"PRIMARY KEY (`id`) /*T![clustered_index] CLUSTERED */,");
        System.out.println("ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci /*T![auto_rand_base] AUTO_RANDOM_BASE=240001 */ COMMENT='"
        +tableNote+"';");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getConClass(Feign feign) {
        String tName = feign.getTname();
        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
        System.out.println();
        System.out.println("@Slf4j\n@RestController\n@RequiredArgsConstructor(onConstructor = @__(@Autowired))");
        System.out.println(public1+n+class1+n+tName+"Controller" +n+imp1+n+tName+"FeignService {\n");
        System.out.println(tab+private1+n+final1+tName+"Service"+n+atName+"Service;");
        System.out.println(rig1);
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getServiceClass(Feign feign) {
        String tName = feign.getTname();
        System.out.println();
        System.out.println(public1+n+inter+n+tName+"Service "+extends1+" IService<"+tName+"> {");
        System.out.println();
        System.out.println("\n}");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getSerImplClass(Feign feign) {
        String tName = feign.getTname();
        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
        System.out.println();
        System.out.println("@Slf4j\n@RestController\n@RequiredArgsConstructor(onConstructor = @__(@Autowired))");
        System.out.println(public1+n+class1+n+tName+"ServiceImpl extends ServiceImpl<"+tName+"Mapper, "+tName+"> implements "+tName+"Service {");
        System.out.println("\n"+tab+private1+final1+tName+"Mapper "+atName+"Mapper ;\n}");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getMapperClass(Feign feign) {
        String tName = feign.getTname();
        System.out.println();
        System.out.println(public1+n+inter+n+tName+"Mapper "+extends1+n+"BaseMapper<"+tName+"> {\n}");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }


    public static void getFeignClass(Feign feign) {
        String db = feign.getDb();
        String tName = feign.getTname();
        String atName = tName.substring(0,1).toLowerCase()+tName.substring(1);
        System.out.println();
        System.out.println("@FeignClient(value = \""+db+"\", contextId = \""+atName+"\", fallbackFactory = "+tName+"FeignServiceFallback.class)");
        System.out.println(public1+n+inter+n+tName+"FeignService {\n}");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getFallbackClass(Feign feign) {
        String tName = feign.getTname();
        System.out.println();
        System.out.println("@Component");
        System.out.println(public1+n+class1+n+tName+"FeignServiceFallback "+imp1+n+"FallbackFactory<"+tName+"FeignService> {");
        System.out.println(tab+"@Override");
        System.out.println(tab+public1+n+tName+"FeignService"+n+"create(Throwable cause) {");
        System.out.println(tab+tab+"return new "+tName+"FeignService() {");
        System.out.println(tab+tab+"};\n"+tab+"}\n}");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public static void getMapperXml(Feign feign) {
        String file = feign.getFile();
        String tname = feign.getTname();
        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        System.out.println("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        System.out.println("<mapper namespace=\"com.yadea.iot.tsp."+file+".mapper."+tname+"Mapper\">");
        System.out.println("\n</mapper>");
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    }

    public static void getPageQuery(Feign feign) {



    }




//
}
