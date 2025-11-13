package io.admin.framework.config.security;

import io.admin.modules.common.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

/**
 * 自定义的登录逻辑
 */
@Slf4j
@AllArgsConstructor
public class LoginFilter implements Filter {

    private final AuthService authService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String uri = request.getRequestURI();
        if (!uri.equals("/admin/auth/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String username = request.getParameter("username");

        try {
            authService.preHandler(request);

            filterChain.doFilter(servletRequest, servletResponse);

            authService.onSuccess(username);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户[{}]认证失败： {}", username, e.getMessage());
            authService.onFail(username);
            if (e instanceof AuthenticationException ae) {

                throw ae;
            }
            throw new BadCredentialsException(e.getMessage());
        }

    }


}
