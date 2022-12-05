package tool.annoation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

@Slf4j
public class EnumCheckValidator implements ConstraintValidator<EnumValid, Integer> {

    private EnumValid enumValid;


    @Override
    public void initialize(EnumValid enumValid) {
        this.enumValid = enumValid;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (null == value) {
            return !this.enumValid.required();
        }
        //最终的返回结果
        Boolean result = Boolean.FALSE;
        // 获取 参数的数据类型
        Class<?> valueClass = value.getClass();
        try {
            Method method = this.enumValid.enumClass().getMethod(this.enumValid.enumMethod(), valueClass);
            result = (Boolean) method.invoke(null, value);
            result = result == null ? Boolean.FALSE : result;
            //所有异常需要在开发测试阶段发现完毕
        } catch (Exception e) {
            log.error("枚举值校验失败 , message:{}", e.getMessage());
        } finally {
            return result;
        }
    }
}
