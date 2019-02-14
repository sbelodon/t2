package main.java.testtask.controller;

import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public interface ErrorMessagesProducingController {
    default void addErrorMessagesToModel(BindingResult result, Model model, MessageSource messageSource) {
        String message = null;
        for (Object object : result.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                message = messageSource.getMessage(fieldError, null);
            }
        }
        model.addAttribute("message", message);
    }
}
