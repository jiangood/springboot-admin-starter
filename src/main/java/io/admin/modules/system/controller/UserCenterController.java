package io.admin.modules.system.controller;

import io.admin.common.dto.AjaxResult;
import io.admin.modules.system.dto.request.UpdatePwdRequest;
import io.admin.modules.system.dto.response.UserResponse;
import io.admin.modules.system.service.SysUserService;
import io.admin.framework.perm.SecurityUtils;
import io.admin.framework.perm.Subject;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("userCenter")
public class UserCenterController {

    @Resource
    private  SysUserService sysUserService;



    @RequestMapping("info")
    public AjaxResult info(){
        Subject subject = SecurityUtils.getSubject();
        String name = subject.getName();

        UserResponse user = sysUserService.findOneDto(subject.getId());

        return AjaxResult.ok().data("name",name)
                .data("phone",user.getPhone())
                .data("dept",user.getDeptLabel())
                .data("unit",user.getUnitLabel())
                .data("roles", user.getRoleNames())
                .data("email", user.getEmail())
                .data("account", user.getAccount())
                .data("createTime", user.getCreateTime())
                ;
    }




    @PostMapping("updatePwd")
    public AjaxResult updatePwd(@RequestBody UpdatePwdRequest request) {
        String userId = SecurityUtils.getSubject().getId();
        String newPassword = request.getNewPassword();
        sysUserService.updatePwd(userId,  newPassword);
        return AjaxResult.ok();
    }
}
