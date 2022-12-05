package tool.config;

import lombok.Data;

import java.util.List;

@Data

public class Feign {
    //数据库名
    private String db;

    //目录
    private String file;

    //表名 首字母大写
    private String tname;

    //表名(英文)
    private String tableName;

    //表名(中文)
    private String tableNote;

    //方法入参类型
    private String req;

    //方法返回数据类型
    private String res;

    //方法名称
    private String mname;

    //请求方法
    private String post;

    //请求路径
    private String path;

    //表格列
    private List<RequestDto>  list;

}
