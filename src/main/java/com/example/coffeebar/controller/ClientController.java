package com.example.coffeebar.controller;


import com.example.coffeebar.entity.*;
import com.example.coffeebar.repository.RoleRepository;
import com.example.coffeebar.service.ClientService;
import com.example.coffeebar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Controller
public class ClientController {

    private ClientService clientService;

    private UserService userService;
    private RoleRepository roleRepository;
    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/client/all")
    public String getAll(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "clients";
    }

    @GetMapping("/client/add")
    public String add(Model model) {
        model.addAttribute("client", new Client());
        return "add-client";
    }

//    @PostMapping("/client/add")
//    public String add(@ModelAttribute Client client) {
//        clientService.save(client);
//        return "redirect:/client/all";
//    }

    @PostMapping("/client/add")
    public String add(@ModelAttribute Client client,
                      @RequestParam(name = "username") String username,
                      @RequestParam(name = "email") String email,
                      @RequestParam(name = "password") String password,
                      Model model) throws RoleNotFoundException {
        User userByEmail = userService.findUserByEmail(email);
        if (userByEmail != null) {
            model.addAttribute("err", "User vs email " + email + " exist in database");
            model.addAttribute("client", client);
            return "admin/add-client";
        } else {
            Client clientByPhone = clientService.getClientByPhone(client.getPhone());
            if (clientByPhone  != null) {
                model.addAttribute("err", "Користувач з таким номером вже існує");
                model.addAttribute("client", client);
                return "admin/add-client";
            }else {
                User newUser = new User(client.getName(), username, email, password);
                Role role = roleRepository.findRoleByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RoleNotFoundException("Role not found"));
                newUser.setRoles(new HashSet<>(List.of(role)));
                clientService.save(client);
                userService.adminSave(newUser);
            }
        }
        return "index2";

    }
    @GetMapping("/client/update/{id}")
    public String update(@PathVariable(name = "id") Long idClient, Model model) {
        Client clientById = clientService.findById(idClient);
        model.addAttribute("client", clientById);
        return "add-client";
    }
//    @GetMapping("/client/delete/{idClient}")
//    public String delete(@PathVariable Long idClient) {
//       clientService.deleteClientById(idClient);
//        return "redirect:/client/all";
//    }


    @GetMapping("/client/home")
    public String clientHomePage(Long idClient, Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findUserByUsername(username);
            Client client = clientService.findClientByUserId(user.getId());

            model.addAttribute("client", client);
        }
        return "/client/home-page-client";
    }

    @GetMapping("/client/information/{idClient}")
    public String getClientById(@PathVariable Long idClient, Model model) {
        Client client = clientService.findById(idClient);
        if (client != null) {
            model.addAttribute("client", client);
            return "client/client-info";
        } else {
            return "redirect:/client/all";
        }
    }


}
