package tool.config;

import lombok.Data;
import lombok.experimental.Accessors;
import tool.annoation.EnumValid;
import tool.enums.TypeEnum;

@Data
@Accessors(chain = true)
public class RequestDto {

    /**
     * 字段名稱
     */
    private String name;

    /**
     * 字段类型
     */
    @EnumValid(enumClass = TypeEnum.class)
    private Integer type;

    /**
     * 字段注释
     */
    private String annotation;

    /**
     * 非空按钮
     * 0:false  1:true
     */
    private String notnull;

    /**
     * 默认值
     */
    private String defaultValue;

    // 0    没有默认值
    // 0    "null"
    // 0    其他默认值
    // 1    没有默认值
    // 1    "null"
    // 1    其他默认值
    public RequestDto(String name, Integer type, String annotation, String notnull, String defaultValue) {
        this.name = name;
        this.type = type;
        this.annotation = annotation;
        this.notnull = notnull;
        this.defaultValue = defaultValue;
    }
}
