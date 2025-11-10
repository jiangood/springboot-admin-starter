package io.admin.framework.config.security;

import io.admin.modules.system.entity.SysUser;
import io.admin.modules.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SysUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.findByAccount(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在: " + username);
        }

        // 构造权限列表，比如 ROLE_ADMIN, USER_READ
        List<GrantedAuthority> authorities = new ArrayList<>();

        Set<String> permissions = userService.getAllPermissions(user.getId());
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        return new User(user.getAccount(), user.getPassword(), authorities);
    }
}
