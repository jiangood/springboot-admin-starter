# 功能模块

## 业务代码模板：学生 (Student)

本章节将以“学生”为例，展示如何根据项目现有结构生成新的业务模块代码。

### 1. 实体 (Entity)

参考 `src/main/java/io/admin/modules/system/entity/SysUser.java`

`src/main/java/io/admin/modules/student/entity/Student.java`

```java
package io.admin.modules.student.entity;

import io.admin.common.utils.annotation.Remark;
import io.admin.framework.data.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@Entity
@FieldNameConstants
@Remark("学生")
public class Student extends BaseEntity {

    @Remark("学号")
    @NotNull(message = "学号不能为空")
    @Column(unique = true, length = 50)
    private String studentNo;

    @Remark("姓名")
    @Column(length = 50)
    private String name;

    @Remark("年龄")
    private Integer age;

    @Remark("性别")
    private String gender; // 可以使用枚举或字典表

    @Remark("班级")
    private String className;
}
```

### 2. 数据访问层 (DAO)

参考 `src/main/java/io/admin/modules/system/dao/SysUserDao.java`

`src/main/java/io/admin/modules/student/dao/StudentDao.java`

```java
package io.admin.modules.student.dao;

import io.admin.framework.data.repository.BaseDao;
import io.admin.modules.student.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseDao<Student> {

    public Student findByStudentNo(String studentNo) {
        return this.findByField(Student.Fields.studentNo, studentNo);
    }
}
```

### 3. 业务逻辑层 (Service)

参考 `src/main/java/io/admin/modules/system/service/SysUserService.java`

`src/main/java/io/admin/modules/student/service/StudentService.java`

```java
package io.admin.modules.student.service;

import io.admin.framework.data.service.BaseService;
import io.admin.modules.student.dao.StudentDao;
import io.admin.modules.student.entity.Student;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentService extends BaseService<Student> {

    @Resource
    private StudentDao studentDao;

    public Student findByStudentNo(String studentNo) {
        return studentDao.findByStudentNo(studentNo);
    }
}
```

### 4. 控制层 (Controller)

参考 `src/main/java/io/admin/modules/system/controller/SysUserController.java`

`src/main/java/io/admin/modules/student/controller/StudentController.java`

```java
package io.admin.modules.student.controller;

import io.admin.common.dto.AjaxResult;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.config.security.HasPermission;
import io.admin.framework.data.specification.Spec;
import io.admin.modules.student.entity.Student;
import io.admin.modules.student.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @HasPermission("student:view")
    @RequestMapping("page")
    public AjaxResult page(String searchText, @PageableDefault(sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        Spec<Student> query = Spec.of();
        if (searchText != null) {
            query.like(Student.Fields.name, searchText);
        }
        Page<Student> page = studentService.findAll(query, pageable);
        return AjaxResult.ok().data(page);
    }

    @HasPermission("student:save")
    @PostMapping("save")
    public AjaxResult save(@RequestBody Student input, RequestBodyKeys updateFields) throws Exception {
        studentService.saveOrUpdateByRequest(input, updateFields);
        return AjaxResult.ok();
    }

    @HasPermission("student:delete")
    @GetMapping("delete")
    public AjaxResult delete(String id) {
        studentService.delete(id);
        return AjaxResult.ok();
    }
}
```

### 5. 菜单配置

参考 `src/main/resources/application-data-framework.yml`

在 `application-data-biz.yml` 中增加如下配置：

```yaml
data:
  menus:
    - id: student
      name: 学生管理
      path: /student
      icon: TeamOutlined # 假设有一个TeamOutlined图标
      seq: 200 # 顺序
      perms:
        - name: 查看
          perm: student:view
        - name: 保存
          perm: student:save
        - name: 删除
          perm: student:delete
```

### 6. 页面模板

参考 `web/src/pages/system/sysManual/index.jsx`

`web/src/pages/student/index.jsx`

```jsx
import {PlusOutlined} from '@ant-design/icons'
import {Button, Form, Input, Modal, Popconfirm} from 'antd'
import React from 'react'
import {ButtonList, Page, ProTable} from "../../framework";
import {HttpUtils} from "../../framework/utils";


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
            title: '性别',
            dataIndex: 'gender',
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
                    <Popconfirm perm='student:delete' title='是否确定删除该学生' onConfirm={() => this.handleDelete(record)}>
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
                   destroyOnClose={true}
                   maskClosable={false}
            >
                <Form ref={this.formRef} labelCol={{flex: '100px'}}
                      initialValues={this.state.formValues}
                      onFinish={this.onFinish}
                >
                    <Form.Item name='id' noStyle></Form.Item>

                    <Form.Item label='学号' name='studentNo' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='姓名' name='name' rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='年龄' name='age'>
                        <Input type='number'/>
                    </Form.Item>

                    <Form.Item label='性别' name='gender'>
                        <Input/>
                    </Form.Item>

                    <Form.Item label='班级' name='className'>
                        <Input/>
                    </Form.Item>
                </Form>
            </Modal>
        </Page>
    }
}
```