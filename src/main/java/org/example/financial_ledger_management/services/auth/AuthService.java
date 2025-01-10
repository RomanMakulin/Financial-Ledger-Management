package org.example.financial_ledger_management.services.auth;

import org.example.financial_ledger_management.model.User;
import org.example.financial_ledger_management.model.dto.RegistrationUserDto;

/**
 * Интерфейс для аутентификации пользователей.
 */
public interface AuthService {

    /**
     * Регистрирует нового пользователя.
     *
     * @param registrationUserDto данные для регистрации пользователя
     * @return сообщение о результате регистрации
     */
    String registerUser(RegistrationUserDto registrationUserDto);

    /**
     * Аутентифицирует пользователя.
     *
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return сообщение о результате аутентификации с токеном
     */
    String loginUser(String login, String password);

    /**
     * Возвращает аутентифицированного пользователя.
     *
     * @return аутентифицированный пользователь
     */
    User getAuthenticatedUser();
}

