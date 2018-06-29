package com.library.tool.validator;

import com.library.tool.validator.validatorClass.SexValidator;
import com.library.tool.validator.validatorClass.XiBieValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LinTi on 2016/8/5.
 */
@Constraint(validatedBy = XiBieValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XiBie {

    String message() default "请选择";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
