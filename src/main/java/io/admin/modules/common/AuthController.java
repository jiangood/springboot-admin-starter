package io.admin.modules.common;// src/main/java/com/example/controller/AuthController.java

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import io.admin.framework.config.SysProp;
import io.admin.common.dto.AjaxResult;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/admin/auth")
@AllArgsConstructor
public class AuthController {

    public static final String CAPTCHA_CODE = "captchaCode";



    @Resource
    SysProp prop;

    @Resource
    CodeGenerator codeGenerator;




    @PostMapping("logout")
    public AjaxResult logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        if (request.getSession(false) != null) {
            request.getSession(false).invalidate();
        }

        return AjaxResult.ok();
    }




    @GetMapping("captchaImage")
    public void captcha(HttpSession session, HttpServletResponse resp) throws IOException {
        try {
            ICaptcha captcha = CaptchaUtil.createLineCaptcha(100, 50, codeGenerator, 100);
            captcha.write(resp.getOutputStream());

            String code = captcha.getCode();
            session.setAttribute(CAPTCHA_CODE, code);
        } catch (Exception e) {
            log.error("生成验证码失败，将验证码参数设置为禁用");
        }
    }


}
