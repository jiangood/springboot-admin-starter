package io.admin.framework.config.security;

import io.admin.common.utils.ArrayUtils;
import io.admin.common.utils.PasswordUtils;
import io.admin.framework.config.security.ajax.AjaxFormLoginConfigurer;
import io.admin.framework.config.security.ajax.AjaxLoginFilter;
import io.admin.framework.config.SysProp;
import io.admin.modules.common.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Slf4j
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final SysProp sysProp;

    // 配置 HTTP 安全
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AjaxLoginFilter loginFilter) throws Exception {
        String[] loginExclude = ArrayUtils.toStrArr(sysProp.getXssExcludePathList());

        // 前后端分离项目，关闭csrf
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authz -> {
            if (loginExclude.length > 0) {
                authz.requestMatchers(loginExclude).permitAll();
            }
            authz.requestMatchers("/admin/auth/**", "/admin/public/**").permitAll()
                    .requestMatchers("/admin/**").authenticated()
                    .anyRequest().permitAll();

        });
        http.sessionManagement(cfg -> {
            int maximumSessions = sysProp.getMaximumSessions();
            log.info("设置最大并发会话数为 {}", maximumSessions);

            cfg.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
            cfg.sessionConcurrency(conCfg -> {
                conCfg.maximumSessions(maximumSessions)
                        //达到限制时，新登录失败
                        .maxSessionsPreventsLogin(true)
                        .sessionRegistry(sessionRegistry());
            });
        });




        AjaxFormLoginConfigurer<HttpSecurity> configurer = new AjaxFormLoginConfigurer<>(loginFilter);
        http.with(configurer, cfg->{});

        return http.build();

    }


    // 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordUtils.getPASSWORD_ENCODER();
    }


    @Bean
    public AjaxLoginFilter loginFilter(AuthService loginService, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        AjaxLoginFilter loginFilter = new AjaxLoginFilter(loginService,authenticationConfiguration.getAuthenticationManager());
        loginFilter.setSecurityContextRepository(securityContextRepository());
        return loginFilter;
    }

    @Bean
    @ConditionalOnMissingBean // 这样用户可以自定义redisSession等来覆盖
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    /**
     * **重要**：注册一个 SessionRegistry Bean
     * SessionRegistry 用于记录和管理所有的 Session 信息，是并发控制的基础。
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        // 使用标准的 SessionRegistryImpl 即可
        return new SessionRegistryImpl();
    }


}
