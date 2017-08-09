package com.lhamacorp.cashtrackerauth.entity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User save(User user);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

}
