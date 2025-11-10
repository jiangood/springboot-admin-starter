
package io.admin.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleTreeNode implements io.admin.common.utils.tree.TreeNode<SimpleTreeNode> {

    private String id;

    private String pid;



    private String title;



    private List<SimpleTreeNode> children;


    public SimpleTreeNode(String id, String pid, String title) {
        this.id = id;
        this.pid = pid;
        this.title = title;
    }
}
