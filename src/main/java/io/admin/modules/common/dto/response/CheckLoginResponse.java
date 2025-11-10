package io.admin.modules.common.dto.response;

import lombok.Data;

@Data
public class CheckLoginResponse {

    private boolean isLogin;
    private boolean needUpdatePwd;
}
