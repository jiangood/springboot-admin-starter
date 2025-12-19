package io.github.jiangood.sa.modules.flowable.core.config.meta;

import io.github.jiangood.sa.common.tools.field.ValueType;
import lombok.Data;

@Data
public class ProcessVariable {
    private String name;
    private String label;
    private ValueType valueType;
    private boolean required = false;
}
