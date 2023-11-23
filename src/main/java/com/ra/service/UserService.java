package com.ra.service;

import com.ra.model.entity.User;

public interface UserService {
    User login(String email,String password);
    Boolean register(User user);
    User logon(String email,String password);
}
