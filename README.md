# SpringBoot Admin Starter 后端管理系统框架

## 介绍

SpringBoot Admin Starter是一个基于Spring Boot的后端管理系统的框架，为业务项目提供便捷的开发基础。该框架集成了用户管理、权限控制、作业调度、流程引擎、报表管理等功能模块，帮助开发者快速构建企业级管理后台。

后端最新版本为： ![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter)
前端最新版本为： ![NPM Version](https://img.shields.io/npm/v/@jiangood/springboot-admin-starter)

### 开发环境

- Java 17+
- Maven 3.6+
- Node.js 16+
- React 19+
- Ant Design 6.x

### 后端依赖

- Spring Boot 3.5.8 - 核心框架
- Spring Data JPA - 数据访问层
- Spring Security - 安全认证
- Spring Web - Web层支持
- Spring Quartz - 作业调度
- Spring Validation - 参数校验
- Lombok - 代码简化
- MapStruct - 对象映射
- MySQL - 数据库驱动
- MinIO - 文件存储
- Flowable - 工作流程引擎
- Hutool - 工具库
- Apache POI - Excel处理
- iText - PDF处理
- Guava - Google开源库

### 前端依赖

- React 19 - 前端框架
- Ant Design 6.x - UI组件库
- Umi 4 - 前端构建工具
- Axios - HTTP客户端
- DayJS - 时间处理库
- TypeScript 5 - 类型系统
- Bpmn-js - 流程图绘制
- TinyMCE - 富文本编辑器

## 菜单列表

框架预置了以下菜单项：

- **我的任务** - 流程相关任务管理
- **系统管理**
  - 机构管理 - 管理组织架构
  - 用户管理 - 用户账户管理
  - 角色管理 - 角色权限管理
  - 操作手册 - 系统使用说明
  - 系统配置 - 系统参数设置
  - 数据字典 - 数据字典管理
  - 存储文件 - 文件上传管理
  - 作业调度 - 定时任务管理
  - 操作日志 - 操作记录查看
  - 接口管理 - API接口管理
  - 流程引擎 - 工作流程设计
  - 报表管理 - 数据报表管理

业务项目中可以通过在 `application-data-biz.yml` 文件中配置额外的菜单项。

## 前端框架功能

前端框架位于 `web/src/framework` 目录，提供了丰富的组件和工具：

### 组件
- **ProTable** - 高级表格组件，支持分页、搜索、筛选等功能
- **ProModal** - 高级弹窗组件
- **Page** - 页面布局组件
- **ButtonList** - 按钮列表组件
- **PageLoading** - 页面加载组件
- **DownloadFileButton** - 文件下载按钮
- **LinkButton** - 链接按钮
- **Ellipsis** - 文本省略组件
- **NamedIcon** - 命名图标组件
- **Gap** - 间距组件
- **ValueType** - 值类型组件

### 视图组件
- **view** - 提供多种数据展示组件

### 字段组件
- **field-components** - 提供多种表单字段组件

### 工具函数
- **DateUtils** - 日期处理工具
- **ArrUtils** - 数组操作工具
- **UrlUtils** - URL处理工具
- **StringUtils** - 字符串处理工具
- **EventBusUtils** - 事件总线工具
- **ColorsUtils** - 颜色处理工具
- **DomUtils** - DOM操作工具
- **UuidUtils** - UUID生成工具
- **TreeUtils** - 树结构处理工具
- **StorageUtils** - 存储工具
- **DeviceUtils** - 设备检测工具
- **ObjectUtils** - 对象操作工具
- **ValidateUtils** - 验证工具
- **MessageUtils** - 消息提示工具

## 后端功能详解

### Spec查询构建器
位于 `io.admin.framework.data.specification.Spec`，提供简洁、动态、支持关联字段查询的JPA Specification构建器，支持多种查询操作符，如eq、ne、like、in、between、isNotNull等，以及逻辑组合条件，如or、not、and等。

### 树结构处理
位于 `io.admin.common.utils.tree.TreeUtils`，提供树形结构的构建、遍历、转换等功能，支持将列表转换为树结构，将树结构转换为列表，获取叶子节点等操作。

### 注解工具
位于 `io.admin.common.utils.annotation`，提供了对注解的处理工具，如获取字段注解的描述信息。

### Excel处理
位于 `io.admin.common.utils.excel.ExcelUtils`，提供了Excel文件的导入和导出功能，支持通过注解标记实体字段与Excel列的映射关系。

### JDBC工具
位于 `io.admin.framework.data.JdbcUtils`，提供了基于Spring的JdbcTemplate封装的SQL执行工具，支持复杂的原生SQL查询和更新，提供了查询、更新、分页、动态DML等方法。

### ID生成器
- `GenerateUuidV7` - 使用UUID v7生成ID
- `GeneratePrefixedSequence` - 生成带前缀的序列ID

### 数据转换器
位于 `io.admin.framework.data.converter`，提供数据类型的转换功能，如List、Map、Set等集合类型的转换。

### 参数校验
位于 `io.admin.framework.validator`，提供了多种校验注解，如验证手机号、身份证号、邮箱、车牌号等。

### 登录工具
位于 `io.admin.modules.common.LoginUtils`，提供获取当前登录用户信息、权限、角色等的功能。

### 任务调度
- `BaseJob` - 任务调度的基础类
- `JobDescription` - 任务描述注解
- `HelloWorldJob` - 示例任务

### 流程引擎
- `application-process.yml` - 流程定义配置文件
- 流程监听器相关功能

## 业务代码模板（以学生管理为例）

### 实体类
```java
package io.admin.modules.business.entity;

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

/**
 * 学生实体类
 */
@Remark("学生")
@Entity
@Getter
@Setter
@Table(name = "biz_student")
@FieldNameConstants
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学号，最大长度20字符，不能为空
     */
    @NotNull
    @Remark("学号")
    @Column(length = 20, nullable = false, unique = true)
    @Size(max = 20, message = "学号长度不能超过20个字符")
    private String studentId;

    /**
     * 姓名，最大长度50字符，不能为空
     */
    @NotNull
    @Remark("姓名")
    @Column(length = 50, nullable = false)
    @Size(max = 50, message = "姓名长度不能超过50个字符")
    private String name;

    /**
     * 年龄，必须为正整数
     */
    @Remark("年龄")
    @Column
    private Integer age;

    /**
     * 性别，最大长度10字符
     */
    @Remark("性别")
    @Column(length = 10)
    @Size(max = 10, message = "性别长度不能超过10个字符")
    private String gender;

    /**
     * 电话号码，最大长度20字符
     */
    @Remark("电话")
    @Column(length = 20)
    @Size(max = 20, message = "电话号码长度不能超过20个字符")
    private String phone;

    /**
     * 邮箱，最大长度100字符
     */
    @Remark("邮箱")
    @Column(length = 100)
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    private String email;

    /**
     * 专业，最大长度100字符
     */
    @Remark("专业")
    @Column(length = 100)
    @Size(max = 100, message = "专业长度不能超过100个字符")
    private String major;

}
```

### DAO层
```java
package io.admin.modules.business.dao;

import io.admin.framework.data.specification.Spec;
import io.admin.modules.business.entity.Student;
import io.admin.framework.data.repository.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {

    public Spec<Student> getSearchCondition(String searchText) {
        Spec<Student> q = Spec.of();
        q.orLike(searchText, 
                 Student.Fields.name, 
                 Student.Fields.studentId, 
                 Student.Fields.major, 
                 Student.Fields.email);
        return q;
    }
}
```

### Service层
```java
package io.admin.modules.business.service;

import io.admin.modules.business.dao.StudentDao;
import io.admin.modules.business.entity.Student;
import io.admin.framework.data.service.BaseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends BaseService<Student> {

    @Resource
    StudentDao dao;

    @Override
    public Student saveOrUpdateByRequest(Student input, List<String> updateKeys) throws Exception {
        // 在这里可以添加业务逻辑，如数据校验等
        return super.saveOrUpdateByRequest(input, updateKeys);
    }
}
```

### Controller层
```java
package io.admin.modules.business.controller;

import io.admin.common.dto.AjaxResult;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.config.security.HasPermission;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.business.entity.Student;
import io.admin.modules.business.service.StudentService;
import jakarta.annotation.Resource;
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
    public AjaxResult page(String searchText,
                           @PageableDefault(direction = Sort.Direction.DESC, sort = "updateTime") Pageable pageable) throws Exception {
        Spec<Student> q = service.getSearchCondition(searchText);
        var page = service.findPageByRequest(q, pageable);
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

### 前端页面
```jsx
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm, InputNumber} from 'antd'
import React from 'react'
import {ButtonList, HttpUtils, Page, ProTable} from "@jiangood/springboot-admin-starter";

export default class extends React.Component {

    state = {
        formValues: {},
        formOpen: false
    }

    formRef = React.createRef()
    tableRef = React.createRef()

    columns = [
        {
            title: '学号',
            dataIndex: 'studentId',
        },
        {
            title: '姓名',
            dataIndex: 'name',
        },
        {
            title: '年龄',
            dataIndex: 'age',
        },
        {
            title: '性别',
            dataIndex: 'gender',
        },
        {
            title: '电话',
            dataIndex: 'phone',
        },
        {
            title: '邮箱',
            dataIndex: 'email',
        },
        {
            title: '专业',
            dataIndex: 'major',
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

            <Modal 
                title='学生信息'
                open={this.state.formOpen}
                onOk={() => this.formRef.current.submit()}
                onCancel={() => this.setState({formOpen: false})}
                destroyOnHidden
                maskClosable={false}
            >
                <Form 
                    ref={this.formRef} 
                    labelCol={{flex: '100px'}}
                    initialValues={this.state.formValues}
                    onFinish={this.onFinish}
                >
                    <Form.Item name='id' noStyle></Form.Item>

                    <Form.Item label='学号' name='studentId' rules={[{required: true, max: 20}]}>
                        <Input placeholder="请输入学号" />
                    </Form.Item>

                    <Form.Item label='姓名' name='name' rules={[{required: true, max: 50}]}>
                        <Input placeholder="请输入姓名" />
                    </Form.Item>

                    <Form.Item label='年龄' name='age' rules={[{type: 'number', min: 0, max: 150}]}>
                        <InputNumber min={0} max={150} placeholder="请输入年龄" style={{width: '100%'}} />
                    </Form.Item>

                    <Form.Item label='性别' name='gender' rules={[{max: 10}]}>
                        <Input placeholder="请输入性别" />
                    </Form.Item>

                    <Form.Item label='电话' name='phone' rules={[{max: 20}]}>
                        <Input placeholder="请输入电话号码" />
                    </Form.Item>

                    <Form.Item label='邮箱' name='email' rules={[{type: 'email', max: 100}]}>
                        <Input placeholder="请输入邮箱地址" />
                    </Form.Item>

                    <Form.Item label='专业' name='major' rules={[{max: 100}]}>
                        <Input placeholder="请输入专业" />
                    </Form.Item>
                </Form>
            </Modal>
        </Page>
    }
}
```

### 菜单配置
在 `application-data-biz.yml` 中添加：
```yml
- id: student
  name: 学生管理
  path: /business/student
  icon: UserOutlined
  perms:
    - name: 查看
      perm: student:view
    - name: 保存
      perm: student:save
    - name: 删除
      perm: student:delete
```

## 目录

[TOC]