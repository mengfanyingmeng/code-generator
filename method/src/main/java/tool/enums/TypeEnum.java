package tool.enums;


import java.util.Objects;

public enum TypeEnum {
    String(1,"String"),
    Integer(2,"Integer"),
    Long(3,"Long"),
    LocalDateTime(4,"LocalDateTime"),
    ;

    private Integer code;
    private String msg;

    TypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg1(Integer code) {
        for (TypeEnum enum1 : TypeEnum.values()) {
            if(enum1.code.equals(code)){
                return enum1.msg;
            }
        }
        return null;
    }
}
