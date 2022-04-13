package kz.iitu.itse_1904.naga.validator;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.AssertTrue;

public class CustomValidator implements
        ConstraintValidator<CustomConstraint, String> {

    @Override
    @AssertTrue
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty();
    }
}
