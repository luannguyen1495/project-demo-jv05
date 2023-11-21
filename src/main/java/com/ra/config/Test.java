package com.ra.config;


import org.mindrot.jbcrypt.BCrypt;

public class Test {
    public static void main(String[] args) {
        // mã hóa
//        System.out.println(BCrypt.hashpw("123456",BCrypt.gensalt(12)));

        // gải mã
        System.out.println(BCrypt.checkpw("123456","$2a$12$B.qpU0RKVEbNlxkwxnjU0uWZ85EbsxfBEqIkW7pDc5VQQhKCkKBOe"));
    }
}
