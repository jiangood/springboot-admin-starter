package io.admin.framework.config.security.ajax;

import io.admin.modules.common.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 自定义的登录逻辑
 */
@Slf4j
public class AjaxLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthService authService;


    public AjaxLoginFilter(AuthService authService, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authService = authService;
    }

    /**
     * 重写认证尝试方法，用于拦截登录请求并解密密码
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            authService.preHandler(request);

            Authentication authentication = super.attemptAuthentication(request, response);

            authService.onSuccess(authentication.getName());
            return authentication;
        }catch (Exception e){
            String username = this.obtainUsername(request);
            authService.onFail(username);
            if(e instanceof AuthenticationException ae){
                throw ae;
            }

            throw  new AuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return (String) request.getAttribute("username");
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return (String) request.getAttribute("password");
    }



}
