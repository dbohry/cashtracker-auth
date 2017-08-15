package com.lhamacorp.cashtrackerauth.service;

import com.lhamacorp.cashtrackerauth.entity.User;
import com.lhamacorp.cashtrackerauth.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User get(Long id) {
        return repository.findOne(id);
    }

    public User findByLogin(User user) {
        return repository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
