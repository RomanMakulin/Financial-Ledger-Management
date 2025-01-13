package org.example.financial_ledger_management.services.security;

import org.example.financial_ledger_management.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Класс, представляющий детали пользователя для аутентификации.
 */
public class CustomUserDetails implements UserDetails {

    private final User user;

    /**
     * Конструктор класса CustomUserDetails.
     *
     * @param user пользователь
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Возвращает список ролей пользователя.
     *
     * @return список ролей пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Или добавить роли, если есть.
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль пользователя
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return имя пользователя
     */
    @Override
    public String getUsername() {
        return user.getLogin();
    }

    /**
     * Проверяет, не истек ли срок действия аккаунта пользователя.
     *
     * @return true, если срок действия аккаунта не истек, false в противном случае
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, не заблокирован ли аккаунт пользователя.
     *
     * @return true, если аккаунт не заблокирован, false в противном случае
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, не истек ли срок действия учетных данных пользователя.
     *
     * @return true, если срок действия учетных данных не истек, false в противном случае
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, включен ли аккаунт пользователя.
     *
     * @return true, если аккаунт включен, false в противном случае
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Возвращает пользователя.
     *
     * @return пользователь
     */
    public User getUser() {
        return user;
    }
}

