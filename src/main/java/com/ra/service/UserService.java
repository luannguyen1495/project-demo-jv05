package com.ra.service;

import com.ra.dto.request.UserRegisterDTO;
import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.entity.User;

public interface UserService {
    ResponseUserLoginDTO login(String email, String password);
    Boolean register(UserRegisterDTO user);
    ResponseUserLoginDTO logon(String email,String password);
}
