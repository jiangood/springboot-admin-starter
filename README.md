# springboot-admin-starter

## 介绍

本项目是一个小型管理系统框架。

### 开发环境
*   **Java**: 17
*   **Maven**: `io.github.jiangood:springboot-admin-starter`
    版本号: ![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter)
*   **NPM**: `@jiangood/springboot-admin-starter`
    版本号: ![NPM Version](https://img.shields.io/npm/v/@jiangood/springboot-admin-starter)

### 后端依赖

以下是项目的主要后端依赖：
*   `org.springframework.boot:spring-boot-starter-web`: Spring Boot Web starter.
*   `org.springframework.boot:spring-boot-starter-quartz`: Spring Boot Quartz scheduler starter.
*   `org.springframework.boot:spring-boot-starter-validation`: Spring Boot validation starter.
*   `org.springframework.boot:spring-boot-starter-aop`: Spring Boot AOP starter.
*   `org.springframework.boot:spring-boot-starter-data-jpa`: Spring Boot Data JPA starter.
*   `org.springframework.boot:spring-boot-starter-cache`: Spring Boot cache starter.
*   `org.springframework.boot:spring-boot-starter-security`: Spring Boot Security starter.
*   `org.springframework.boot:spring-boot-configuration-processor`: Spring Boot configuration processor.
*   `org.mapstruct:mapstruct`: Java bean mapping.
*   `com.jhlabs:filters`: Image processing filters.
*   `io.minio:minio`: MinIO client.
*   `com.squareup.okhttp3:okhttp-jvm`: HTTP client.
*   `javax.mail:mail`: JavaMail API.
*   `org.apache.poi:poi-ooxml`: Apache POI for Office documents (OOXML).
*   `org.apache.poi:poi-scratchpad`: Apache POI for Office documents (scratchpad).
*   `com.itextpdf:itextpdf`: iText PDF library.
*   `com.github.f4b6a3:uuid-creator`: UUID generator (uuid7).
*   `commons-dbutils:commons-dbutils`: Apache Commons DBUtils.
*   `cn.hutool:hutool-all`: HuTool utility collection.
*   `org.apache.commons:commons-lang3`: Apache Commons Lang.
*   `com.google.guava:guava`: Google Guava.
*   `commons-io:commons-io`: Apache Commons IO.
*   `com.fasterxml.jackson.dataformat:jackson-dataformat-yaml`: Jackson YAML dataformat.
*   `commons-beanutils:commons-beanutils`: Apache Commons BeanUtils.
*   `com.belerweb:pinyin4j`: Pinyin converter.
*   `org.jsoup:jsoup`: HTML parser.
*   `org.projectlombok:lombok`: Lombok library.
*   `com.mysql:mysql-connector-j`: MySQL JDBC driver.
*   `io.github.jiangood:ureport-console`: UReport console.
*   `org.flowable:flowable-spring-boot-starter-process`: Flowable process engine starter.

### 前端依赖 (peerDependencies)

以下是项目主要的前端 peerDependencies：
*   `@ant-design/icons`: Ant Design 图标库。
*   `@bpmn-io/properties-panel`: BPMN.io 属性面板。
*   `@tinymce/tinymce-react`: TinyMCE React 组件。
*   `@umijs/types`: UmiJS 类型定义。
*   `antd`: Ant Design UI 组件库。
*   `antd-img-crop`: Ant Design 图片裁剪组件。
*   `axios`: 基于 Promise 的 HTTP 客户端。
*   `bpmn-js`: BPMN 2.0 渲染工具包。
*   `bpmn-js-properties-panel`: BPMN.js 属性面板。
*   `preact`: 快速 3KB React 替代方案。
*   `dayjs`: 轻量级日期处理库。
*   `jsencrypt`: JavaScript RSA 加解密库。
*   `lodash`: JavaScript 实用工具库。
*   `qs`: URL 查询字符串解析和序列化库。
*   `react`: 用于构建用户界面的 JavaScript 库。
*   `react-dom`: React 的 DOM 渲染库。
*   `umi`: 可插拔的企业级前端应用框架。

### 菜单列表

系统菜单配置示例（完整配置请参考 `src/main/resources/application-data-framework.yml`）：
```yaml
data:
  menus:
    - name: 我的任务
      id: myFlowableTask
      path: /flowable/task
      seq: -1
      refresh-on-tab-click: true
      message-count-url: /admin/flowable/my/todoCount
      perms:
        - perm: myFlowableTask:manage
          name: 任务管理
    - id: sys
      name: 系统管理
      seq: 10000 # 系统管理排在后面
      children:
        - id: sysOrg
          name: 机构管理
          path: /system/org
          icon: ApartmentOutlined
          perms:
            - name: 保存
              perm: sysOrg:save
            - name: 查看
              perm: sysOrg:view
            - name: 删除
              perm: sysOrg:delete
        # ... 更多菜单项
```

### 业务项目配置

业务项目可以通过在 `application-data-biz.yml` 中配置 `data.configs` 来定义自己的配置项，示例：

```yaml
data:
  configs:
    - group-name: 自定义配置组
      children:
        - code: biz.myConfig
          name: 我的业务配置
          value-type: string # 可选值：string, boolean, number, json
          description: 这是一个示例业务配置项
```
