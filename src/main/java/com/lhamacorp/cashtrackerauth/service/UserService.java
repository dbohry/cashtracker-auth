package com.lhamacorp.cashtrackerauth.service;

import com.lhamacorp.cashtrackerauth.entity.User;
import com.lhamacorp.cashtrackerauth.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) {
        return user;
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
