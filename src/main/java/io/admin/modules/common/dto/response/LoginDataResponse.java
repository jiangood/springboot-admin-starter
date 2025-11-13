package io.admin.modules.common.dto.response;

import io.admin.modules.system.dto.response.SysDictTreeResponse;
import lombok.Data;

import java.util.List;

@Data
public class LoginDataResponse {

    private boolean login;
    private boolean needUpdatePwd;


    private  List<SysDictTreeResponse> dictTree;

    private LoginInfoResponse loginInfo;
}
