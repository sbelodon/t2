package test.java;

import main.java.testtask.controller.WebMvcConfiguration;
import main.java.testtask.dao.UserDao;
import main.java.testtask.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfiguration.class})
@WebAppConfiguration

public class UserDaoTest {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "ROLE";
    @Autowired
    private UserDao userDao;

    @Before
    public void testUser() {
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setRole(ROLE);
        Integer savedUserId = userDao.addUser(user);
        assertNotNull(savedUserId);
    }

    @Test
    public void testGetByUserName() {
        User userByUsername = userDao.getUserByUsername(USERNAME);
        assertNotNull(userByUsername);
        assertEquals(USERNAME, userByUsername.getUsername());
        assertEquals(PASSWORD, userByUsername.getPassword());
        assertEquals(ROLE, userByUsername.getRole());
    }
}
