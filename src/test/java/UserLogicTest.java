package test.java;

import main.java.testtask.controller.WebMvcConfiguration;
import main.java.testtask.dao.UserDao;
import main.java.testtask.dto.PasswordDto;
import main.java.testtask.dto.UserDTO;
import main.java.testtask.entity.User;
import main.java.testtask.logic.UserLogicImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfiguration.class})
@WebAppConfiguration
public class UserLogicTest {
    public static final String USER = "user";
    public static final String PSW = "psw";
    public static final String ENCODED = "encoded";
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserLogicImpl userLogic;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveUserTest() {
        UserDTO user = new UserDTO();
        user.setUsername(USER);
        user.setPassword(PSW);
        userLogic.saveUser(user);
        Mockito.when(passwordEncoder.encode(Mockito.eq(PSW))).thenReturn(ENCODED);
        Mockito.verify(userDao, Mockito.times(1)).addUser(Mockito.any(User.class));
    }

    @Test
    public void getUserByUserNameTest() {
        User user = new User();
        user.setUsername(USER);
        Mockito.when(userDao.getUserByUsername(Mockito.eq(USER))).thenReturn(user);
        UserDetails userQueried = userLogic.loadUserByUsername(USER);
        assertNotNull(userQueried);
        assertEquals(USER, userQueried.getUsername());
    }

    @Test
    public void checkPasswordStrengthTest() {
        assertEquals(0, (int) userLogic.checkPasswordStrength(new PasswordDto()));
        assertEquals(0, (int) userLogic.checkPasswordStrength(new PasswordDto("123")));
        assertEquals(1, (int) userLogic.checkPasswordStrength(new PasswordDto("1234sssfgs22544ewgs")));
        assertEquals(2, (int) userLogic.checkPasswordStrength(new PasswordDto("asfSDYAasdf123___23AAAFd()#!@#$^$fd")));
    }
}
