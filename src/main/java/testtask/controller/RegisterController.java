package main.java.testtask.controller;

import main.java.testtask.dto.PasswordDto;
import main.java.testtask.dto.UserDTO;
import main.java.testtask.logic.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController implements ErrorMessagesProducingController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserLogic userLogic;

    @GetMapping
    public void get() {

    }

    @PostMapping
    public String register(@ModelAttribute @Valid UserDTO user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            addErrorMessagesToModel(result, model, messageSource);
            return "register";
        }
        userLogic.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/check")
    @ResponseBody
    public ResponseEntity<Integer> checkPasswordStrength(@RequestBody PasswordDto passwordDto) {
        return new ResponseEntity<>(userLogic.checkPasswordStrength(passwordDto), HttpStatus.OK);
    }
}
