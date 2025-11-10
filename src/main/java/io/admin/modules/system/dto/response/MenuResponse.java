package io.admin.modules.system.dto.response;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id","pid", "label"})
public class MenuResponse   {

  String id;


  String pid;

  /**
   * 根节点id， 比如用户管理的根节点为最顶层菜单系统管理， 用于前端自动切换顶层app菜单
   */
  String rootid; // 如果使用

  String label;


  String path;

  String icon;


  String key;

  boolean refreshOnTabClick;



  List<MenuResponse> children = new LinkedList<>();




}
