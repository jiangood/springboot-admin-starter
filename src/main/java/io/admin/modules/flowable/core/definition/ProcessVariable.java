package io.admin.modules.flowable.core.definition;

import io.admin.common.utils.field.ValueType;
import lombok.Data;

@Data
public class ProcessVariable {
    private String name;
    private String label;
    private ValueType valueType;
    private boolean required = false;
}
