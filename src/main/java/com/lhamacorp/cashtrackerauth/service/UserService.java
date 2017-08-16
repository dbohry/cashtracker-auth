package com.lhamacorp.cashtrackerauth.service;

import com.lhamacorp.cashtrackerauth.entity.role.RoleRepository;
import com.lhamacorp.cashtrackerauth.entity.user.User;
import com.lhamacorp.cashtrackerauth.entity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserService {

    private UserRepository repository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        user.setEnabled(Boolean.FALSE);
        return repository.save(user);
    }

    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(Boolean.TRUE);
        return repository.save(user);
    }

    public User get(Long id) {
        return repository.findOne(id);
    }

    public User findByLogin(String username, String password) {
        return repository.findByUsernameAndPasswordAndEnabledTrue(username, password);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
