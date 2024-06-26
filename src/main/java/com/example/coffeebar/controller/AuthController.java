package com.example.coffeebar.controller;


import com.example.coffeebar.email.test.MyMailSender;
import com.example.coffeebar.entity.User;
import com.example.coffeebar.entity.VerificationToken;
import com.example.coffeebar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.RoleNotFoundException;
import java.security.Principal;

@Controller
public class AuthController {

    private final UserService userService;

    private final MyMailSender myMailSender;


    @Autowired
    public AuthController(UserService userService, MyMailSender myMailSender) {
        this.userService = userService;
        this.myMailSender = myMailSender;
    }


    @GetMapping("/")
    public String index(Principal principal, Model model) {

        if (principal != null) {
            String principalName = principal.getName();
            User userByUsername = userService.findUserByUsername(principalName);
            if (userByUsername != null) {
                System.out.println("userByUsername = " + userByUsername.getUsername());
                model.addAttribute("user", userByUsername);
            }
        }
        return "index2";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/registration")
    public String reg(Model model) {
        model.addAttribute("user", new User());
        return "registrationOld";
    }


    @PostMapping("/registration")
    public String reg(@ModelAttribute User user, Model model) throws RoleNotFoundException {
        model.addAttribute("mail", user.getEmail());
        user.setConfirm(false);
        userService.save(user);
        myMailSender.confirmRegistration(user.getEmail(), user);
        return "confirm";
    }

    @GetMapping("/confirmRegistration")
    public String confirmRegistration(@RequestParam(name = "token") String token, Model model) throws RoleNotFoundException {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            model.addAttribute("tokenError", "Token is not valid");
            return "error";
        }
        User userByToken = userService.getUserByToken(token);
        userByToken.setConfirm(true);
        userService.saveRegisteredUser(userByToken);
        return "redirect:/";
    }


    @GetMapping("/confirmPassword")
    public String confirmPasswordEmail() {
        return "confirm-password-email";
    }

    @PostMapping("/confirmPassword")
    public String confirmPasswordEmail(@RequestParam(name = "email") String email, Model model) {

        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail == null) {
            model.addAttribute("err", "User not found");
            return "confirm-password-email";
        } else {
            myMailSender.confirmPassword(email, userByEmail);
            return "confirm";
        }
    }

    @GetMapping("/restorePassword")
    public String confirmPassword(@RequestParam(name = "token") String token,
                                  @RequestParam(name = "user_id") Long userId,
                                  Model model) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            model.addAttribute("tokenError", "Token is not valid");
            return "error";
        }
        System.out.println("restorePassword userId = " + userId);
        model.addAttribute("user_id", userId);
        return "confirm-password";
    }

    @PostMapping("/newPassword")
    public String confirmNewPassword(@RequestParam(name = "password") String password,
                                     @RequestParam(name = "user_id") Long userId) {
        User userById = userService.findUserById(userId);
        userById.setPassword(password);
        userService.saveUserPassword(userById);
        return "redirect:/";
    }
}
