package io.admin.framework.perm;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// 仅部分字段有getter， setter
@Getter
@Setter
@Slf4j
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;
    private String account;

    private String name;
    private String unitId; // 所属机构， 如公司，非内部小组或部门
    private String deptId;


    // ------------- 权限相关----------

    private Set<String> permissions = new HashSet<>();
    private Set<String> roles = new HashSet<>();





    private Set<String> orgPermissions = new HashSet<>(); // 数据权限

    public Collection<String> getOrgPermissions() {
        return orgPermissions;
    }

    public boolean hasOrgPermission(String orgId) {
        if (orgId == null || orgId.isEmpty()) {
            return false;
        }


        return orgPermissions.contains(orgId);
    }




    public boolean hasPermission(String input) {
        if (StringUtils.isEmpty(input) ) {
            return true;
        }

        if(CollectionUtils.isEmpty(permissions)){
            return false;
        }

        if (permissions.contains("*")) {
            return true;
        }

        // 处理url的情况
        if (input.contains("/")) {
            input = StringUtils.removeStart(input, "/");
            input = StringUtils.removeEnd(input, "/");
            input = StringUtils.replace(input, "/", ":");
        }

        return permissions.contains(input);
    }


    @Override
    public String toString() {
        return "用户 " + account;
    }






}
