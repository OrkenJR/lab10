package kz.iitu.itse_1904.naga.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomConstraint {
    String message() default "Some error";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
