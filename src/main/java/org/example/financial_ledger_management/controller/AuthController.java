package org.example.financial_ledger_management.controller;

import jakarta.validation.Valid;
import org.example.financial_ledger_management.model.dto.RegistrationUserDto;
import org.example.financial_ledger_management.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для аутентификации пользователей.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Конструктор класса AuthController.
     *
     * @param authService сервис для аутентификации пользователей
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Регистрирует нового пользователя.
     *
     * @param registrationUserDto данные для регистрации пользователя
     * @return ответ с сообщением о результате регистрации
     */
    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody @Valid RegistrationUserDto registrationUserDto) {
        return ResponseEntity.ok(authService.registerUser(registrationUserDto));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password) {
        return ResponseEntity.ok(authService.loginUser(login, password));
    }

}


