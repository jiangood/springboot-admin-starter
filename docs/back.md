# 后端模块

## 项目依赖 (pom.xml)

项目名称：`springboot-admin-starter`

| 依赖分类   | 主要依赖项                                                                                                                                                                                | 描述                                     |
| :--------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | :--------------------------------------- |
| Spring Boot | `spring-boot-starter-web`, `spring-boot-starter-quartz`, `spring-boot-starter-validation`, `spring-boot-starter-aop`, `spring-boot-starter-data-jpa`, `spring-boot-starter-cache`, `spring-boot-starter-security` | Spring Boot 核心功能、Web、定时任务、验证、AOP、JPA 数据访问、缓存、安全等 |
| 对象映射   | `org.mapstruct:mapstruct`, `org.mapstruct:mapstruct-processor`                                                                                                                              | MapStruct 对象映射工具                   |
| 对象存储   | `io.minio:minio`, `com.squareup.okhttp3:okhttp-jvm`                                                                                                                                         | MinIO 对象存储客户端                     |
| 文件处理   | `org.apache.poi:poi-ooxml`, `org.apache.poi:poi-scratchpad`, `com.itextpdf:itextpdf`                                                                                                        | Apache POI (Excel), iText PDF (PDF)      |
| 实用工具   | `com.github.f4b6a3:uuid-creator`, `commons-dbutils:commons-dbutils`, `cn.hutool:hutool-all`, `org.apache.commons:commons-lang3`, `com.google.guava:guava`, `commons-io:commons-io`, `commons-beanutils:commons-beanutils` | UUID 生成、数据库工具、Hutool 工具包、Apache Commons 家族工具、Google Guava |
| 数据格式   | `com.fasterxml.jackson.dataformat:jackson-dataformat-yaml`                                                                                                                                  | Jackson YAML 数据格式处理                |
| 其他       | `com.belerweb:pinyin4j`, `org.jsoup:jsoup`, `org.projectlombok:lombok`                                                                                                                      | 拼音转换、HTML 解析、Lombok 简化代码     |
| 数据库驱动 | `com.mysql:mysql-connector-j`                                                                                                                                                               | MySQL 数据库连接                         |
| 报表       | `io.github.jiangood:ureport-console`                                                                                                                                                        | UReport 报表控制台                       |
| 流程引擎   | `org.flowable:flowable-spring-boot-starter-process`                                                                                                                                         | Flowable 流程引擎集成                    |
| 测试       | `org.springframework.boot:spring-boot-starter-test`, `org.springframework.security:spring-security-test`, `com.h2database:h2`                                                               | Spring Boot 测试、安全测试、H2 内存数据库 |

## 树形结构工具 (src/main/java/io/admin/common/utils/tree)

| 类名        | 描述                                     | 主要用途                                                 |
| :---------- | :--------------------------------------- | :------------------------------------------------------- |
| `TreeNode`  | 树形结构中的节点定义。                     | 定义树节点的 `id`、`parentId`、`children` 等属性。       |
| `TreeManager` | 树形结构管理器。                         | 用于管理或操作树形结构，例如构建树、查找节点等。         |
| `TreeUtils` | 树形结构通用工具类。                     | 提供树形数据的构建、遍历、过滤等常用操作。               |

## JPA 查询规范构建器 (src/main/java/io/admin/framework/data/specification/Spec.java)

| 方法/功能                     | 描述                                                                    |
| :---------------------------- | :---------------------------------------------------------------------- |
| **整体功能**                  | 简洁、动态、支持关联字段查询的 JPA `Specification` 构建器，通过链式调用收集条件，最终使用 `AND` 逻辑连接。 |
| `of()`                        | 静态工厂方法，创建 `Spec` 实例。                                        |
| `addExample(...)`             | 基于 `Example` 进行模糊匹配，支持忽略指定字段。                         |
| `eq(...)`, `ne(...)`          | 等于、不等于查询。                                                      |
| `gt(...)`, `lt(...)`, `ge(...)`, `le(...)` | 大于、小于、大于等于、小于等于查询。                                    |
| `like(...)`, `notLike(...)`   | 模糊匹配 (支持前后缀通配符，不区分大小写)。                             |
| `in(...)`, `between(...)`     | 字段值在给定集合中、字段值在给定范围内。                                |
| `isNull(...)`, `isNotNull(...)` | 字段为空、不为空查询。                                                  |
| `distinct(...)`               | 设置查询结果去重。                                                      |
| `or(...)`                     | 将多个 `Specification` 通过 `OR` 连接。                                 |
| `not(...)`                    | 对 `Specification` 进行 `NOT` (取反) 操作。                             |
| `orLike(...)`                 | 常用封装：多字段 `OR` 逻辑的模糊查询。                                  |
| `isMember(...)`, `isNotMember(...)` | 检查元素是否属于实体集合字段的成员 (JPA `IS MEMBER OF`)。               |
| `groupBy(...)`, `having(...)` | 设置 `GROUP BY` 字段和 `HAVING` 过滤条件。                              |
| `toPredicate(...)`            | 核心方法，将所有收集到的 `Specification` 通过 `AND` 连接，生成最终的 `Predicate`。 |