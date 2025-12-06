# 后端框架文档

## 核心配置 (pom.xml)

| 配置项 | 描述 | 值/版本 |
|---|---|---|
| `Spring Boot Parent` | Spring Boot 父项目 | `3.5.8` |
| `groupId` | Maven Group ID | `io.github.jiangood` |
| `artifactId` | Maven Artifact ID | `springboot-admin-starter` |
| `version` | 项目版本 | `0.0.2` |
| `name` | 项目名称 | `springboot-admin-starter` |
| `description` | 项目描述 | `小型管理系统框架` |
| `java.version` | Java 编译版本 | `17` |
| `hutool.version` | Hutool 工具库版本 | `5.8.41` |
| `poi.version` | Apache POI 版本 | `5.5.0` |
| `mapstruct.version` | MapStruct 版本 | `1.6.3` |
| `guava.version` | Guava 工具库版本 | `33.5.0-jre` |
| `commons-io.version` | Commons IO 工具库版本 | `2.21.0` |

### 核心依赖

| 依赖名称 | 描述 |
|---|---|
| `spring-boot-starter-web` | Web 应用开发 |
| `spring-boot-starter-quartz` | 定时任务 |
| `spring-boot-starter-validation` | 数据校验 |
| `spring-boot-starter-aop` | AOP 支持 |
| `spring-boot-starter-data-jpa` | JPA 数据持久化 |
| `spring-boot-starter-cache` | 缓存支持 |
| `spring-boot-starter-security` | 安全框架 |
| `org.mapstruct:mapstruct` | 对象映射工具 |
| `com.jhlabs:filters` | 图像处理滤镜 (用于验证码) |
| `io.minio:minio` | MinIO 对象存储客户端 |
| `org.apache.poi:poi-ooxml` | Excel 文件处理 |
| `com.itextpdf:itextpdf` | PDF 文件处理 |
| `com.github.f4b6a3:uuid-creator` | UUID 生成器 (支持 UUID7) |
| `cn.hutool:hutool-all` | 常用工具类库 (Hutool) |
| `com.fasterxml.jackson.dataformat:jackson-dataformat-yaml` | Jackson YAML 数据格式支持 |
| `com.belerweb:pinyin4j` | 汉字转拼音库 |
| `org.jsoup:jsoup` | HTML 解析器 |
| `io.github.jiangood:ureport-console` | 报表控制台 |
| `org.flowable:flowable-spring-boot-starter-process` | Flowable 工作流引擎 |

## 通用工具类 (src/main/java/io/admin/common/utils)

### `TreeNode` 接口 (io.admin.common.utils.tree.TreeNode)

| 方法名 | 描述 | 参数 | 示例 |
|---|---|---|---|
| `getId()` | 获取节点ID | 无 | `String id = node.getId();` |
| `setId(String id)` | 设置节点ID | `id`: 节点ID | `node.setId("123");` |
| `getPid()` | 获取父节点ID | 无 | `String pid = node.getPid();` |
| `setPid(String pid)` | 设置父节点ID | `pid`: 父节点ID | `node.setPid("0");` |
| `getChildren()` | 获取子节点列表 | 无 | `List<MyNode> children = node.getChildren();` |
| `setChildren(List<T> list)` | 设置子节点列表 | `list`: 子节点列表 | `node.setChildren(new ArrayList<>());` |
| `setIsLeaf(Boolean b)` | 设置是否为叶子节点 | `b`: 是否为叶子节点 | `node.setIsLeaf(true);` |

### `TreeUtils` 类 (io.admin.common.utils.tree.TreeUtils)

| 方法名 | 描述 | 参数 | 示例 |
|---|---|---|---|
| `buildTree(List<TreeOption> list)` | 构造 `TreeOption` 类型的树。 | `list`: 扁平的 `TreeOption` 列表 | `List<TreeOption> tree = TreeUtils.buildTree(optionList);` |
| `treeToMap(List<TreeOption> tree)` | 将 `TreeOption` 树转换为 Map 结构。 | `tree`: `TreeOption` 树列表 | `Map<String, TreeOption> map = TreeUtils.treeToMap(treeList);` |
| `buildTree(List<E> list, Function<E, String> keyFn, Function<E, String> pkeyFn, Function<E, List<E>> getChildren, BiConsumer<E, List<E>> setChildren)` | 通用树构造方法。 | `list`: 扁平列表<br>`keyFn`: 获取节点Key的函数<br>`pkeyFn`: 获取父节点Key的函数<br>`getChildren`: 获取子节点列表的函数<br>`setChildren`: 设置子节点列表的函数 | `TreeUtils.buildTree(dataList, Node::getId, Node::getPid, Node::getChildren, Node::setChildren);` |
| `walk(List<E> list, Function<E, List<E>> getChildren, Consumer<E> consumer)` | 深度优先遍历树节点，对每个节点执行回调。 | `list`: 树列表<br>`getChildren`: 获取子节点列表的函数<br>`consumer`: 节点处理器 | `TreeUtils.walk(treeList, Node::getChildren, node -> System.out.println(node.getName()));` |
| `getLeafs(List<E> list, Function<E, List<E>> getChildren)` | 获取一棵树的叶子节点列表。 | `list`: 树列表<br>`getChildren`: 获取子节点列表的函数 | `List<Node> leafs = TreeUtils.getLeafs(treeList, Node::getChildren);` |
| `treeToList(List<E> tree, Function<E, List<E>> getChildren)` | 将树结构转换为扁平列表。 | `tree`: 树列表<br>`getChildren`: 获取子节点列表的函数 | `List<Node> flatList = TreeUtils.treeToList(treeList, Node::getChildren);` |
| `getPids(String nodeId, List<E> list, Function<E, String> keyFn, Function<E, String> pkeyFn)` | 获取节点的父节点ID列表。 | `nodeId`: 节点ID<br>`list`: 扁平列表<br>`keyFn`: 获取节点Key的函数<br>`pkeyFn`: 获取父节点Key的函数 | `List<String> parentIds = TreeUtils.getPids("childId", allNodes, Node::getId, Node::getPid);` |

### `TreeManager` 类 (io.admin.common.utils.tree.TreeManager)

| 方法名 | 描述 | 参数 | 示例 |
|---|---|---|---|
| `of(List<X> dataList)` | 静态工厂方法，用于构建一个 `TreeManager` 实例。 | `dataList`: 实现了 `TreeNode` 接口的节点列表 | `TreeManager<MyNode> manager = TreeManager.of(nodeList);` |
| `getTree()` | 获取构建好的树形结构。 | 无 | `List<MyNode> tree = manager.getTree();` |
| `getMap()` | 获取所有节点的映射。 | 无 | `Map<String, MyNode> map = manager.getMap();` |
| `traverseTree(List<T> treeList, TraverseAction<T> traverseAction)` | 遍历树结构，对每个节点执行指定操作。 | `treeList`: 树列表<br>`traverseAction`: 遍历操作回调 | `manager.traverseTree(manager.getTree(), node -> System.out.println(node.getName()));` |
| `traverseTreeFromLeaf(TraverseAction<T> traverseAction)` | 从最深子节点到父节点遍历树。 | `traverseAction`: 遍历操作回调 | `manager.traverseTreeFromLeaf(node -> System.out.println(node.getId()));` |
| `getParentById(String id)` | 根据ID获取节点的父节点。 | `id`: 节点ID | `MyNode parent = manager.getParentById("childId");` |
| `isLeaf(String id)` | 判断指定ID的节点是否为叶子节点。 | `id`: 节点ID | `boolean isLeaf = manager.isLeaf("nodeId");` |
| `getLeafList()` | 获取所有叶子节点列表。 | 无 | `List<MyNode> leafs = manager.getLeafList();` |
| `getParentIdListById(String id)` | 根据节点ID获取其所有父节点的ID列表。 | `id`: 节点ID | `List<String> pids = manager.getParentIdListById("childId");` |
| `getIdsByLevel(int level)` | 通过层级获取节点ID列表。 | `level`: 层级 | `List<String> level2Ids = manager.getIdsByLevel(2);` |

## 数据传输对象 (src/main/java/io/admin/common/dto)

### `AjaxResult` 类 (io.admin.common.dto.AjaxResult)

| 字段名 | 类型 | 描述 | 示例 |
|---|---|---|---|
| `success` | `boolean` | 表示操作是否成功。 | `true` |
| `code` | `Integer` | 响应状态码，`SUCCESS` (200) 表示成功，`FAILURE` (500) 表示失败。 | `200` |
| `data` | `Object` | 响应携带的数据。 | `{"id": 1, "name": "Test"}` |
| `message` | `String` | 响应消息。 | `"操作成功"` |
| `extData` | `Map<String, Object>` | 动态扩展数据。 | `{"statusLabel": "已处理"}` |

### `DropdownRequest` 类 (io.admin.common.dto.DropdownRequest)

| 字段名 | 类型 | 描述 | 示例 |
|---|---|---|---|
| `searchText` | `String` | 搜索文本。 | `"搜索内容"` |
| `selected` | `List<String>` | 已选中的值列表，方便前端显示。 | `["value1", "value2"]` |

## JPA Specification 构建 (src/main/java/io/admin/framework/data/specification)

### `ExpressionUtils` 类 (io.admin.framework.data.specification.ExpressionUtils)

| 方法名 | 描述 | 参数 | 示例 |
|---|---|---|---|
| `getPath(Root<?> root, String field)` | 根据字段路径（支持点分隔，如"dept.name"）获取对应的 JPA `Expression`。 | `root`: JPA `Root` 对象<br>`field`: 字段路径 | `Expression<?> path = ExpressionUtils.getPath(root, "user.department.name");` |

### `Spec` 类 (io.admin.framework.data.specification.Spec)

| 方法名 | 描述 | 参数 | 示例 |
|---|---|---|---|
| `of()` | 静态工厂方法，创建 `Spec` 实例。 | 无 | `Spec<User> spec = Spec.of();` |
| `eq(String field, Object value)` | 添加等于 (`=`) 条件。 | `field`: 字段名<br>`value`: 值 | `spec.eq("name", "张三");` |
| `like(String field, String value)` | 添加模糊查询 (`LIKE %value%`) 条件 (不区分大小写)。 | `field`: 字段名<br>`value`: 值 | `spec.like("userName", "admin");` |
| `in(String field, Collection<?> values)` | 添加 `IN` 条件。 | `field`: 字段名<br>`values`: 值集合 | `spec.in("id", Arrays.asList(1, 2, 3));` |
| `between(String field, C value1, C value2)` | 添加 `BETWEEN` 条件。 | `field`: 字段名<br>`value1`: 起始值<br>`value2`: 结束值 | `spec.between("age", 18, 30);` |
| `or(Specification<T>... orSpecifications)` | 将多个 `Specification` 用 `OR` 连接。 | `orSpecifications`: 多个 `Specification` | `spec.or(Spec.of().eq("field1", "a"), Spec.of().eq("field2", "b"));` |
| `orLike(String value, String... fields)` | 常用封装：OR 逻辑的模糊查询 (多个字段 `LIKE %value%`)。 | `value`: 模糊查询的值<br>`fields`: 多个字段名 | `spec.orLike("keyword", "title", "content");` |
| `groupBy(String... fields)` | 设置 `GROUP BY` 字段。 | `fields`: 字段名数组 | `spec.groupBy("department.id");` |
| `having(Specification<T> havingSpec)` | 设置 `HAVING` 过滤条件。 | `havingSpec`: 包含聚合函数条件的 `Specification` | `spec.groupBy("dept").having(Spec.of().gt("count(id)", 10));` |
| `toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb)` | 核心方法，将所有条件通过 AND 连接构建成 JPA `Predicate`。 | `root`: JPA `Root` 对象<br>`query`: JPA `CriteriaQuery` 对象<br>`cb`: JPA `CriteriaBuilder` 对象 | (内部调用，无需用户直接使用) |

## 框架配置 (src/main/resources/application-data-framework.yml)

### `data.configs` 配置项

| 分组名称 | 配置项 Code | 名称 | 描述 | 值类型 |
|---|---|---|---|---|
| 邮件配置 | `email.from` | 邮件发送账号 | - | - |
| | `email.pass` | 邮件发送密码 | - | - |
| 网站配置 | `sys.copyright` | 版权信息 | - | - |
| | `sys.loginBackground` | 登录背景图 | - | - |
| | `sys.loginBoxBottomTip` | 登录框下面的提示 | - | - |
| | `sys.title` | 站点标题 | - | - |
| | `sys.waterMark` | 开启水印 | 在所有页面增加水印 | `boolean` |
| 系统配置 | `sys.baseUrl` | 请求基础地址 | 非必填，可用于拼接完整请求地址 | - |
| | `sys.jwtSecret` | jwt密码 | - | - |

### `data.menus` 菜单结构

- **我的任务**: `/flowable/task`，消息计数URL `/admin/flowable/my/todoCount`，权限 `myFlowableTask:manage`。
- **系统管理**: `id: sys`，包含以下子菜单：
  - **机构管理**: `/system/org`，图标 `ApartmentOutlined`，权限 `sysOrg:save`, `sysOrg:view`, `sysOrg:delete`。
  - **用户管理**: `/system/user`，图标 `UserOutlined`，权限 `sysUser:view`, `sysUser:save`, `sysUser:delete`, `sysUser:resetPwd`, `sysUser:grantPerm`。
  - **角色管理**: `/system/role`，图标 `IdcardOutlined`，权限 `sysRole:save`。
  - **操作手册**: `/system/sysManual`，图标 `CopyOutlined`，权限 `sysManual:view`, `sysManual:delete`, `sysManual:save`。
  - **系统配置**: `/system/config`，图标 `SettingOutlined`，权限 `sysConfig:view`, `sysConfig:save`。
  - **数据字典**: `/system/dict`，图标 `FileSearchOutlined`，权限 `sysDict:view`, `sysDict:save`, `sysDict:delete`。
  - **存储文件**: `/system/file`，图标 `FolderOpenOutlined`，权限 `sysFile:view`, `sysFile:delete`。
  - **作业调度**: `/system/job`，图标 `OrderedListOutlined`，权限 `job:view`, `job:save`, `job:triggerJob`, `job:jobLogClean`。
  - **操作日志**: `/system/log`，图标 `FileSearchOutlined`，权限 `sysLog:view`。
  - **接口管理**: `/system/api`，图标 `ApiOutlined`，权限 `api`。
  - **流程引擎**: `/flowable`，权限 `flowableModel:design`, `flowableModel:deploy`, `flowableTask:setAssignee`, `flowableInstance:close`。
  - **报表管理**: `/ureport`，图标 `TableOutlined`，权限 `ureport:view`, `ureport:design`。
