package com.dstevens.reader.users;

interface UserDao {

    void save(User user);
    
    User findBy(String email);

    User findById(String userId);
    
}
