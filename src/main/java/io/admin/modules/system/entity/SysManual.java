package io.admin.modules.system.entity;

import io.admin.common.utils.ann.Remark;
import io.admin.framework.data.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Remark("操作手册")
@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_sys_manual", columnNames = {"name","version"})})
@FieldNameConstants
public class SysManual extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Remark("名称")
    @Column(length = 100)
    String name;

    @NotNull
    @Remark("版本")
    @Positive(message = "版本号必须为正整数")
    Integer version;

    @Remark("文件")
    @Column(length = 32)
    String fileId;

}
