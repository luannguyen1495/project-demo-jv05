package com.ra.service;

import com.ra.model.dao.UserDAO;
import com.ra.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements  UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public User login(String email, String password) {
        return userDAO.checkLogin(email,password);
    }

    @Override
    public Boolean register(User user) {
        // max hoa mat khau
        String passwordHash = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12));
        user.setPassword(passwordHash);
        return  userDAO.save(user);
    }
}
