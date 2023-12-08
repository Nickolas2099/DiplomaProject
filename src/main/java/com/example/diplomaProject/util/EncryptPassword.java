package com.example.diplomaProject.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
@RequiredArgsConstructor
public class EncryptPassword {

    public char [] encryptPassword(char [] password) {

        char [] encrypted = new char[password.length];
        for(int i = 0; i < password.length; i++) {
            byte [] bytes = DigestUtils.md5Digest(new byte[] {(byte) password[i]});
            System.arraycopy(new String (bytes).toCharArray(), 0, encrypted, i, 1);
        }

        return encrypted;
    }
}
