package com.lhamacorp.cashtrackerauth.controller;

import com.lhamacorp.cashtrackerauth.entity.user.User;
import com.lhamacorp.cashtrackerauth.entity.user.UserConverter;
import com.lhamacorp.cashtrackerauth.entity.user.UserDTO;
import com.lhamacorp.cashtrackerauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ct/secure")
public class UserController {

    private UserService service;
    private UserConverter converter;

    @Autowired
    public UserController(UserService service,
                          UserConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> get(@PathVariable("id") Long id,
                                       @RequestHeader("authorization") String token) {
        User response = service.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(converter.convert(response));
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id,
                                          @RequestBody UserDTO userDTO,
                                          @RequestHeader("authorization") String token) {
        User response = service.save(converter.convert(userDTO));
        return ResponseEntity.status(HttpStatus.OK).body(converter.convert(response));
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id,
                       @RequestHeader("authorization") String token) {
        service.delete(id);
    }

}
