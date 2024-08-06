package com.example.mango_admin.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final String salt;

    public CustomPasswordEncoder(String salt) {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.salt = salt;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String saltedPassword = rawPassword + salt;
        return bCryptPasswordEncoder.encode(saltedPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String saltedPassword = rawPassword + salt;
        return bCryptPasswordEncoder.matches(saltedPassword, encodedPassword);
    }
}
