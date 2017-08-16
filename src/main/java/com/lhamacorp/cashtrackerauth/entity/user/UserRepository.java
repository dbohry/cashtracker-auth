package com.lhamacorp.cashtrackerauth.entity.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsernameAndPasswordAndEnabledTrue(String username, String password);

}
