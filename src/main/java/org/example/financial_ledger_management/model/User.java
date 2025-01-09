package org.example.financial_ledger_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Представляет пользователя в системе управления финансовым учетом.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    /**
     * Имя для входа пользователя.
     */
    private String login;

    /**
     * Имя пользователя.
     */
    private String firstname;

    /**
     * Фамилия пользователя.
     */
    private String lastname;

    /**
     * Отчество пользователя.
     */
    private String surname;

    /**
     * Электронная почта пользователя.
     */
    private String email;

    /**
     * Номер телефона пользователя.
     */
    private String phone;

    /**
     * Адрес пользователя.
     */
    private String address;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Список счетов пользователя.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

}

