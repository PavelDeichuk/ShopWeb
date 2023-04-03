package com.pavel.shopweb.Service;

public interface EmailService {
    boolean SendMessage(String sendTo, String subject, String message);
}
