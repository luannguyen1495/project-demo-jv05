package com.ra.model.dao;

import com.ra.model.entity.User;
import com.ra.util.ConnectionDatabase;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOImpl implements  UserDAO{
    @Override
    public Boolean save(User user) {
        Connection connection = null;
            try {
                connection = ConnectionDatabase.openConnection();
                PreparedStatement preparedStatement = null;
                preparedStatement = connection.prepareStatement("INSERT INTO user(user_name,email, password, phone, role) VALUES (?,?,?,?,?)");
                preparedStatement.setString(1,user.getUserName());
                preparedStatement.setString(2,user.getEmail());
                preparedStatement.setString(3,user.getPassword());
                preparedStatement.setString(4,user.getPhone());
                preparedStatement.setByte(5,user.getRole());
                int check = preparedStatement.executeUpdate();
                if(check >0) {
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectionDatabase.closeConnection(connection);
            }
        return false;
    }

    @Override
    public User checkLogin(String email, String password) {
        Connection connection = null;
        User user = new User();
        try {
            connection = ConnectionDatabase.openConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();

               while (resultSet.next()){
                   user.setId(resultSet.getInt("id"));
                   user.setUserName(resultSet.getString("user_name"));
                   user.setEmail(resultSet.getString("email"));
                   user.setPassword(resultSet.getString("password"));
                   user.setRole(resultSet.getByte("role"));
               }
               if(BCrypt.checkpw(password,user.getPassword())){
                   System.out.println(user.getUserName());
                   return user;
               }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDatabase.closeConnection(connection);
        }
        return null;

    }
}
