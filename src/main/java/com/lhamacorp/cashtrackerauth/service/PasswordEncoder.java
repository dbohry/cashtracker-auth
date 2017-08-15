package com.lhamacorp.cashtrackerauth.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    public String encode(String pass) {
        return DigestUtils.sha256Hex(pass);
    }

}
