package org.example.financial_ledger_management.controller;

import jakarta.validation.Valid;
import org.example.financial_ledger_management.model.dto.RegistrationUserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/registration")
    public String registration(@RequestBody @Valid RegistrationUserDto registrationUserDto) {
        // TODO доделать + проверить безопасность как работает и работает ли вообще.
        // TODO + добавить вход
    }

}
