package io.admin.modules.common.dto;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;

    // 验证码
    String captchaCode;

    // 外部系统登录的token
    String token;



}
