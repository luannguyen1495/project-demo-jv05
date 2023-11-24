package com.ra.service;

import com.ra.dto.request.UserRegisterDTO;
import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.dao.UserDAO;
import com.ra.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements  UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public ResponseUserLoginDTO login(String email, String password) {
        User user = userDAO.checkLogin(email,password);
        ResponseUserLoginDTO responseUserLoginDTO = new ResponseUserLoginDTO();
        responseUserLoginDTO.setId(user.getId());
        responseUserLoginDTO.setUserName(user.getUserName());
        responseUserLoginDTO.setEmail(user.getEmail());
        responseUserLoginDTO.setPhone(user.getPhone());
        responseUserLoginDTO.setRole(user.getRole());
        return responseUserLoginDTO;
    }

    @Override
    public Boolean register(UserRegisterDTO userDTO) {
        // max hoa mat khau
        String passwordHash = BCrypt.hashpw(userDTO.getPassword(),BCrypt.gensalt(12));
        userDTO.setPassword(passwordHash);
        ModelMapper  modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO,User.class);
        return  userDAO.save(user);
    }

    public ResponseUserLoginDTO logon(String email, String password) {
        ResponseUserLoginDTO user = login(email,password);
        if(user.getRole() == 1){
            return user;
        }
        return null;
    }
}
