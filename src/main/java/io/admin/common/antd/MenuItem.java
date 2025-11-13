package io.admin.common.antd;

// https://ant.design/components/menu-cn#itemtype

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItem {
    Boolean danger; //	展示错误状态样式	boolean	false
    Boolean disabled; //	是否禁用	boolean	false
    String extra; //	额外节点	ReactNode	-	5.21.0
    AntDesignIcon icon; //	菜单图标	ReactNode	-
    String key; //item 的唯一标志	string	-
    String label; //	菜单项标题	ReactNode	-
    String title; //    设置收缩时展示的悬浮标题 string


    List<MenuItem> children = new ArrayList<>();


    // 额外字段，用于组装树
    @JsonIgnore
    String parentKey;


    // === 额外信息, 由于react的特性，这里全部用小写 ========

    @JsonProperty("root_id")
    String rootId;

    @JsonProperty("refresh_on_tab_click")
    Boolean refreshOnTabClick;

    String path;

}
