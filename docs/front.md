# 前端模块

## 组件列表

| 组件名称 | 描述 | 主要参数 |
| :------- | :--- | :------- |
| `DownloadFileButton` | 一个用于下载文件的按钮组件，点击后会显示加载提示，并在文件下载完成后自动关闭。 | `url`: `string` (文件下载的URL), `params`: `object` (请求参数), `children`: `ReactNode` (按钮的子元素) |
| `LinkButton` | 一个用于页面跳转的链接按钮组件，点击后会通过 `PageUtils.open` 方法打开指定路径的页面。 | `path`: `string` (目标页面的路径), `label`: `string` (目标页面的标题或标签), `children`: `ReactNode` (按钮的子元素), `size`: `string` (可选, 默认: `'small'`) |
| `Ellipsis` | 文本超出指定长度时显示省略号，点击后可查看完整文本的组件。 | `length`: `number` (可选, 默认: `15`), `children`: `string` (需要处理的文本内容), `pre`: `boolean` (可选, 默认: `false`) |
| `NamedIcon` | 根据传入的 `name` 动态渲染 Ant Design 图标的组件。 | `name`: `string` (Ant Design 图标的名称字符串) |
| `PageLoading` | 页面加载时显示的动画和提示信息组件。 | `message`: `string` (可选, 默认: `'页面加载中...'`) |
| **`Gap` 目录下的组件** | 布局间隔组件。 | 详见代码 |
| **`Page` 目录下的组件** | 页面布局相关组件。 | 详见代码 |
| **`ProModal` 目录下的组件** | 模态框组件。 | 详见代码 |
| **`ProTable` 目录下的组件** | 表格组件。 | 详见代码 |
| **`system` 目录下的组件** | 系统通用组件。 | 详见代码 |
| **`ValueType` 目录下的组件** | 值类型显示组件。 | 详见代码 |
| **`view` 目录下的组件** | 视图相关组件。 | 详见代码 |

## 字段组件列表

| 组件名称 | 描述 | 主要参数 |
| :------- | :--- | :------- |
| `FieldBoolean` | 布尔值输入组件，支持 `select` (默认), `radio`, `checkbox`, `switch` 四种类型。能将 `null` 转换为 `false`，并能解析 `1`, `'true'`, `'Y'` 为 `true`。 | `value`: `boolean | number | string` (当前布尔值), `onChange`: `function(value: boolean)` (值改变时的回调函数), `type`: `string` (可选, 默认: `'select'`) |
| `FieldDate` | 日期选择组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder` 等通用字段参数。 |
| `FieldDateRange` | 日期范围选择组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder` 等通用字段参数。 |
| `FieldDictSelect` | 字典选择组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder`, `dictCode` 等参数。 |
| `FieldEditor` | 富文本编辑器组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder` 等通用字段参数。 |
| `FieldPercent` | 百分比输入组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder` 等通用字段参数。 |
| `FieldRemoteSelect` | 远程单选组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder`, `url`, `params` 等参数。 |
| `FieldRemoteSelectMultiple` | 远程多选组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder`, `url`, `params` 等参数。 |
| `FieldRemoteSelectMultipleInline` | 远程多选行内组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder`, `url`, `params` 等参数。 |
| `FieldRemoteTree` | 远程树形选择组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder`, `url`, `params` 等参数。 |
| `FieldRemoteTreeCascader` | 远程树形联级选择组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder`, `url`, `params` 等参数。 |
| `FieldRemoteTreeSelect` | 远程树形单选组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder`, `url`, `params` 等参数。 |
| `FieldRemoteTreeSelectMultiple` | 远程树形多选组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder`, `url`, `params` 等参数。 |
| `FieldSysOrgTree` | 系统组织机构树组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder` 等参数。 |
| `FieldSysOrgTreeSelect` | 系统组织机构树选择组件。 | `value`, `onChange`, `readOnly`, `disabled`, `placeholder` 等参数。 |
| `FieldTable` | 表格字段组件。 | `value`, `onChange`, `readOnly`, `disabled` 等参数。 |
| `FieldTableSelect` | 表格选择组件。 | `value`, `onChange`, `readOnly`, `disabled`, `columns`, `rowKey` 等参数。 |
| `FieldUploadFile` | 文件上传组件。 | `value`, `onChange`, `readOnly`, `disabled`, `action`, `maxCount` 等参数。 |
| **`system` 目录下的字段组件** | 系统通用字段组件。 | 详见代码 |

## 工具类列表

| 工具类名称 | 描述 | 主要方法 |
| :------- | :--- | :------- |
| `ArrUtils` | 数组相关工具类。 | 详见代码 |
| `ColorsUtils` | 颜色相关工具类。 | 详见代码 |
| `DateUtils` | 日期时间工具类。 | 详见代码 |
| `DeviceUtils` | 设备信息工具类。 | 详见代码 |
| `DomUtils` | DOM 操作工具类。 | 详见代码 |
| `EventBusUtils` | 事件总线工具类。 | 详见代码 |
| `HttpUtils` | HTTP 请求工具类。 | `downloadFile` |
| `MessageUtils` | 消息提示工具类。 | 详见代码 |
| `ObjectUtils` | 对象操作工具类。 | 详见代码 |
| `PageUtils` | 页面操作工具类。 | `open` |
| `StorageUtils` | 本地存储工具类。 | 详见代码 |
| `StringUtils` | 字符串工具类。 | `ellipsis` |
| `TreeUtils` | 树形结构工具类。 | 详见代码 |
| `UrlUtils` | URL 处理工具类。 | 详见代码 |
| `UuidUtils` | UUID 生成工具类。 | 详见代码 |
| `ValidateUtils` | 验证工具类。 | 详见代码 |
| **`system` 目录下的工具类** | 系统通用工具类。 | 详见代码 |