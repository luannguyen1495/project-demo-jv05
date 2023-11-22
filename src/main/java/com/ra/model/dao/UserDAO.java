package com.ra.model.dao;

import com.ra.model.entity.User;

public interface UserDAO {
    Boolean save(User user);
    User checkLogin(String email,String password);

}
