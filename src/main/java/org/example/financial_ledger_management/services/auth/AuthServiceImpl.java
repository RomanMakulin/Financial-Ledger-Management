package org.example.financial_ledger_management.services.auth;

import org.example.financial_ledger_management.model.User;
import org.example.financial_ledger_management.model.dto.RegistrationUserDto;
import org.example.financial_ledger_management.repository.UserRepository;
import org.example.financial_ledger_management.services.CustomUserDetails;
import org.example.financial_ledger_management.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Сервис для аутентификации пользователей.
 */
@Service
public class AuthServiceImpl implements AuthService {

    /**
     * Репозиторий пользователей.
     */
    private final UserRepository userRepository;

    /**
     * Менеджер аутентификации.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Утилита для работы с JWT.
     */
    private final JwtUtils jwtUtils;

    /**
     * Конструктор класса AuthService.
     *
     * @param userRepository репозиторий пользователей
     */
    public AuthServiceImpl(UserRepository userRepository,
                           AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Регистрирует нового пользователя.
     *
     * @param registrationUserDto данные для регистрации пользователя
     * @return сообщение о результате регистрации
     */
    @Override
    @Transactional
    public String registerUser(RegistrationUserDto registrationUserDto) {

        if (userRepository.existsByLogin(registrationUserDto.getLogin())) {
            return "user with this login already exists";
        }

        User newUser = createUser(registrationUserDto);
        return "User registered successfully: " + newUser;
    }

    /**
     * Аутентифицирует пользователя.
     *
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return сообщение о результате аутентификации
     */
    @Override
    public String loginUser(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );
        String token = jwtUtils.generateToken(authentication.getName());
        return "Login successful! Token: " + token;
    }

    /**
     * Возвращает аутентифицированного пользователя.
     *
     * @return аутентифицированный пользователь
     */
    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Создает нового пользователя на основе данных для регистрации.
     *
     * @param registrationUserDto данные для регистрации пользователя
     * @return новый пользователь
     */
    private User createUser(RegistrationUserDto registrationUserDto){
        User newUser = new User();

        newUser.setLogin(registrationUserDto.getLogin());
        newUser.setPassword(new BCryptPasswordEncoder().encode(registrationUserDto.getPassword()));
        newUser.setEmail(registrationUserDto.getEmail());
        newUser.setPhone(registrationUserDto.getPhone());
        newUser.setLastname(registrationUserDto.getLastname());
        newUser.setFirstname(registrationUserDto.getFirstname());
        newUser.setSurname(registrationUserDto.getSurname());
        Optional.ofNullable(registrationUserDto.getAddress()).ifPresent(newUser::setAddress); // Единственное необязательное поле. Остальные поля валидируются в контроллере

        userRepository.save(newUser);
        return newUser;
    }

}

