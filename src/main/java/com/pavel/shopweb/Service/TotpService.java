package com.pavel.shopweb.Service;

public interface TotpService {
    String generateSecret();
    String getUriForImage(String secret);
    boolean verifyCode(String code, String secret);
}
