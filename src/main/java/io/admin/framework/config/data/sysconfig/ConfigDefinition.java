package io.admin.framework.config.data.sysconfig;


import io.admin.common.utils.field.ValueType;
import lombok.Data;

@Data
public class ConfigDefinition {
    String id;
    String groupName;
    ValueType valueType;
    String defaultValue;
    String description;
    String label;
}
