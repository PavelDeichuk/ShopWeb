package com.pavel.shopweb.Service;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public interface CryptoService {
    SecretKey generateKey(int n) throws NoSuchAlgorithmException;
    String encrypt(String data) throws Exception;

}
