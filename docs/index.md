# 首页

## 引用方式

### Maven

```xml
<dependency>
    <groupId>io.github.jiangood</groupId>
    <artifactId>springboot-admin-starter</artifactId>
    <version>
        <![CDATA[<img src="https://img.shields.io/badge/version-0.0.2-blue" alt="Version">]]>
    </version>
</dependency>
```

### NPM

```json
{
  "dependencies": {
    "@jiangood/springboot-admin-starter": "<img src="https://img.shields.io/badge/version-0.0.1--beta.30-blue" alt="Version">"
  }
}
```

## 开发环境

*   **后端**: Java 17, Spring Boot 3.x
*   **前端**: React, UmiJS, Ant Design, TypeScript

## 功能列表

| 功能模块 | 功能名称       |
| -------- | -------------- |
| 邮件配置 | 邮件发送账号   |
| 邮件配置 | 邮件发送密码   |
| 网站配置 | 版权信息       |
| 网站配置 | 登录背景图     |
| 网站配置 | 登录框下面的提示 |
| 网站配置 | 站点标题       |
| 网站配置 | 开启水印       |
| 系统配置 | 请求基础地址   |
| 系统配置 | jwt密码        |
| 菜单     | 我的任务       |
| 菜单     | 系统管理       |
| 菜单     | 机构管理       |
| 菜单     | 用户管理       |
| 菜单     | 角色管理       |
| 菜单     | 操作手册       |
| 菜单     | 系统配置       |
| 菜单     | 数据字典       |
| 菜单     | 存储文件       |
| 菜单     | 作业调度       |
| 菜单     | 操作日志       |
| 菜单     | 接口管理       |
| 菜单     | 流程引擎       |
| 菜单     | 报表管理       |

## 配置说明

| 配置项 Code     | 名称         | 描述               |
| --------------- | ------------ | ------------------ |
| email.from      | 邮件发送账号 |                    |
| email.pass      | 邮件发送密码 |                    |
| sys.copyright   | 版权信息     |                    |
| sys.loginBackground | 登录背景图   |                    |
| sys.loginBoxBottomTip | 登录框下面的提示 |                    |
| sys.title       | 站点标题     |                    |
| sys.waterMark   | 开启水印     | 在所有页面增加水印 |
| sys.baseUrl     | 请求基础地址 | 非必填，可用于拼接完整请求地址 |
| sys.jwtSecret   | jwt密码      |                    |


## 文档链接

*   [前端模块](front.md)
*   [后端模块](back.md)