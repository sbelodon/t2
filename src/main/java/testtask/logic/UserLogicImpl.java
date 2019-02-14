package main.java.testtask.logic;

import main.java.testtask.dao.UserDao;
import main.java.testtask.dto.PasswordDto;
import main.java.testtask.dto.UserDTO;
import main.java.testtask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserLogicImpl implements UserLogic {
    public static final String USER_ROLE = "USER";
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(USER_ROLE);
        userDao.addUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @Override
    public Integer checkPasswordStrength(PasswordDto passwordDto) {
        String password = passwordDto.getPassword();
        if (password == null || password.length() < 8) {
            return 0;
        }
        if (password.matches("(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")) {
            return 2;
        }
        return 1;
    }
}
