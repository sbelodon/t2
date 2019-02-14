package main.java.testtask.validator;

import main.java.testtask.dto.PasswordDto;
import main.java.testtask.logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator implements
        ConstraintValidator<PasswordStrengthConstraint, String> {
    @Autowired
    private UserLogic userLogic;

    @Override
    public void initialize(PasswordStrengthConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext cxt) {
        return userLogic.checkPasswordStrength(new PasswordDto(password)) == 2;
    }
}

