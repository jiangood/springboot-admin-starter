package io.admin.modules.api.entity;

import io.admin.common.utils.ann.Remark;
import io.admin.framework.data.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Remark("访客权限")
@Entity
@FieldNameConstants
@Getter
@Setter
@Table(name = "sys_api_account_resource", uniqueConstraints = @UniqueConstraint(columnNames = {"account_id", "resource_id"}))
public class ApiAccountResource extends BaseEntity {

    @NotNull
    @ManyToOne
    ApiAccount account;



    @NotNull
    @ManyToOne
    ApiResource resource;


    @NotNull
    Boolean enable;

}
