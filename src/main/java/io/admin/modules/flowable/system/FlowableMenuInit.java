package io.admin.modules.flowable.system;

import io.tmgg.init.SystemHook;
import io.tmgg.modules.system.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class FlowableMenuInit implements SystemHook {

    @Resource
    SysMenuService sysMenuService;

    @Override
    public void afterDataInit() {

    }


}
