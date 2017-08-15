package com.lhamacorp.cashtrackerauth.entity.user;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDTO convert(User bo) {
        if (bo == null) return null;

        UserDTO dto = new UserDTO();

        dto.setId(bo.getId());
        dto.setEmail(bo.getEmail());
        dto.setUsername(bo.getUsername());
        dto.setPassword(bo.getPassword());
        dto.setEnabled(bo.getEnabled());

        return dto;
    }

}
