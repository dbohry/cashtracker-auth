package com.lhamacorp.cashtrackerauth.validator;

import com.lhamacorp.cashtrackerauth.entity.user.User;
import io.netty.util.internal.ObjectUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.xml.bind.ValidationException;

@Component
public class AuthValidator {

    public void validate(User login) throws ServletException {
        if (ObjectUtils.isEmpty(login) || (login.getUsername() == null || login.getPassword() == null)) {
            throw new ServletException("User and password must be not null.");
        }
    }

}
