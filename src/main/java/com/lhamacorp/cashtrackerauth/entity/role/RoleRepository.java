package com.lhamacorp.cashtrackerauth.entity.role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String role);
}
