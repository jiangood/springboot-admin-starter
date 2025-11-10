package io.admin.common.utils.tree.drag;

import lombok.Data;

@Data
public class DragDropEvent {

    String dropKey;
    String dragKey;
    int dropPosition;
    boolean dropToGap; // 两个节点的关系，true表示平级

}
