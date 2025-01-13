package org.example.financial_ledger_management.config;

import org.example.financial_ledger_management.services.security.CustomUserDetailsService;
import org.example.financial_ledger_management.utils.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Конфигурация безопасности приложения.
 */
@Configuration
public class SecurityConfig {

    /**
     * Фильтр аутентификации JWT.
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Кастомный сервис для работы с пользователями.
     */
    private final CustomUserDetailsService userDetailsService;

    /**
     * Конструктор класса SecurityConfig.
     *
     * @param jwtAuthenticationFilter фильтр аутентификации JWT
     * @param userDetailsService сервис для работы с пользователями
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          CustomUserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Создает цепочку фильтров безопасности.
     *
     * @param http объект HttpSecurity для настройки безопасности
     * @return цепочка фильтров безопасности
     * @throws Exception если возникает ошибка при настройке безопасности
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Создает менеджер аутентификации.
     *
     * @param authConfig конфигурация аутентификации
     * @return менеджер аутентификации
     * @throws Exception если возникает ошибка при создании менеджера аутентификации
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Создает провайдер аутентификации.
     *
     * @return провайдер аутентификации
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Создает кодировщик паролей.
     *
     * @return кодировщик паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


