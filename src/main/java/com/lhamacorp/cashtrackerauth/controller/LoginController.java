package com.lhamacorp.cashtrackerauth.controller;

import com.lhamacorp.cashtrackerauth.entity.user.User;
import com.lhamacorp.cashtrackerauth.entity.user.UserConverter;
import com.lhamacorp.cashtrackerauth.entity.user.UserDTO;
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
    private UserConverter converter;

    @Autowired
    public LoginController(UserService userService,
                           AuthService authService,
                           UserConverter converter) {
        this.userService = userService;
        this.authService = authService;
        this.converter = converter;
    }

    @CrossOrigin(allowedHeaders = "*")
    @ApiOperation(value = "Inform the parameters to register a new user", response = User.class)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user) {
        User response = userService.save(converter.convert(user));
        return ResponseEntity.status(HttpStatus.OK).body(converter.convert(response));
    }

    @CrossOrigin(allowedHeaders = "*")
    @ApiOperation(value = "Inform username and password to get a valid token", response = String.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserDTO dto) throws ValidationException {
        String response = authService.getToken(converter.convert(dto));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
