package io.admin.framework.config.data;

import lombok.Data;

/**
 * 为了方便yml解析，增加的根节点
 */
@Data
public class DataRoot {
    private DataProp data;
}
