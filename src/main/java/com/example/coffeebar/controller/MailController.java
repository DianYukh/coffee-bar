package com.example.coffeebar.controller;

import com.example.coffeebar.email.test.MyMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailController {


    private final MyMailSender senderTest;
    private final JavaMailSender mailSender;
    @Autowired
    public MailController(MyMailSender senderTest, JavaMailSender mailSender) {
        this.senderTest = senderTest;
        this.mailSender = mailSender;
    }

    @GetMapping("/send/mail")
    public String sendmail(@RequestParam(name = "email") String email) {
        //senderTest.confirmRegistration(email);
        return "redirect:/";
    }

//    @PostMapping("/send/mail")
//    public String sendMail(@RequestParam(name = "name") String name,
//                           @RequestParam(name = "email") String mail,
//                           @RequestParam(name = "subject") String subject,
//                           @RequestParam(name = "message") String message) {
//
//        SimpleMailMessage email = new SimpleMailMessage();
//
//        email.setFrom(mail);
//
//        email.setTo("testingprogram@ukr.net");
//        email.setSubject(subject);
//        email.setText("From: " + name + " " + message);
//
//        try {
//            mailSender.send(email);
//            return "redirect:/";
//        } catch (MailException e) {
//
//            return "error";
//        }
//    }
}
