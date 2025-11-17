package io.admin.framework.config.security;

import io.admin.modules.system.dto.response.UserResponse;
import io.admin.modules.system.entity.SysUser;
import io.admin.modules.system.service.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class SysUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService userService;



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername {}",username);
        SysUser user = userService.findByAccount(username);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
        if(!user.getEnabled()){
            throw new UsernameNotFoundException("用户被禁用: " + username);
        }

        // 构造权限列表，比如 ROLE_ADMIN, USER_READ
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<String> permissions = userService.getAllPermissions(user.getId());
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        LoginUser loginUser = new LoginUser(user.getAccount(), user.getPassword(), authorities);

        UserResponse dto = userService.findOneDto(user.getId());
        loginUser.setId(dto.getId());
        loginUser.setDeptId(dto.getDeptId());
        loginUser.setDeptName(dto.getDeptLabel());
        loginUser.setUnitId(dto.getUnitId());
        loginUser.setUnitName(dto.getUnitLabel());
        loginUser.setName(dto.getName());

        return loginUser;
    }
}
