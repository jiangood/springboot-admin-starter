package io.github.jiangood.sa.modules.system.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class GrantUserToRoleRequest {
    String id;
    List<String> userIdList;
}
