package com.lhamacorp.cashtrackerauth.service;

import com.lhamacorp.cashtrackerauth.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User save(User user) {
        return user;
    }

    public User findByEmail(String email) {
        return new User();
    }

}
