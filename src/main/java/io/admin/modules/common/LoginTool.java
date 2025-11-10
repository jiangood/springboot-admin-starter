package io.admin.modules.common;

import io.admin.modules.system.entity.SysUser;
import io.admin.modules.system.service.SysUserService;
import io.admin.common.utils.SpringTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Slf4j
public class LoginTool {

    public  static String getLoginAccount() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        String account = authentication.getName();
        log.debug("获取当前登录用户 {}", account);

        boolean authenticated = authentication.isAuthenticated();
        if(!authenticated || account.equals("anonymousUser")){
            return null;
        }

        return account;
    }

    public  static SysUser getLoginUser() {
        String account = getLoginAccount();
        if(account == null){
            return null;
        }

        SysUserService userService = SpringTool.getBean(SysUserService.class);
        SysUser user = userService.findByAccount(account);

        return user;
    }

    public static List<String> getOrgPermissions() {
        User principal = getUserPrincipal();
        Collection<GrantedAuthority> authorities = principal.getAuthorities();

        return authorities.stream().map(GrantedAuthority::getAuthority)
                .filter(t -> t.startsWith("ORG_"))
                .map(t -> t.substring(4))
                .toList();
    }

    public static List<String> getPermissions() {
        User principal = getUserPrincipal();
        Collection<GrantedAuthority> authorities = principal.getAuthorities();

        return authorities.stream().map(GrantedAuthority::getAuthority)
                .filter(t -> !t.startsWith("ROLE_") && !t.startsWith("ORG_"))
                .toList();
    }

    public static List<String> getRoles() {
        User principal = getUserPrincipal();
        Collection<GrantedAuthority> authorities = principal.getAuthorities();

        return authorities.stream().map(GrantedAuthority::getAuthority)
                .filter(t -> t.startsWith("ROLE_"))
                .map(t -> t.substring(5))
                .toList();
    }

    private static User getUserPrincipal() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        return principal;
    }

    public static boolean isAdmin() {
        List<String> roles = getRoles();
        return roles.contains("admin");
    }

}
