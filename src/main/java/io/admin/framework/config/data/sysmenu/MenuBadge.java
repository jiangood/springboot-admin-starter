package io.admin.framework.config.data.sysmenu;

import io.admin.framework.data.domain.BaseEntity;
import io.admin.common.utils.ann.Remark;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuBadge extends BaseEntity {


    @Remark("菜单ID")
    String menuId;

    @NotNull
    String url;

}
