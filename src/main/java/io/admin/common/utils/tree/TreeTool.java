package io.admin.common.utils.tree;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 将列表转换为树,请使用TreeManager
 */
public class TreeTool {


    public static List<Dict> buildTree(List<Dict> list) {
        return buildTree(list, e -> e.getStr("key"), e -> e.getStr("parentKey"), (e, children) -> e.set("children", children));
    }


    /**
     * 构造树
     *
     * @param list
     * @param idFn
     * @param pidFn
     * @param <E>
     * @return
     */
    public static <E> List<E> buildTree(List<E> list, Function<E, String> idFn, Function<E, String> pidFn, BiConsumer<E, List<E>> setChildrenFn) {
        // 1. 建立 ID 到节点的映射 (方便快速找到父节点对象)
        Map<String, E> idMap = new HashMap<>();
        for (E e : list) {
            idMap.put(idFn.apply(e), e);
        }

        // 2. 收集每个父节点下的所有子节点 (使用 PID 作为 Key)
        Map<String, List<E>> childrenMap = new HashMap<>();
        // 用于存放最终的根节点
        List<E> rootNodes = new ArrayList<>();

        for (E e : list) {
            String pid = pidFn.apply(e);
            E parent = idMap.get(pid); // 尝试获取父节点对象

            if (parent == null) {
                // 如果父节点不存在 (通常是 PID 为 null/空字符串，或者找不到对应ID)，则认为是根节点
                rootNodes.add(e);
            } else {
                // 是子节点，加入 childrenMap
                // computeIfAbsent 保证了如果列表不存在则创建，并返回该列表供 add 使用
                childrenMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(e);
            }
        }

        // 3. 将收集好的子节点列表设置给对应的父节点
        for (Map.Entry<String, List<E>> entry : childrenMap.entrySet()) {
            String pid = entry.getKey();
            List<E> children = entry.getValue();
            E parent = idMap.get(pid);

            // 理论上 parent 不会为 null，但为了健壮性检查一下
            if (parent != null) {
                // 只调用一次 setChildrenFn，传入完整的子节点列表
                setChildrenFn.accept(parent, children);
            }
        }

        return rootNodes;
    }


    /**
     * 递归树并处理子树下的节点
     *
     * @param consumer 节点处理器
     */
    public static <E> void walk(List<E> list, Function<E, List<E>> getChildren, Consumer<E> consumer) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        for (E e : list) {
            consumer.accept(e);
            List<E> children = getChildren.apply(e);
            walk(children, getChildren, consumer);
        }
    }

    /**
     * 递归树并处理子树下的节点
     *
     * @param consumer 节点处理器, 两个参数，分别是节点，节点的父节点
     */
    public static <E> void walk(List<E> list, Function<E, List<E>> getChildren, BiConsumer<E, E> consumer) {
        walk(null, list, getChildren, consumer);
    }

    private static <E> void walk(E parent, List<E> list, Function<E, List<E>> getChildren, BiConsumer<E, E> consumer) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        for (E e : list) {
            consumer.accept(e, parent);
            List<E> children = getChildren.apply(e);
            walk(e, children, getChildren, consumer);
        }
    }

    /**
     * 获取一棵树的叶子
     *
     * @param <E>
     * @return
     */
    public static <E> List<E> getLeafs(List<E> list, Function<E, List<E>> getChildren) {
        List<E> result = new ArrayList<>();
        walk(list, getChildren, e -> {
            List<E> children = getChildren.apply(e);
            boolean isLeaf = CollUtil.isEmpty(children);
            if (isLeaf) {
                result.add(e);
            }
        });

        return result;
    }




    public static <E> List<E> treeToList(List<E> tree, Function<E, List<E>> getChildren) {
        List<E> list = new ArrayList<>();
        walk(tree, getChildren, e -> list.add(e));
        return list;
    }


    /**
     * 获取节点的父节点列表
     *
     * @param <E>
     * @param list  注意不是树，而是列表
     * @return
     */
    public static <E> List<String> getPids(String nodeId, List<E> list, Function<E, String> idFn, Function<E, String> pidFn) {
        Map<String,E> idMap = new HashMap<>();
        for (E e : list) {
            idMap.put(idFn.apply(e), e);
        }
        E node = idMap.get(nodeId);
        if(node == null){
            return Collections.emptyList();
        }

        List<String> pids = new ArrayList<>();

        String pid = pidFn.apply(node);
        E parent = idMap.get(pid);
        while (parent != null) {
            pids.add(pid);
            pid = pidFn.apply(parent);
            parent = idMap.get(pid);
        }


        return  pids;

    }

}
