package com.lhamacorp.cashtrackerauth.service;

import com.lhamacorp.cashtrackerauth.entity.user.User;
import com.lhamacorp.cashtrackerauth.validator.AuthValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
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

    public String getToken(User login) throws ServletException {
        validator.validate(login);

        String hash = passwordEncoder.encode(login.getPassword());
        User user = userService.findByLogin(login.getUsername(), hash);

        if (user == null) throw new ServletException("User not found.");

        return Jwts.builder()
                .setSubject(login.getEmail())
                .claim("roles", user.getRoles().toString())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
    }

}
