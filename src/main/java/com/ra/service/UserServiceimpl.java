package com.ra.service;

import com.ra.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements  UserService {

    @Override
    public Boolean login(User user) {
        if(user.getEmail().equals("user@gmail.com") && user.getPassword().equals("123")){
            return true;
        }
        return false;
    }
}
