# 前端框架文档

## 组件 (web/src/framework/components)

| 组件名称 | 描述 | 主要参数 |
|---|---|---|
| `DownloadFileButton` | 文件下载按钮组件，点击后会发起文件下载请求。 | `url`: 文件下载的 URL<br>`params`: 下载请求的参数<br>`children`: 按钮内容<br>`...rest`: Ant Design `Button` 组件的其他属性 |
| `Ellipsis` | 文本省略组件，当文本长度超过指定值时进行省略，点击后可查看完整内容。 | `length`: (可选) 文本省略的长度，默认为 15。<br>`children`: 要显示的文本内容。<br>`pre`: (可选) 是否在模态框中以 `<pre>` 标签显示完整内容，默认为 `false`。 |
| `LinkButton` | 链接按钮组件，点击后通过 `PageUtils.open` 打开指定路径。 | `path`: 点击按钮后要跳转的路径。<br>`label`: 页面标签（可能用于 `PageUtils.open` 的标题或标识）。<br>`children`: 按钮内容。<br>`size`: (可选) 按钮大小，默认为 `small`。<br>`...rest`: Ant Design `Button` 组件的其他属性 |
| `NamedIcon` | 动态加载 Ant Design 图标组件。通过传入图标名称，显示对应的 Ant Design 图标。 | `name`: 要显示的 Ant Design 图标的名称字符串 (例如 "HomeOutlined", "SettingFilled" 等)。<br>`...rest`: Ant Design 图标组件的其他属性。 |
| `PageLoading` | 页面加载动画组件，用于在页面加载时显示加载提示。 | `message`: (可选) 加载时显示的提示信息，默认为 '页面加载中...'。 |

## 工具类 (web/src/framework/utils)

| 工具类名称 | 描述 | 主要方法 |
|---|---|---|
| `ArrUtils` | 数组工具类，提供了一系列对数组进行操作的静态方法。 | `contains<T>(arr: T[], item: T): boolean`: 检查数组是否包含某个元素。<br>`add<T>(arr: T[], item: T): void`: 在数组末尾添加一个元素。<br>`remove<T>(arr: T[], item: T): void`: 移除数组中第一个匹配的元素。<br>`unique<T>(arr: T[]): T[]`: 对数组进行去重。 |
| `ColorsUtils` | 颜色工具类，提供颜色相关的转换和操作方法，包括 RGB、HSV、Hex 之间的转换以及颜色亮度、混合等功能。 | `rgbToHex(color: RGB): string`: 将 RGB 颜色对象转换为十六进制字符串。<br>`hexToRgb(hex: string): RGB`: 将十六进制颜色字符串转换为 RGB 颜色对象。<br>`lighten(color: string, percent: number): string`: 调亮或调暗颜色。<br>`brightness(color: string | RGB): number`: 计算颜色的感知亮度 (Brightness)。 |
| `DateUtils` | 日期工具类，提供了一系列日期时间格式化、获取以及友好时间显示等静态方法。 | `formatDate(d: Date): string`: 格式化日期为 'YYYY-MM-DD' 格式。<br>`formatTime(d: Date): string`: 格式化时间为 'HH:mm:ss' 格式。<br>`friendlyTime(pastDate: Date | string | number): string | undefined`: 显示友好时间，如 "2小时前", "1周前"。 |
| `DeviceUtils` | 设备工具类，提供判断设备类型和获取 WebSocket 基础 URL 的静态方法。 | `isMobileDevice(): boolean`: 判断当前设备是否为移动设备。<br>`getWebsocketBaseUrl(): string`: 根据当前页面的协议和主机名构造 WebSocket 的基础 URL。 |
| `DomUtils` | DOM 操作工具类，封装了获取元素位置和尺寸的工具方法。 | `offset(el: Element | Window): { top: number; left: number }`: 获取元素相对于视口的偏移量。<br>`height(el: Element | Window): number`: 获取元素的外部高度。<br>`width(el: Element | Window): number`: 获取元素的外部宽度。 |
| `EventBusUtils` | 静态事件总线工具类，提供事件的注册、触发和移除功能。 | `on<T extends any[] = []>(name: string, callback: (...args: T) => void, ctx?: any): void`: 注册事件监听器。<br>`emit<T extends any[] = []>(name: string, ...args: T): void`: 触发事件。<br>`off<T extends any[] = []>(name: string, callback?: ((...args: T) => void)): void`: 移除事件监听器。 |
| `MessageUtils` | 消息工具类，封装了 Ant Design 的 Modal, message, 和 notification 静态方法。 | `alert(content: React.ReactNode): void`: 弹出 Alert 提示框。<br>`confirm(content: React.ReactNode): Promise<boolean>`: 弹出 Confirm 确认框。<br>`success(content: React.ReactNode): void`: 显示成功消息。<br>`notifyError(message: React.ReactNode, description: React.ReactNode): void`: 弹出失败通知。 |
| `ObjectUtils` | 对象工具类，提供了安全获取嵌套属性、根据目标对象结构复制属性等静态方法。 | `get<TObj extends object, TDefault = unknown>(obj: TObj | null | undefined, path: string | (keyof TObj)[], defaultValue: TDefault | undefined = undefined): unknown | TDefault`: 安全地获取深度嵌套的对象属性的值。<br>`copyProperty<TSource extends object, TTarget extends object>(source: TSource | null | undefined, target: TTarget | null | undefined): void`: 复制对象属性。 |
| `StorageUtils` | 存储工具类，提供了基于 `localStorage` 的数据存取功能，并自动处理时间戳和默认值。 | `get<T>(key: string, defaultValue: T | null = null): T | null`: 从 `localStorage` 获取数据。<br>`set<T>(key: string, value: T | null | undefined): void`: 将数据存储到 `localStorage`。 |
| `StringUtils` | 字符串工具类，提供了一系列对字符串进行操作的静态方法。 | `removePrefix(str: string, ch: string): string`: 移除字符串前缀。<br>`ellipsis(str: string, len: number): string`: 字符串省略处理。<br>`toCamelCase(str: string): string`: 将下划线或连字符分隔的字符串转为驼峰命名。 |
| `TreeUtils` | 树结构操作工具类，提供树结构与扁平数组的相互转换、遍历、查找等功能。 | `treeToList<T extends TreeNode>(tree: T[]): (T & { level: number })`: 将树结构转换为列表结构。<br>`buildTree<T extends TreeNode>(list: T[]): T[]`: 将扁平数组转换为树结构。<br>`walk<T extends TreeNode>(tree: T[], callback: (node: T) => void): void`: 深度优先遍历树节点。 |
| `UrlUtils` | URL 工具类，提供 URL 参数的获取、设置、替换以及路径连接等静态方法。 | `getParams(url: string | null = null): UrlUtils.ParamsObject`: 获取 URL 的参数对象。<br>`setParam(url: string, key: string, value: string | number | boolean | null | undefined): string`: 设置或删除 URL 中的一个参数。<br>`join(path1: string, path2: string): string`: 连接两个路径片段。 |
| `UuidUtils` | UUID 工具类，提供生成符合 v4 标准的 UUID 字符串的静态方法。 | `uuidV4(): string`: 生成一个符合 v4 标准的 UUID 字符串。 |
| `ValidateUtils` | 验证工具类，提供了对字符串进行各种验证的静态方法，例如电子邮件地址验证。 | `isEmail(emailStr: string): boolean`: 检查给定的字符串是否为有效的电子邮件地址。 |