package main.java.testtask.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordStrengthConstraint {
    String message() default "Password must be strong!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
