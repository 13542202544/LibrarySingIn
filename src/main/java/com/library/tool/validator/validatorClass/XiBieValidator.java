package com.library.tool.validator.validatorClass;

import com.library.tool.validator.Sex;
import com.library.tool.validator.XiBie;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by LinTi on 2016/8/5.
 */
public class XiBieValidator implements ConstraintValidator<XiBie, com.library.model.XiBie> {
    public void initialize(XiBie xiBie) {
    }

    public boolean isValid(com.library.model.XiBie xiBie, ConstraintValidatorContext constraintValidatorContext) {
        if (xiBie.getXbId() != null) {
            if (xiBie.getXbId() > 0) {
                return true;
            }
            return false;
        }else {
            return true;
        }
    }

}
