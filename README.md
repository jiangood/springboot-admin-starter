# SpringBoot Admin Starter 开发文档

## 目录
- [1 介绍](#1-介绍)
- [2 前端](#2-前端)
- [3 后端](#3-后端)
- [4 模板代码](#4-模板代码)

---

## 1 介绍

**SpringBoot Admin Starter** 是一个功能完备的小型管理系统框架，旨在为业务项目提供快速开发的解决方案。该框架集成了前后端分离架构，提供了统一的用户管理、权限控制、数据访问、文件上传、作业调度、流程引擎等功能。

### 开发环境

- **Java**: 17+
- **Spring Boot**: 3.5.8
- **Node.js**: 19+
- **React**: 19+
- **Ant Design**: 6.x
- **数据库**: MySQL 8.0+

### 版本信息

后端最新版本为： ![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter)

前端最新版本为： ![NPM Version](https://img.shields.io/npm/v/@jiangood/springboot-admin-starter)

### 后端依赖

| 依赖项 | 版本 | 描述 |
|--------|------|------|
| `org.springframework.boot:spring-boot-starter-web` | 3.5.8 | Web 框架 |
| `org.springframework.boot:spring-boot-starter-quartz` | 3.5.8 | 任务调度 |
| `org.springframework.boot:spring-boot-starter-data-jpa` | 3.5.8 | 数据持久化 |
| `org.springframework.boot:spring-boot-starter-security` | 3.5.8 | 安全框架 |
| `org.springframework.boot:spring-boot-starter-validation` | 3.5.8 | 参数验证 |
| `org.mapstruct:mapstruct` | 1.6.3 | 对象映射工具 |
| `org.apache.poi:poi-ooxml` | 5.5.0 | Excel 操作 |
| `cn.hutool:hutool-all` | 5.8.41 | Java 工具包 |
| `com.google.guava:guava` | 33.5.0-jre | Google 工具库 |
| `org.flowable:flowable-spring-boot-starter-process` | 7.2.0 | 工作流引擎 |
| `com.mysql:mysql-connector-j` | - | MySQL 驱动 |

### 前端依赖

| 依赖项 | 版本 | 描述 |
|--------|------|------|
| `antd` | ^6.0.0 | React UI 组件库 |
| `react` | ^19.0.0 | React 框架 |
| `react-dom` | ^19.0.0 | React DOM |
| `axios` | ^1.13.2 | HTTP 客户端 |
| `umi` | ^4.0.0 | React 应用框架 |
| `@ant-design/icons` | ^5.4.0 | 图标库 |
| `dayjs` | ^1.11.13 | 时间处理库 |
| `lodash` | ^4.17.21 | 工具函数库 |
| `bpmn-js` | ^18.7.0 | BPMN 流程图引擎 |
| `@tinymce/tinymce-react` | ^6.0.0 | 富文本编辑器 |

---

## 2 前端

前端框架提供了丰富的组件和工具，用于构建现代化的管理系统。

### 框架组件

框架提供了多个基础组件：

- **`Page`**: 页面基础布局组件
- **`ProTable`**: 高级表格组件，支持分页、搜索等功能
- **`ProModal`**: 高级模态框组件
- **`FieldUploadFile`**: 文件上传组件
- **`DownloadFileButton`**: 文件下载按钮组件
- **`LinkButton`**: 链接按钮组件
- **`Ellipsis`**: 文本省略组件
- **`PageLoading`**: 页面加载组件
- **`ButtonList`**: 按钮列表组件
- **`NamedIcon`**: 命名图标组件

### 系统管理组件

- **`system`**: 系统管理相关组件
- **`ValueType`**: 值类型组件
- **`view`**: 视图组件
- **`field-components`**: 字段组件
- **`view-components`**: 视图组件

### 工具函数

- **`HttpUtils`**: HTTP 请求工具类
- **`TreeUtils`**: 树形结构工具类

---

## 3 后端

### 配置参考

框架的核心配置位于 `application-data-framework.yml` 文件中，包含了数据库配置元数据、菜单配置等功能。

### 菜单列表

框架预定义了系统管理相关的菜单项，包括：

- **我的任务**: 任务管理相关页面
- **系统管理**: 包含机构管理、用户管理、角色管理、操作手册、系统配置、数据字典、存储文件、作业调度、操作日志、接口管理、流程引擎、报表管理等模块
  - 机构管理 (`sysOrg`)
  - 用户管理 (`sysUser`)
  - 角色管理 (`sysRole`)
  - 操作手册 (`sysManual`)
  - 系统配置 (`sysConfig`)
  - 数据字典 (`sysDict`)
  - 存储文件 (`sysFile`)
  - 作业调度 (`job`)
  - 操作日志 (`sysLog`)
  - 接口管理 (`api`)
  - 流程引擎 (`flowableModel`)
  - 报表管理 (`ureport`)

### 业务项目如何配置

业务项目可以在 `application-data-biz.yml` 中配置自定义菜单和配置项。

### 数据访问工具

- **`Spec.java`**: 简洁、动态、支持关联字段查询的 JPA Specification 构建器。支持多种查询操作，如 `eq`(等于)、`ne`(不等于)、`like`(模糊匹配)、`in`(包含)、`between`(区间)、`isNotNull`(非空) 等操作符，以及 `or`、`not` 等逻辑操作。

- **`JdbcUtils.java`**: Spring Boot 环境下的原生 SQL 工具类，基于 Spring 的 JdbcTemplate 封装，支持 `findOne`、`findAll`、`update`、`insert`、`delete` 等操作。

### 树结构工具

- **`TreeUtils.java`**: 提供树形结构构建和处理功能，包括 `buildTree`(构建树)、`treeToMap`(树转Map)、`walk`(遍历树)、`getLeafs`(获取叶子节点) 等方法。

### 注解工具

- **`RemarkUtils.java`**: 注解工具类，用于获取 `@Remark` 注解的值，支持字段、方法、类、枚举等。

### Excel工具

- **`ExcelUtils.java`**: Excel 导入导出工具类，支持通过注解映射字段，提供 `importExcel` 和 `exportExcel` 方法。

### ID生成器

- **`GenerateUuidV7.java`**: UUIDv7 注解，用于生成基于时间的UUID
- **`GeneratePrefixedSequence.java`**: 带前缀的序列生成注解
- **`UuidV7IdGenerator.java`**: UUIDv7 ID生成器实现
- **`PrefixedSequenceGenerator.java`**: 带前缀序列生成器实现
- **`DailyTableGenerator.java`**: 每日表生成器

### 数据转换器

提供多种数据类型转换器：

- **`BaseConverter.java`**: 基础转换器
- **`BaseToListConverter.java`**: 转换为列表基础类
- **`ToBigDecimalListConverter.java`**: 转换为BigDecimal列表
- **`ToIntListConverter.java`**: 转换为整数列表
- **`ToLongListConverter.java`**: 转换为长整数列表
- **`ToMapConverter.java`**: 转换为Map
- **`ToListConverter.java`**: 转换为列表
- **`ToSetConverter.java`**: 转换为集合
- **`BaseCodeEnumConverter.java`**: 枚举转换器

### 验证器

框架内置多种验证器：

- **`ValidateCarDrivingLicence.java`**: 驾驶证验证
- **`ValidateChineseName.java`**: 中文姓名验证
- **`ValidateCreditCode.java`**: 统一社会信用代码验证
- **`ValidateIdNum.java`**: 身份证号码验证
- **`ValidateMobile.java`**: 手机号码验证
- **`ValidatePassword.java`**: 密码验证
- **`ValidateDate.java`**: 日期验证
- **`ValidateIp.java`**: IP地址验证
- **`ValidateZipCode.java`**: 邮政编码验证
- 等等

### 登录工具

- **`LoginUtils.java`**: 登录用户工具类，提供获取当前用户信息、权限、角色等方法，包括 `getUserId`、`getUser`、`getPermissions`、`getRoles`、`isAdmin` 等。

### 任务调度

- **`BaseJob.java`**: 基础任务类，所有任务都需要继承此基类
- **`JobDescription.java`**: 任务描述注解，用于标注任务信息
- **`HelloWorldJob.java`**: 任务示例
- **`JobParamFieldProvider.java`**: 动态任务参数提供者

### 流程引擎

- **`LeaveProcessListener.java`**: 流程监听器示例
- **`application-process.yml`**: 流程定义配置，支持请假流程等业务流程的定义和监听

流程配置包括流程key、名称、监听器、变量定义和表单定义。

---

## 4 模板代码

以下是基于 `SysManual` 实体的模板代码示例，业务项目可参考这些模板进行开发：

### 实体类模板 (`${entityName}.java`)

```java
package io.admin.modules.system.entity;

import io.admin.common.utils.annotation.Remark;
import io.admin.framework.data.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * ${entityRemark}实体类
 * 注意：(name, version) 必须全局唯一
 */
@Remark("${entityRemark}")
@Entity
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(name = "uk_${entityNameLower}", columnNames = {"name", "version"})})
@FieldNameConstants
public class ${entityName} extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称，最大长度100字符，不能为空
     */
    @NotNull
    @Remark("名称")
    @Column(length = 100)
    @Size(max = 100, message = "名称长度不能超过100个字符")
    private String name;

    /**
     * 版本号，必须为正整数，不能为空
     */
    @NotNull
    @Remark("版本")
    @Positive(message = "版本号必须为正整数")
    private Integer version;

    /**
     * 文件ID，最大长度32字符
     */
    @Remark("文件")
    @Column(length = 32)
    @Size(max = 32, message = "文件ID长度不能超过32个字符")
    private String fileId;

}
```

### DAO层模板 (`${entityName}Dao.java`)

```java
package io.admin.modules.system.dao;

import io.admin.framework.data.repository.BaseDao;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.system.entity.${entityName};
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class ${entityName}Dao extends BaseDao<${entityName}> {

    public int findMaxVersion(String name){
        Spec<${entityName}> q = Spec.<${entityName}>of().eq(${entityName}.Fields.name, name);

        ${entityName} e = this.findTop1(q, Sort.by(Sort.Direction.DESC, ${entityName}.Fields.version));

        return e == null ? 0 : e.getVersion();
    }

}
```

### Service层模板 (`${entityName}Service.java`)

```java
package io.admin.modules.system.service;

import io.admin.framework.data.service.BaseService;
import io.admin.modules.system.dao.${entityName}Dao;
import io.admin.modules.system.entity.${entityName};
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${entityName}Service extends BaseService<${entityName}> {

    @Resource
    ${entityName}Dao dao;

    @Override
    public ${entityName} saveOrUpdateByRequest(${entityName} input, List<String> updateKeys) throws Exception {
        if(input.isNew()){
            int maxVersion = dao.findMaxVersion(input.getName());
            input.setVersion(maxVersion+1);
        }

        return super.saveOrUpdateByRequest(input, updateKeys);
    }
}
```

### Controller层模板 (`${entityName}Controller.java`)

```java
package io.admin.modules.system.controller;

import io.admin.common.dto.AjaxResult;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.config.security.HasPermission;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.system.entity.${entityName};
import io.admin.modules.system.service.${entityName}Service;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/${entityNameLower}")
public class ${entityName}Controller {

    @Resource
    ${entityName}Service service;

    @HasPermission("${entityNameLower}:view")
    @RequestMapping("page")
    public AjaxResult page(String searchText, @PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) throws Exception {
        Spec<${entityName}> q = Spec.of();
        q.orLike(searchText, ${entityName}.Fields.name);

        Page<${entityName}> page = service.findPageByRequest(q, pageable);

        return AjaxResult.ok().data(page);
    }

    @HasPermission("${entityNameLower}:save")
    @PostMapping("save")
    public AjaxResult save(@RequestBody ${entityName} input, RequestBodyKeys updateFields) throws Exception {
        service.saveOrUpdateByRequest(input, updateFields);
        return AjaxResult.ok().msg("保存成功");
    }

    @HasPermission("${entityNameLower}:delete")
    @RequestMapping("delete")
    public AjaxResult delete(String id) {
        service.deleteByRequest(id);
        return AjaxResult.ok().msg("删除成功");
    }
}
```

### 配置文件模板 (`application-data-framework.yml`)

```yaml
## 框架的核心配置项和菜单结构。 业务项目需在 application-data-biz.yml 中进行配置。
data:
  # 数据库配置元数据
  configs:
    - group-name: ${entityRemark}配置
      children:
        - code: ${entityNameLower}.enable
          name: 启用${entityRemark}
        - code: ${entityNameLower}.default
          name: 默认${entityRemark}设置
  # 菜单配置
  menus:
    - id: ${entityNameLower}
      name: ${entityRemark}
      path: /system/${entityNameLower}
      icon: FileTextOutlined
      perms:
        - perm: ${entityNameLower}:view
          name: 查看
        - perm: ${entityNameLower}:delete
          name: 删除
        - perm: ${entityNameLower}:save
          name: 保存
```

### 前端页面模板 (`${entityNameLower}/index.jsx`)

```jsx
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm} from 'antd'
import React from 'react'
import {ButtonList, FieldUploadFile, HttpUtils, Page, ProTable} from "../../../framework";

export default class extends React.Component {

    state = {
        formValues: {},
        formOpen: false
    }

    formRef = React.createRef()
    tableRef = React.createRef()

    columns = [
        {
            title: '名称',
            dataIndex: 'name',
        },
        {
            title: '版本',
            dataIndex: 'version',
            render(version) {
                return 'v' + version;
            }
        },
        {
            title: '文件',
            dataIndex: 'fileId',
            render(id){
               const url = 'admin/sysFile/preview/' + id;
                return <a href={url} target='_blank'>查看文件</a>
            }
        },
        {
            title: '操作',
            dataIndex: 'option',
            render: (_, record) => (
                <ButtonList>
                    <Button size='small' perm='${entityNameLower}:save' onClick={() => this.handleEdit(record)}>编辑</Button>
                    <Popconfirm perm='${entityNameLower}:delete' title='是否确定删除${entityRemark}'  onConfirm={() => this.handleDelete(record)}>
                        <Button size='small'>删除</Button>
                    </Popconfirm>
                </ButtonList>
            ),
        },
    ]

    handleAdd = () => {
        this.setState({formOpen: true, formValues: {}})
    }

    handleEdit = record => {
        this.setState({formOpen: true, formValues: record})
    }

    onFinish = values => {
        HttpUtils.post('admin/${entityNameLower}/save', values).then(rs => {
            this.setState({formOpen: false})
            this.tableRef.current.reload()
        })
    }

    handleDelete = record => {
        HttpUtils.get('admin/${entityNameLower}/delete', {id: record.id}).then(rs => {
            this.tableRef.current.reload()
        })
    }

    render() {
        return <Page>
            <ProTable
                actionRef={this.tableRef}
                toolBarRender={(params, {selectedRows, selectedRowKeys}) => {
                    return <ButtonList>
                        <Button perm='${entityNameLower}:save' type='primary' onClick={this.handleAdd}>
                            <PlusOutlined/> 新增
                        </Button>
                    </ButtonList>
                }}
                request={(params) => HttpUtils.get('admin/${entityNameLower}/page', params)}
                columns={this.columns}
            />

            <Modal title='${entityRemark}'
                   open={this.state.formOpen}
                   onOk={() => this.formRef.current.submit()}
                   onCancel={() => this.setState({formOpen: false})}
                   destroyOnHidden
                   maskClosable={false}
            >
                <Form ref={this.formRef} labelCol={{flex: '100px'}}
                      initialValues={this.state.formValues}
                      onFinish={this.onFinish}
                >
                    <Form.Item name='id' noStyle></Form.Item>

                    <Form.Item label='名称' name='name' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='文件' name='fileId' rules={[{required: true}]}>
                        <FieldUploadFile accept=".pdf" maxCount={1} />
                    </Form.Item>
                </Form>
            </Modal>
        </Page>
    }
}
```