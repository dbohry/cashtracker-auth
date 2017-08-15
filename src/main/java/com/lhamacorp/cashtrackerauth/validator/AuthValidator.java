package com.lhamacorp.cashtrackerauth.validator;

import com.lhamacorp.cashtrackerauth.entity.user.User;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class AuthValidator {

    public void validate(User login) throws ValidationException {
        if (login.getEmail() == null || login.getPassword() == null) {
            throw new ValidationException("User and password must be not null.");
        }
    }

}
