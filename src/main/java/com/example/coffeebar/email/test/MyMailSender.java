package com.example.coffeebar.email.test;

import com.example.coffeebar.entity.User;
import com.example.coffeebar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MyMailSender {

    private final UserService userService;

    private final JavaMailSender mailSender;

    @Autowired
    public MyMailSender(UserService userService, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }


    public void confirmRegistration(String mail, User user) {
        String token = UUID.randomUUID().toString();

        userService.createVerificationToken(user, token);

        String confirmURL = "/confirmRegistration?token=" + token;
        String msg = "Перейдіть за цим посиланням для підтвердження реєстрації";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(mail);
        email.setFrom("testingprogram@ukr.net");
        email.setSubject("Registration Confirmation");
        email.setText(msg + " " + "http://localhost:8080" + confirmURL);
        mailSender.send(email);
    }

    public void confirmPassword(String mail, User user) {
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String confirmURL = "/restorePassword?token=" + token + "&user_id=" + user.getId();
        String msg = "Перейдіть за цим посиланням для відновлення паролю";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(mail);
        email.setFrom("testingprogram@ukr.net");
        email.setSubject("Password Confirmation");
        email.setText(msg + " " + "http://localhost:8080" + confirmURL);
        mailSender.send(email);
    }

    public void sendMail(String name, String mail, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("testingprogram@ukr.net");
        email.setFrom(mail);
        email.setSubject(subject);
        email.setText("From: " + name + " " + message);
        mailSender.send(email);
    }
}
