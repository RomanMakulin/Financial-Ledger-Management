package org.example.financial_ledger_management.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationUserDto {

    /**
     * Логин пользователя
     */
    @NotNull(message = "Login is required")
    private String login;

    /**
     * Имя пользователя.
     */
    @NotNull(message = "Firstname is required")
    private String firstname;

    /**
     * Фамилия пользователя.
     */
    @NotNull(message = "Lastname is required")
    private String lastname;

    /**
     * Отчество пользователя.
     */
    @NotNull(message = "Surname is required")
    private String surname;

    /**
     * Электронная почта пользователя.
     */
    @NotNull(message = "Email is required")
    private String email;

    /**
     * Номер телефона пользователя.
     */
    @NotNull(message = "Phone is required")
    private String phone;

    /**
     * Адрес пользователя.
     */
    private String address;

    /**
     * Пароль пользователя.
     */
    @NotNull(message = "Password is required")
    private String password;
}
