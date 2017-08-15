package com.lhamacorp.cashtrackerauth.controller;

import com.lhamacorp.cashtrackerauth.entity.user.User;
import com.lhamacorp.cashtrackerauth.service.AuthService;
import com.lhamacorp.cashtrackerauth.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/ct/login")
public class LoginController {

    private UserService userService;
    private AuthService authService;

    @Autowired
    public LoginController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @CrossOrigin(allowedHeaders = "*")
    @ApiOperation(value = "Try to register", response = User.class)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        return userService.save(user);
    }

    @CrossOrigin(allowedHeaders = "*")
    @ApiOperation(value = "Try to authenticate", response = String.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody User login) throws ValidationException {
        String response = authService.getToken(login);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
