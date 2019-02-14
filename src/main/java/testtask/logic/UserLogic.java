package main.java.testtask.logic;

import main.java.testtask.dto.PasswordDto;
import main.java.testtask.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserLogic extends UserDetailsService {
    void saveUser(UserDTO user);

    Integer checkPasswordStrength(PasswordDto passwordDto);
}
