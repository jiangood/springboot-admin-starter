package io.admin.modules.flowable.system;



import io.admin.modules.system.entity.SysOrg;
import io.admin.modules.system.service.SysOrgService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;


@Component
public class LoginUserProviderImpl implements FlowableLoginUserProvider {

    @Resource
    SysOrgService sysOrgService;

    @Resource
    SysUserService sysUserService;


    @Override
    public FlowableLoginUser currentLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        UserResponse user = sysUserService.findOneDto(subject.getId());

        FlowableLoginUser fu = new FlowableLoginUser();
        fu.setId(subject.getId());
        fu.setName(subject.getName());
        fu.setSuperAdmin(subject.hasPermission("*"));
        fu.setDeptId(subject.getDeptId());
        fu.setDeptName(user.getDeptLabel());
        fu.setUnitName(subject.getUnitId());
        fu.setUnitName(user.getUnitLabel());


        // 获取部门领导
        if(fu.getDeptId() != null){
            SysOrg org = sysOrgService.findOne(fu.getDeptId());

            if(org != null){
                SysUser deptLeader = org.getLeader();
                if(deptLeader != null){
                    fu.setDeptLeaderId(deptLeader.getId());
                }
            }
        }

        return fu;
    }

}
