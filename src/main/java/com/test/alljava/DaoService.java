package com.test.alljava;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DaoService {
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Transactional
    public void addUser(User user) {
        this.dao.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        this.dao.updateUser(user);
    }

    @Transactional
    public void removeUser(int id) {
        this.dao.removeUser(id);
    }

    public User getUserById(int id) {
        return this.dao.getUserById(id);
    }

    public List<User> listUsers() {
        return this.dao.listUsers();
    }

}
