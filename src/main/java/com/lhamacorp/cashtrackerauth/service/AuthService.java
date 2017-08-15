package com.lhamacorp.cashtrackerauth.service;

import com.lhamacorp.cashtrackerauth.entity.User;
import com.lhamacorp.cashtrackerauth.validator.AuthValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.Objects;

@Service
public class AuthService {

    private UserService userService;
    private AuthValidator validator;
    private PasswordBuilder passwordBuilder;

    @Autowired
    public AuthService(UserService userService,
                       AuthValidator validator,
                       PasswordBuilder passwordBuilder) {
        this.userService = userService;
        this.validator = validator;
        this.passwordBuilder = passwordBuilder;
    }

    public String getToken(User login) throws ValidationException {
        validator.validate(login);

        String hash = passwordBuilder.encode(login.getPassword());

        User user = userService.findByLogin(new User(login.getEmail(), hash));

        if (user == null) throw new ValidationException("User not found.");

        if (!Objects.equals(hash, user.getPassword()))
            throw new ValidationException("Login failed, pls try again.");

        return Jwts.builder().setSubject(login.getEmail()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
    }

}
