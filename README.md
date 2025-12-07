# SpringBoot Admin Starter 开发文档

## 1 介绍

本项目是一个后端管理系统的框架，供业务项目使用。它基于Spring Boot 3.5.8构建，提供了一套完整的后台管理系统解决方案，包括用户认证、权限控制、数据管理、任务调度、流程引擎等功能。

### 版本信息

- 后端最新版本为： ![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter)
- 前端最新版本为： ![NPM Version](https://img.shields.io/npm/v/@jiangood/springboot-admin-starter)

### 开发环境

- Java 17
- Spring Boot 3.5.8
- Node.js (前端构建)
- MySQL 数据库

### 后端依赖

| 依赖项 | 版本 | 描述 |
|--------|------|------|
| `spring-boot-starter-web` |  | Web应用基础依赖 |
| `spring-boot-starter-data-jpa` |  | JPA数据访问依赖 |
| `spring-boot-starter-security` |  | 安全认证依赖 |
| `spring-boot-starter-quartz` |  | 任务调度依赖 |
| `spring-boot-starter-validation` |  | 参数校验依赖 |
| `hutool-all` | 5.8.41 | 工具类库 |
| `poi-ooxml` | 5.5.0 | Excel操作 |
| `mapstruct` | 1.6.3 | 对象映射工具 |
| `minio` | 8.6.0 | 文件存储服务 |
| `flowable-spring-boot-starter-process` | 7.2.0 | 流程引擎 |
| `mysql-connector-j` |  | MySQL数据库连接器 |
| `lombok` |  | 代码简化工具 |

### 前端依赖

| 依赖项 | 版本 | 描述 |
|--------|------|------|
| `antd` | ^6.0.0 | UI组件库 |
| `react` | ^19.0.0 | 前端框架 |
| `axios` | ^1.13.2 | HTTP客户端 |
| `dayjs` | ^1.11.13 | 日期处理库 |
| `lodash` | ^4.17.21 | JavaScript工具库 |
| `umi` | ^4.0.0 | 前端构建工具 |

## 2 前端

前端框架提供了一系列组件和工具类，用于快速构建管理界面。

### 组件文档

| 组件名称 | 参数 | 说明 | 举例 |
|----------|------|------|------|
| `Gap` | `n` | 上下间隔组件，n为间隔大小 | `<Gap n={2} />` |
| `Page` | `padding`, `backgroundGray` | 页面容器，可设置内边距和背景色 | `<Page padding={true} />` |
| `ProModal` | `title`, `width`, `children` | 专业模态框组件 | `<ProModal title="标题" width={600}>...</ProModal>` |
| `ProTable` | `columns`, `request`, `rowKey` | 专业表格组件，支持分页、筛选 | `<ProTable columns={columns} request={...} />` |
| `FieldTableSelect` | `url`, `columns`, `placeholder` | 下拉表格选择组件 | `<FieldTableSelect url="..." columns={...} />` |

### 工具类文档

| 工具类名称 | 说明 | 举例 |
|------------|------|------|
| `DateUtils` | 日期处理工具 | `DateUtils.format(date, 'YYYY-MM-DD')` |
| `StringUtils` | 字符串处理工具 | `StringUtils.random(16)` |
| `HttpUtils` | HTTP请求工具 | `HttpUtils.get('api/data')` |
| `MessageUtils` | 消息提示工具 | `MessageUtils.success('操作成功')` |
| `StorageUtils` | 本地存储工具 | `StorageUtils.setItem('key', 'value')` |
| `TreeUtils` | 树形结构处理工具 | `TreeUtils.buildTree(list)` |

### 视图组件

| 组件名称 | 参数 | 说明 | 举例 |
|----------|------|------|------|
| `ViewText` | - | 文本显示组件 | `<ViewText value="文本内容" />` |
| `ViewImage` | - | 图片显示组件 | `<ViewImage value="图片路径" />` |
| `ViewBoolean` | - | 布尔值显示组件 | `<ViewBoolean value={true} />` |

### 表单字段组件

| 组件名称 | 参数 | 说明 | 举例 |
|----------|------|------|------|
| `FieldBoolean` | - | 布尔字段组件 | `<FieldBoolean />` |
| `FieldDate` | - | 日期字段组件 | `<FieldDate />` |
| `FieldDictSelect` | - | 字典选择字段组件 | `<FieldDictSelect dictType="status" />` |
| `FieldUploadFile` | - | 文件上传组件 | `<FieldUploadFile />` |
| `FieldRemoteSelect` | - | 远程选择字段组件 | `<FieldRemoteSelect url="..." />` |

### 后端

#### 菜单列表

框架预定义了系统管理菜单，包括：

- 系统设置
- 用户管理
- 角色管理
- 机构管理
- 数据字典
- 操作手册
- 作业调度
- 操作日志
- 流程引擎
- 报表管理

#### 业务项目如何配置

业务项目需要在 `application-data-biz.yml` 文件中进行自定义配置，包括菜单、权限和数据配置。

### 核心功能说明

#### 1. 数据查询规范 (Spec.java)

`Spec.java` 提供了一个简洁、动态的JPA查询构建器，支持关联字段查询和多种操作符。

**示例代码：**
```java
Spec<User> spec = Spec.of()
    .eq("name", "admin")
    .like("email", "test")
    .between("createTime", startDate, endDate);
List<User> users = userDao.findAll(spec);
```

#### 2. 树形结构处理 (TreeUtils.java)

提供树形结构的构建、遍历、转换等功能。

**示例代码：**
```java
List<TreeOption> tree = TreeUtils.buildTree(list);
List<User> leafs = TreeUtils.getLeafs(tree, TreeOption::getChildren);
```

#### 3. 注解工具 (RemarkUtils.java)

用于获取类、字段、方法上的 `@Remark` 注解值。

**示例代码：**
```java
String remark = RemarkUtils.getRemark(User.class.getDeclaredField("name"));
```

#### 4. Excel工具 (ExcelUtils.java)

提供Excel导入导出功能。

**示例代码：**
```java
// 导入
List<User> users = ExcelUtils.importExcel(User.class, inputStream);

// 导出
ExcelUtils.exportExcel(User.class, users, outputStream);
```

#### 5. JDBC工具 (JdbcUtils.java)

提供原生SQL操作的便捷方法。

**示例代码：**
```java
// 查询
List<User> users = jdbcUtils.findAll(User.class, "SELECT * FROM user WHERE age > ?", 18);

// 插入
jdbcUtils.insert("user", userObject);
```

#### 6. ID生成 (id包)

提供多种ID生成策略实现。

#### 7. 数据转换器 (converter包)

提供各种数据类型转换器，如列表、集合、映射等。

#### 8. 自定义验证注解 (validator包)

提供一系列自定义验证注解，如手机号、身份证、密码强度等验证。

#### 9. 登录工具 (LoginUtils.java)

提供获取当前登录用户信息的工具方法。

**示例代码：**
```java
String userId = LoginUtils.getUserId();
LoginUser user = LoginUtils.getUser();
List<String> permissions = LoginUtils.getPermissions();
```

#### 10. 任务调度 (job包)

提供基于Quartz的任务调度功能。

**示例代码：**
```java
@JobDescription(label = "定时任务", params = {@FieldDescription(name = "param1", label = "参数1")})
public class MyJob extends BaseJob {
    @Override
    public String execute(JobDataMap data, Logger logger) throws Exception {
        logger.info("执行定时任务");
        return "success";
    }
}
```

#### 11. 配置管理 (DataProperties.java)

用于配置系统设置和菜单定义。

#### 12. 流程引擎 (flowable)

集成Flowable工作流引擎，支持流程设计、部署和执行。

## 3 模板代码

以下是一个学生实体的通用代码模板：

### 3.1 实体类 (Student.java)

```java
package io.admin.modules.system.entity;

import io.admin.common.utils.annotation.Remark;
import io.admin.framework.data.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Remark("学生")
@Entity
@Getter
@Setter
@Table(name = "student")
@FieldNameConstants
public class Student extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Remark("姓名")
    @Column(length = 50)
    @Size(max = 50, message = "姓名长度不能超过50个字符")
    private String name;

    @Remark("年龄")
    private Integer age;

    @Remark("班级")
    @Column(length = 50)
    @Size(max = 50, message = "班级长度不能超过50个字符")
    private String className;
}
```

### 3.2 数据访问层 (StudentDao.java)

```java
package io.admin.modules.system.dao;

import io.admin.framework.data.repository.BaseDao;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.system.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {
    public int countByClassName(String className) {
        Spec<Student> q = Spec.<Student>of().eq(Student.Fields.className, className);
        return this.count(q);
    }
}
```

### 3.3 业务逻辑层 (StudentService.java)

```java
package io.admin.modules.system.service;

import io.admin.framework.data.service.BaseService;
import io.admin.modules.system.dao.StudentDao;
import io.admin.modules.system.entity.Student;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends BaseService<Student> {
    @Resource
    StudentDao dao;
}
```

### 3.4 控制器层 (StudentController.java)

```java
package io.admin.modules.system.controller;

import io.admin.common.dto.AjaxResult;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.config.security.HasPermission;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.system.entity.Student;
import io.admin.modules.system.service.StudentService;
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
@RequestMapping("admin/student")
public class StudentController {
    @Resource
    StudentService service;

    @HasPermission("student:view")
    @RequestMapping("page")
    public AjaxResult page(String searchText, @PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) throws Exception {
        Spec<Student> q = Spec.of();
        q.orLike(searchText, Student.Fields.name);
        Page<Student> page = service.findPageByRequest(q, pageable);
        return AjaxResult.ok().data(page);
    }

    @HasPermission("student:save")
    @PostMapping("save")
    public AjaxResult save(@RequestBody Student input, RequestBodyKeys updateFields) throws Exception {
        service.saveOrUpdateByRequest(input, updateFields);
        return AjaxResult.ok().msg("保存成功");
    }

    @HasPermission("student:delete")
    @RequestMapping("delete")
    public AjaxResult delete(String id) {
        service.deleteByRequest(id);
        return AjaxResult.ok().msg("删除成功");
    }
}
```

### 3.5 前端页面 (index.jsx)

```jsx
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm} from 'antd'
import React from 'react'
import {ButtonList, HttpUtils, Page, ProTable} from "../../../framework";

export default class extends React.Component {
    state = {
        formValues: {},
        formOpen: false
    }

    formRef = React.createRef()
    tableRef = React.createRef()

    columns = [
        {
            title: '姓名',
            dataIndex: 'name',
        },
        {
            title: '年龄',
            dataIndex: 'age',
        },
        {
            title: '班级',
            dataIndex: 'className',
        },
        {
            title: '操作',
            dataIndex: 'option',
            render: (_, record) => (
                <ButtonList>
                    <Button size='small' perm='student:save' onClick={() => this.handleEdit(record)}>编辑</Button>
                    <Popconfirm perm='student:delete' title='是否确定删除学生' onConfirm={() => this.handleDelete(record)}>
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
        HttpUtils.post('admin/student/save', values).then(rs => {
            this.setState({formOpen: false})
            this.tableRef.current.reload()
        })
    }

    handleDelete = record => {
        HttpUtils.get('admin/student/delete', {id: record.id}).then(rs => {
            this.tableRef.current.reload()
        })
    }

    render() {
        return <Page>
            <ProTable
                actionRef={this.tableRef}
                toolBarRender={(params, {selectedRows, selectedRowKeys}) => {
                    return <ButtonList>
                        <Button perm='student:save' type='primary' onClick={this.handleAdd}>
                            <PlusOutlined/> 新增
                        </Button>
                    </ButtonList>
                }}
                request={(params) => HttpUtils.get('admin/student/page', params)}
                columns={this.columns}
            />

            <Modal title='学生信息'
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
                    <Form.Item label='姓名' name='name' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>
                    <Form.Item label='年龄' name='age'>
                        <Input type="number" />
                    </Form.Item>
                    <Form.Item label='班级' name='className' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>
                </Form>
            </Modal>
        </Page>
    }
}
```