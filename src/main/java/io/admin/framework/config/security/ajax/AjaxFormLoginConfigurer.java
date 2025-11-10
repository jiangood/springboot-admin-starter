package io.admin.framework.config.security.ajax;

import io.admin.common.dto.AjaxResult;
import io.admin.common.utils.ResponseUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public final class AjaxFormLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractAuthenticationFilterConfigurer<H, AjaxFormLoginConfigurer<H>, AjaxLoginFilter> {
    public static final String URL = "/admin/auth/login";

    public AjaxFormLoginConfigurer(AjaxLoginFilter filter) {
        super(filter, URL);
        filter.setRequiresAuthenticationRequestMatcher(createLoginProcessingUrlMatcher(getLoginProcessingUrl()));

        this.failureHandler((request, response, exception) -> ResponseUtils.response(response, AjaxResult.err(401, "登录失败：" + getMsg(exception))));
        this.successHandler((request, response, authentication) -> ResponseUtils.response(response, AjaxResult.ok().msg("登录成功")));

    }

    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
        return PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, loginProcessingUrl);
    }

    public String getMsg(AuthenticationException e) {


        return e.getMessage();
    }

}
