package io.github.jiangood.sa.framework.config.data.dto;


import io.github.jiangood.sa.common.tools.field.ValueType;
import lombok.Data;

@Data
public class ConfigDefinition {
    String code;
    ValueType valueType;
    String description;
    String name;
}
