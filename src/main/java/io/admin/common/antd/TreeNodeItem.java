package io.admin.common.antd;

// https://ant.design/components/tree-cn#treenode-props

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNodeItem {

    Boolean checkable; //	当树为 checkable 时，设置独立节点是否展示 Checkbox	boolean	-
    Boolean disableCheckbox;//	禁掉 checkbox	boolean	false
    Boolean disabled;//	禁掉响应	boolean	false
    String icon;//	自定义图标。可接收组件，props 为当前节点 props	ReactNode | (props) => ReactNode	-
    @JsonProperty("isLeaf")
    Boolean isLeaf;//	设置为叶子节点 (设置了 loadData 时有效)。为 false 时会强制将其作为父节点	boolean	-
    String key;//	被树的 (default)ExpandedKeys / (default)CheckedKeys / (default)SelectedKeys 属性所用。注意：整个树范围内的所有节点的 key 值不能重复！	string	(内部计算出的节点位置)
    Boolean selectable;//	设置节点是否可被选中	boolean	true
    String title;//	标题	ReactNode	---

    List<TreeNodeItem> children;


    // DirectoryTree props
    String expandAction; //	目录展开逻辑，可选：false | click | doubleClick	string | boolean	click

    // 额外字段，方便构建树
    @JsonIgnore
    String parentKey;

    // 直接设置icon前端无法显示的，设置成这个，前端再转换为ReactNode
    String iconName;


    // 兼容选择框
    //https://ant.design/components/tree-select-cn#treenode-props
    public String getValue() {
        return key;
    }
}
