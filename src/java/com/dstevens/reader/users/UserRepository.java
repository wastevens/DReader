package com.dstevens.reader.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final UserDao dao;

    @Autowired
    public UserRepository(UserDao dao) {
        this.dao = dao;
    }
    
    public void save(User user) {
        dao.save(user);
    }
    
    public User findBy(String email) {
        return dao.findBy(email);
    }

    public User findById(String userId) {
        return dao.findById(userId);
    }
    
}
