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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
