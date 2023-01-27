package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:email.properties")
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public boolean SendMessage(String sendTo, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        try {
            javaMailSender.send(simpleMailMessage);
        }
        catch (MailException ex){
            throw new BadRequestException("Error to send message " + ex.getMessage());
        }
        return true;
    }

}
