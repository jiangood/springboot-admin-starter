# SpringBoot Admin Starter

## 介绍

这是一个小型管理系统框架，供业务项目使用。基于Spring Boot开发，提供了常用的后台管理功能组件。

后端最新版本为：![Maven Version](https://img.shields.io/maven-central/v/io.github.jiangood/springboot-admin-starter)
前端最新版本为：![NPM Version](https://img.shields.io/npm/v/@jiangood/springboot-admin-starter)

### 开发环境

- Java 17+
- Node.js
- Maven
- MySQL

### 后端依赖

| 依赖 | 版本 | 说明 |
|------|------|------|
| Spring Boot Web | 3.5.8 | 用于构建Web应用程序 |
| Spring Boot Quartz | 3.5.8 | 用于任务调度 |
| Spring Boot Validation | 3.5.8 | 用于参数验证 |
| Spring Boot AOP | 3.5.8 | 用于面向切面编程 |
| Spring Boot Data JPA | 3.5.8 | 用于数据库访问 |
| Spring Boot Cache | 3.5.8 | 用于缓存功能 |
| Spring Boot Security | 3.5.8 | 用于安全认证和授权 |
| MapStruct | 1.6.3 | 用于对象映射 |
| Filters | 2.0.235-1 | 用于验证码图像处理 |
| MinIO | 8.6.0 | 用于对象存储 |
| Java Mail | 1.4.7 | 用于邮件发送 |
| Apache POI OOXML | 5.5.0 | 用于Office文档处理 |
| iText PDF | 5.5.13.4 | 用于PDF文档处理 |
| UUID Creator | 6.1.1 | 用于生成UUID |
| Commons DBUtils | 1.8.1 | 用于数据库操作 |
| Hutool | 5.8.41 | 通用工具类 |
| Apache Commons Lang3 | - | 字符串处理等工具 |
| Google Guava | 33.5.0-jre | 通用工具类 |
| Commons IO | 2.21.0 | IO操作工具 |
| Jackson YAML | - | YAML格式解析 |
| Commons BeanUtils | 1.11.0 | JavaBean操作 |
| Pinyin4j | 2.5.1 | 中文转拼音 |
| Jsoup | 1.21.2 | HTML解析 |
| Lombok | - | 简化代码 |
| MySQL Connector | - | MySQL数据库连接 |
| Ureport Console | 1.0.4 | 报表功能 |
| Flowable | 7.2.0 | 流程引擎 |

### 前端依赖

| 依赖 | 版本 | 说明 |
|------|------|------|
| Ant Design | 6.x | UI组件库 |
| React | 19.0.0 | 前端框架 |
| React DOM | 19.0.0 | React DOM操作 |
| Umi | 4.0.0 | 前端框架 |
| Ant Design Icons | 5.4.0 | 图标库 |
| Axios | 1.13.2 | HTTP客户端 |
| Dayjs | 1.11.13 | 日期处理库 |
| Lodash | 4.17.21 | 实用工具库 |
| BPMN JS | 18.7.0 | 流程图渲染引擎 |
| TinyMCE React | 6.0.0 | 富文本编辑器组件 |

### 菜单配置

系统预设了以下菜单配置，业务项目需在 `application-data-biz.yml` 中进行配置。

- 我的任务
  - 路径: /flowable/task
  - 权限: myFlowableTask:manage
- 系统管理
  - 机构管理
    - 路径: /system/org
    - 权限: sysOrg:save, sysOrg:view, sysOrg:delete
  - 用户管理
    - 路径: /system/user
    - 权限: sysUser:view, sysUser:save, sysUser:delete, sysUser:resetPwd, sysUser:grantPerm
  - 角色管理
    - 路径: /system/role
    - 权限: sysRole:save
  - 操作手册
    - 路径: /system/sysManual
    - 权限: sysManual:view, sysManual:delete, sysManual:save
  - 系统配置
    - 路径: /system/config
    - 权限: sysConfig:view, sysConfig:save
  - 数据字典
    - 路径: /system/dict
    - 权限: sysDict:view, sysDict:save, sysDict:delete
  - 存储文件
    - 路径: /system/file
    - 权限: sysFile:view, sysFile:delete
  - 作业调度
    - 路径: /system/job
    - 权限: job:view, job:save, job:triggerJob, job:jobLogClean
  - 操作日志
    - 路径: /system/log
    - 权限: sysLog:view
  - 接口管理
    - 路径: /system/api
    - 权限: api
  - 流程引擎
    - 路径: /flowable
    - 权限: flowableModel:design, flowableModel:deploy, flowableTask:setAssignee, flowableInstance:close
  - 报表管理
    - 路径: /ureport
    - 权限: ureport:view, ureport:design

## 前端

### 组件和工具类

| 名称 | 参数（属性） | 说明 |
|------|--------------|------|
| ProModal | - | 高级弹窗组件 |
| Ellipsis | - | 省略号组件，用于文本截断 |
| Page | - | 页面布局组件 |
| ProTable | - | 高级表格组件 |
| DownloadFileButton | - | 文件下载按钮 |
| LinkButton | - | 链接样式的按钮 |
| NamedIcon | - | 命名图标组件 |
| PageLoading | - | 页面加载中组件 |
| Gap | - | 间距组件 |
| FieldBoolean | - | 布尔类型字段组件 |
| FieldDate | - | 日期类型字段组件 |
| FieldDateRange | - | 日期范围字段组件 |
| FieldDictSelect | - | 字典选择字段组件 |
| FieldEditor | - | 编辑器字段组件 |
| FieldPercent | - | 百分比字段组件 |
| FieldRemoteSelect | - | 远程选择字段组件 |
| FieldRemoteTree | - | 远程树字段组件 |
| FieldUploadFile | - | 文件上传字段组件 |
| ViewBoolean | - | 布尔类型视图组件 |
| ViewPassword | - | 密码类型视图组件 |

### 工具类

| 名称 | 参数（属性） | 说明 |
|------|--------------|------|
| DateUtils | - | 日期处理工具 |
| ArrUtils | - | 数组处理工具 |
| UrlUtils | - | URL处理工具 |
| StringUtils | - | 字符串处理工具 |
| EventBusUtils | - | 事件总线工具 |
| ColorsUtils | - | 颜色处理工具 |
| DomUtils | - | DOM操作工具 |
| UuidUtils | - | UUID生成工具 |
| TreeUtils | - | 树形结构处理工具 |
| StorageUtils | - | 本地存储工具 |
| DeviceUtils | - | 设备信息工具 |
| ObjectUtils | - | 对象处理工具 |
| ValidateUtils | - | 验证工具 |
| MessageUtils | - | 消息提示工具 |

## 后端

### Spec查询构建器
- **功能**: 简洁、动态、支持关联字段查询的 JPA Specification 构建器
- **说明**: 通过链式调用收集 Specification，最终使用 AND 逻辑连接所有条件，支持大部分数据库操作

### TreeUtils树结构工具
- **功能**: 将列表转换为树结构
- **说明**: 提供树构建、遍历、节点查询等功能

### RemarkUtils注解工具
- **功能**: 获取字段、类、枚举上的注解描述信息
- **说明**: 用于获取@Remark注解的内容

### ExcelUtils Excel处理工具
- **功能**: Excel导入导出功能
- **说明**: 通过@Remark注解自动匹配列头和字段

### JdbcUtils原生SQL工具
- **功能**: 基于Spring JdbcTemplate封装的原生SQL工具类
- **说明**: 专注于执行复杂的原生SQL查询和更新操作

### ID生成器
- **GeneratePrefixedSequence**: 基于序列的带前缀ID生成器
- **GenerateUuidV7**: 基于uuid v7的ID生成器

### 数据类型转换器
- **BaseCodeEnumConverter**: 枚举转换器
- **ToXXXListConverter**: 列表类型转换器
- **ToMapConverter**: Map类型转换器

### 验证注解
- **ValidateCarDrivingLicence**: 驾驶证号验证
- **ValidateChineseName**: 中文姓名验证
- **ValidateIdNum**: 身份证号验证
- **ValidateMobile**: 手机号验证
- **ValidatePassword**: 密码验证
- **ValidateEmail**: 邮箱验证
- 等等

### 登录工具
- **LoginUtils**: 获取当前登录用户信息的工具类

### 任务调度
- **BaseJob**: 任务基类，提供任务执行框架
- **JobDescription**: 任务描述注解，定义任务参数等信息

### 流程引擎
- **application-process.yml**: 流程配置文件，定义流程定义、变量、表单等
- **ProcessListener**: 流程事件监听器接口

## 模板代码

以下是一个学生实体的业务代码模板示例：

### 实体类 (Student.java)

```java
package io.admin.modules.biz.entity;

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
    private String studentNo;

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
     * 专业，最大长度100字符
     */
    @Remark("专业")
    @Column(length = 100)
    @Size(max = 100, message = "专业长度不能超过100个字符")
    private String major;

}
```

### 数据访问层 (StudentDao.java)

```java
package io.admin.modules.biz.dao;

import io.admin.framework.data.specification.Spec;
import io.admin.modules.biz.entity.Student;
import io.admin.framework.data.repository.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {

    // 可添加自定义查询方法
    public Student findByStudentNo(String studentNo) {
        Spec<Student> spec = Spec.<Student>of().eq(Student.Fields.studentNo, studentNo);
        return this.findOne(spec);
    }

}
```

### 服务层 (StudentService.java)

```java
package io.admin.modules.biz.service;

import io.admin.modules.biz.dao.StudentDao;
import io.admin.modules.biz.entity.Student;
import io.admin.framework.data.service.BaseService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<Student> {

    @Resource
    StudentDao dao;

    // 可添加自定义业务逻辑

}
```

### 控制层 (StudentController.java)

```java
package io.admin.modules.biz.controller;

import io.admin.common.dto.AjaxResult;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.config.security.HasPermission;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.biz.entity.Student;
import io.admin.modules.biz.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
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
        Spec<Student> q = Spec.of();
        q.orLike(searchText, Student.Fields.name, Student.Fields.studentNo);

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

### 前端页面 (index.jsx)

```jsx
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm} from 'antd'
import React from 'react'
import {ButtonList, HttpUtils, Page, ProTable} from '@jiangood/springboot-admin-starter';

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
            dataIndex: 'studentNo',
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
            title: '专业',
            dataIndex: 'major',
        },
        {
            title: '操作',
            dataIndex: 'option',
            render: (_, record) => (
                <ButtonList>
                    <Button size='small' perm='student:save' onClick={() => this.handleEdit(record)}>编辑</Button>
                    <Popconfirm perm='student:delete' title='是否确定删除学生信息' onConfirm={() => this.handleDelete(record)}>
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

                    <Form.Item label='学号' name='studentNo' rules={[{required: true}]}>
                        <Input placeholder="请输入学号" />
                    </Form.Item>

                    <Form.Item label='姓名' name='name' rules={[{required: true}]}>
                        <Input placeholder="请输入姓名" />
                    </Form.Item>

                    <Form.Item label='年龄' name='age'>
                        <Input placeholder="请输入年龄" />
                    </Form.Item>

                    <Form.Item label='专业' name='major'>
                        <Input placeholder="请输入专业" />
                    </Form.Item>

                </Form>
            </Modal>
        </Page>
    }
}
```

### 菜单配置

在 `application-data-biz.yml` 文件中添加以下菜单配置：

```yaml
data:
  # 业务菜单配置
  menus:
    - id: biz
      name: 业务管理
      seq: 100
      children:
        - id: student
          name: 学生管理
          path: /biz/student
          icon: UserOutlined
          perms:
            - name: 查看
              perm: student:view
            - name: 保存
              perm: student:save
            - name: 删除
              perm: student:delete
```