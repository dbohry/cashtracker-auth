package com.lhamacorp.cashtrackerauth.service;

import com.lhamacorp.cashtrackerauth.entity.user.User;
import com.lhamacorp.cashtrackerauth.validator.AuthValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.Objects;

@Service
public class AuthService {

    private UserService userService;
    private AuthValidator validator;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserService userService,
                       AuthValidator validator,
                       PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    public String getToken(User login) throws ValidationException {
        validator.validate(login);

        String hash = passwordEncoder.encode(login.getPassword());

        User user = userService.findByLogin(new User(login.getEmail(), hash));

        if (user == null) throw new ValidationException("User not found.");

        if (!Objects.equals(hash, user.getPassword()))
            throw new ValidationException("Login failed, pls try again.");

        return Jwts.builder()
                .setSubject(login.getEmail())
                .claim("roles", user.getRoles().toString())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
    }

}
