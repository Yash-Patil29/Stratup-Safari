package com.example.StartupSafari.service;
import com.example.StartupSafari.model.User;
import com.example.StartupSafari.model.Role;
import com.example.StartupSafari.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllInvestors() {
        return userRepository.findByRole(Role.INVESTOR);
    }
}
