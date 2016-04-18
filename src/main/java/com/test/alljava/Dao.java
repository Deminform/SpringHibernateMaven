package com.test.alljava;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Dao {
    private static final Logger logger = LoggerFactory.getLogger(Dao.class);

    public void setSession(SessionFactory session) {
        this.session = session;
    }

    private SessionFactory session;

    public void addUser(User user) {
        Session session = this.session.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. User details: " + user);
    }

    public void updateUser(User user) {
        Session session = this.session.getCurrentSession();
        session.update(user);
        logger.info("User successfully update. User details: " + user);

    }

    public void removeUser(int id) {
        Session session = this.session.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if (user!=null) {
            session.delete(user);
        }
        logger.info("User successfully removed. User details: " + user);
    }

    public User getUserById(int id) {
        Session session = this.session.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User successfully loaded. User details: " + user);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = this.session.getCurrentSession();
        List<User> listUsers = session.createQuery("from User").list();
        for (User user : listUsers) {
            logger.info("User list: " + user);
        }
        return listUsers;
    }



}
