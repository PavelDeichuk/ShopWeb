package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Service.CryptoService;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Service
public class CryptoServiceImpl implements CryptoService {

    private SecretKey key;

    @Override
    public SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        key = keyGenerator.generateKey();
        return key;
    }

    @Override
    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(128));
        byte[] cipherText = cipher.doFinal(data.getBytes());
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }
}
