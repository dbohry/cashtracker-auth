package com.lhamacorp.cashtrackerauth.entity.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User save(User user);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

}
