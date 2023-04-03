package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Entity.UsersDetailEntity;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Repository.UsersDetailRepository;
import com.pavel.shopweb.Service.EmailService;
import com.pavel.shopweb.Service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {

    private final UsersDetailRepository usersDetailRepository;

    private final EmailService emailService;

    private static final String CRON = "0 0 11 * * *";

    public SchedulerServiceImpl(UsersDetailRepository usersDetailRepository, EmailService emailService) {
        this.usersDetailRepository = usersDetailRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        LocalDateTime date = LocalDateTime.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<UsersDetailEntity> list = usersDetailRepository.findByMatchMonthAndMatchDay(month, day);
        list
                .stream()
                .forEach(lists -> {
                    try{
                        String message = "Happy Birthday dear " + lists.getFirstname() + "!";
                        emailService.SendMessage(lists.getUsersEntity().getEmail(), "Happy Birthday!", message);
                        log.info("message is send for email " + lists.getUsersEntity().getEmail());
                    }
                    catch (Exception ex){
                        log.error("Error to send message " + ex.getMessage());
                        throw new BadRequestException("Error to send message!" + ex.getMessage());
                    }
                });
    }
}
