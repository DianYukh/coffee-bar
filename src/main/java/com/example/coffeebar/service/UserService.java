package com.example.coffeebar.service;

import com.example.coffeebar.entity.Client;
import com.example.coffeebar.entity.ERole;
import com.example.coffeebar.entity.Role;
import com.example.coffeebar.entity.User;
import com.example.coffeebar.repository.ClientRepository;
import com.example.coffeebar.repository.RoleRepository;
import com.example.coffeebar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, ClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User adminSave(User user) throws RoleNotFoundException {
        if (user != null) {
            User saveUser = user;
            saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(saveUser);
            return saveUser;
        }
        return user;
    }


    public User save(User user) throws RoleNotFoundException {
        if (user != null) {
            User saveUser = user;
            saveUser.setPassword(passwordEncoder.encode(user.getPassword()));
            Role role = roleRepository.findRoleByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Role not found"));
            saveUser.setRoles(new HashSet<>(List.of(role)));
            saveUser = userRepository.save(saveUser);
            return saveUser;
        }
        return user;
    }


    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
        //.orElseThrow(() -> new UsernameNotFoundException("User vs email " + email + " not found"));
    }


    public User findUserByUsername(String principalName) {
        return userRepository.findUserByUsername(principalName).orElse(null);
    }
}
