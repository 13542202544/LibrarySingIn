package com.library.tool.validator.validatorClass;

import com.library.model.Department;
import com.library.tool.validator.Depar;
import com.library.tool.validator.XiBie;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by LinTi on 2016/8/5.
 */
public class DeparValidator implements ConstraintValidator<Depar, Department> {
    public void initialize(Depar depar) {
    }

    public boolean isValid(Department d, ConstraintValidatorContext constraintValidatorContext) {
        if (d.getdId() > 0) {
            return true;
        }
        return false;
    }

}
