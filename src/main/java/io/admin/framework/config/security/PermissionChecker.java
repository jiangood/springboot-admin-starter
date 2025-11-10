package io.admin.framework.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service("permissionChecker") // 【重要】指定 Bean 名称，供 SpEL 调用
public class PermissionChecker {

    /**
     * 检查当前用户是否拥有传入的任何一个权限。
     * * @param authoritiesString 逗号分隔的权限字符串 (如 "product:write,user:admin")
     * @return 如果拥有其中任何一个权限，则返回 true
     */
    public boolean hasAnyPermission(String authoritiesString) {
        // 1. 获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // 2. 将传入的字符串转换为 Set
        Set<String> requiredPermissions = Arrays.stream(authoritiesString.split(","))
            .map(String::trim)
            .collect(Collectors.toSet());

        // 3. 获取用户当前拥有的权限集合
        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();

        Set<String> userPermissionStrings = userAuthorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toSet());

        // 4. 检查用户权限集合与所需权限集合是否有交集
        // 只有拥有所需权限集合中的任何一个权限，即可通过
        return userPermissionStrings.stream().anyMatch(requiredPermissions::contains);
    }
}
