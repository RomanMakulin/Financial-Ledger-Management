package org.example.financial_ledger_management.services.auth;

import org.example.financial_ledger_management.model.User;
import org.example.financial_ledger_management.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(String username, String password) {
        User user = new User(username, password);
        return "success";
    }

}
