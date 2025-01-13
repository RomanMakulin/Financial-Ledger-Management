package org.example.financial_ledger_management.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.financial_ledger_management.services.security.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Фильтр аутентификации JWT.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Сервис для работы с JWT.
     */
    private final JwtUtils jwtUtils;

    /**
     * Сервис для работы с пользователями.
     */
    private final CustomUserDetailsService userDetailsService;

    /**
     * Конструктор класса JwtAuthenticationFilter.
     *
     * @param jwtUtils сервис для работы с JWT
     * @param userDetailsService сервис для работы с пользователями
     */
    public JwtAuthenticationFilter(JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Обрабатывает запрос и выполняет аутентификацию пользователя.
     *
     * @param request запрос
     * @param response ответ
     * @param filterChain цепочка фильтров
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException если возникает ошибка ввода/вывода
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = parseJwt(request);
        if (token != null && jwtUtils.validateJwtToken(token)) {
            String username = jwtUtils.getUsernameFromJwt(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Извлекает токен JWT из запроса.
     *
     * @param request запрос
     * @return токен JWT или null, если токен не найден
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}


