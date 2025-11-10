package io.admin.modules.system.service;

import io.admin.modules.system.entity.SysMenuBadge;
import io.admin.framework.data.service.BaseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SysMenuBadgeService extends BaseService<SysMenuBadge> {

    @Resource
    SysMenuService sysMenuService;


}

