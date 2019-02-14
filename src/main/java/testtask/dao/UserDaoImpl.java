package main.java.testtask.dao;

import main.java.testtask.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer addUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        Integer savedUserId = (Integer) currentSession.save(user);
        currentSession.flush();
        return savedUserId;
    }

    @Override
    public User getUserByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = currentSession.createQuery("from User where username = :name", User.class)
                .setParameter("name", username)
                .uniqueResult();
        return user;
    }
}
