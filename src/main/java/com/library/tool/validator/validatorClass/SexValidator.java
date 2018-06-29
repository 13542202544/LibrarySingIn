package com.library.tool.validator.validatorClass;

import com.library.tool.validator.Sex;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by LinTi on 2016/8/5.
 */
public class SexValidator implements ConstraintValidator<Sex, Character> {
    public void initialize(Sex sex) {
    }

    public boolean isValid(Character c, ConstraintValidatorContext constraintValidatorContext) {
        if (c == '1'|| c == '2' || c == '3') {
            return true;
        }
        return false;
    }

}
