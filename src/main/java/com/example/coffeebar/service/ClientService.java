package com.example.coffeebar.service;

import com.example.coffeebar.entity.Client;
import com.example.coffeebar.entity.Personal;
import com.example.coffeebar.entity.User;
import com.example.coffeebar.repository.ClientRepository;
import com.example.coffeebar.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
public class ClientService {


    private ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public ClientService(ClientRepository clientRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {
        if (client != null) {
            clientRepository.save(client);
        }
    }



    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long idClient) {
        return clientRepository.findClientByIdClient(idClient);
    }
    
    public void deleteClientById(Long idClient) {
        clientRepository.deleteById(idClient);
    }

//    public Client findClientByUsername(String username) {
//        return clientRepository.findClientByUsername(username);
//    }

    public Client getClientByPhone(String phone) {
        return clientRepository.getClientByPhone(phone);
    }

    public Client findClientByUserId(Long userId) {
        return clientRepository.findClientByUserId(userId);
    }
}
