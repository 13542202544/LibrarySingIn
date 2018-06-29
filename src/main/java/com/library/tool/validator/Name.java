package com.library.tool.validator;

import com.library.tool.validator.validatorClass.NameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by LinTi on 2016/8/5.
 */
@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {

    String message() default "姓名只能全为中文,且2-4个字";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
