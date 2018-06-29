package com.library.tool.validator.validatorClass;

import com.library.tool.validator.Name;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by LinTi on 2016/8/5.
 */
public class NameValidator implements ConstraintValidator<Name, String> {
    public void initialize(Name name) {
    }

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s != null) {
            if (!s.contains(" ") && s.length() > 1 && s.length() < 5) {
                char c[] = s.toCharArray();
                for (int i = 0; i < c.length; i++) {
                    if (!Pattern.compile("[\u4e00-\u9fa5]").matcher(c[i] + "").find()) return false;
                }
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

}
