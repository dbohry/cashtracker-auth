package com.lhamacorp.cashtrackerauth.service;

import com.lhamacorp.cashtrackerauth.entity.User;
import com.lhamacorp.cashtrackerauth.entity.UserRepository;
import org.apache.commons.jxpath.servlet.PageScopeContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;
    private PasswordBuilder passwordBuilder;

    @Autowired
    public UserService(UserRepository repository,
                       PasswordBuilder passwordBuilder) {
        this.repository = repository;
        this.passwordBuilder = passwordBuilder;
    }

    public User save(User user) {
        user.setPassword(passwordBuilder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByLogin(User user) {
        return repository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

}
