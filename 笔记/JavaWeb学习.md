# JavaWeb学习

学习链接：[黑马程序员JavaWeb开发教程，实现javaweb企业开发全流程（涵盖Spring+MyBatis+SpringMVC+SpringBoot等）_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1m84y1w7Tb/?spm_id_from=333.1007.top_right_bar_window_custom_collection.content.click&vd_source=23bb3d2e7d9435bbb9a0e339a3f50ca3)

# 前端

### web标准

HTML：负责网页的结构

CSS：负责网页的表现

JS：负责网页的行为

## 一、HTML、CSS

### HTML快速入门

1. HTML结构标签

   ```html
   <html>
   	<head>
       
   	</head>
   	<body>
       
   	</body>
   </html>
   ```

2. 特点
   HTML标签不区分大小写
   HTML标签属性值单双引号都可以
   HTML语法松散（如：可以解析不完整标签）

### CSS的引入方式

1. 行内样式：写在标签的 style 属性中（不推荐）
   ![image-20240412234446071](image\image-20240412234446071.png)
2. 内嵌样式：写在 style 标签中（可以写在页面的任何位置，但通常约定写在 head 标签中）
   ![image-20240412234500700](image\image-20240412234500700.png)
3. 外联样式：写在一个独立的 .css 文件中（通过 link 标签在网页中引入）
   ![image-20240412234511920](image\image-20240412234511920.png)

### CSS盒子模型

组成：内容（content）、内边距（padding）、边框（border）、外边距（margin）

CSS属性：

​			width：设置宽度
​			height：设置高度
​			border：设置边框的属性，如：1px solid #000;
​			padding：内边距
​			margin：外边距
注意：如果只需要设置其中一个方位的边框、内边距、外边距，可以在属性名后加上位置，如：padding-top、padding-left、padding-right...

### 表格标签

```html
<table>:定义表格
<tr>:定义表格中的行，一个<tr>表示一行
<th>:表示表头单元格，具有加粗居中效果
<td>:表示普通单元格
```

[详细使用说明](https://www.w3school.com.cn/tags/tag_table.asp)

### 表单标签

表单标签：`<form>`
表单属性：
		action：表单数据提交的 url 地址
		method:表单提交的方式
				get:表单数据拼接在 url 后面， ?&username=java ,大小有限制
				post：表单数据在请求体中携带，大小没有限制
注意：表单项必须有name属性才可以提交

### 表单项标签

`<input>`的type属性：text、password、radio、checkbox、file、date、datetime-local、time、number、hidden、button、submit

`<select>`定义下拉列表

`<textarea>`定义文本域

## 二、JavaScript

### JS的引入方式

1. 内部脚本
   将JS代码定义在HTML页面中
   JavaScript代码必须在`<script><script>`标签之间
   在HTML文档中，可以在任地方，放置任意数量的`<script>`
   一般会把脚本至于`<body>`元素的底部，可以改善显示速度
   ![image-20240413173607729](image\image-20240413173607729.png)

2. 外部脚本
   将JS代码定义在外部JS文件中，然后引入到HTML页面中
   外部JS文件中，只包含JS代码，不包含`<script>`标签

   `<script>`标签不能自闭合
   ![image-20240413173719678](image\image-20240413173719678.png)

### 变量

JavaScript中的var关键字（variable的缩写）来声明变量
JavaScript是一门弱语言类型语言，变量可以存放不同类型的值
变量名需遵循如下规则：
	组成字符可以是任何字母、数字、下划线（_）、或美元符号$
	数字不能开头
	建议使用驼峰命名
![image-20240413175225361](image\image-20240413175225361.png)

var关键字定义的变量可以重复声明

​		ECMAScript 6 新增了let关键字来定义变量。它的用法类似于var，但是所声明的变量，只在let关键字所在的代码块内生效，且不允许重复声明
​		ECMAScript 6 新增了const关键字，用来声明一个只读的常量。一旦声明，常量的值就不能改变

### 基本数据类型

![image-20240413181342518](image\image-20240413181342518.png)

### 运算符

![image-20240413181210996](image\image-20240413181210996.png)

### 类型转换

![image-20240413181249796](image\image-20240413181249796.png)

### 函数

介绍：函数（方法）是被设计为执行特定任务的代码块
定义：JavaScript函数通过function关键字进行定义，语法为：
		

```javascript
function functionName(参数1,参数2){
//要执行的代码
}
```

注意：
		形式参数不需要类星星。因为JavaScript是弱类型语言
		返回值也不需要定义类型，可以在函数内直接使用return返回即可
方式一：

![image-20240414095006703](image\image-20240414095006703.png)
方式二：
![image-20240414095432018](image\image-20240414095432018.png)

调用：函数名称（实际参数列表）
												![image-20240414095029213](image\image-20240414095029213.png)		

注意：JS中，函数调用可以传递任意个参数，但函数只会取前面若干个（由形参数量决定）

### JS对象

#### Array

JavaScript中Array对象用于定义数组
定义：

```javascript
/*var 变量名 = new Array(元素列表);//方式一*/
var arr = new Array(1,2,3,4);

/*var 变量名 = [元素列表]//方式二*/
var arr = [1,2,3,4];
```

访问：

```javascript
/*arr[索引] = 值;*/
arr[10] = "hello";
```

JavaScript中的数组相当于Java中的集合，数组长度是可变的，而JavaScript是弱类型语言，所以可以存储任意类型的数据

##### 属性

|  属性  |            描述            |
| :----: | :------------------------: |
| length | 设置或返回数组中元素的数量 |

##### 方法

|   方法    |                       描述                       |
| :-------: | :----------------------------------------------: |
| forEach() | 遍历数组中的每个有值的元素，并调用一次传入的函数 |
|  push()   |     将新元素添加到数组的末尾，并返回新的长度     |
| splice()  |                 从数组中删除元素                 |

箭头函数（ES6）：用来简化函数定义语法。具体形式为：(...) => {...}，如果需要给箭头函数起名字：var xxx = (...) => {...}

#### String

创建方式跟Java相同

属性：

|  属性  |    描述    |
| :----: | :--------: |
| length | 字符串长度 |

方法：

|   方法    |                 描述                 |
| :-------: | :----------------------------------: |
| charAt()  |          返回指定位置的字符          |
| indexOf() |              检索字符串              |
|  trim()   |         去除字符串两边的空格         |
| substring | 提取字符串中两个指定索引号之间的字符 |

#### JavaScript自定义对象

定义格式：

```javascript
var 对象名 = {
  属性名1：属性值1,
  属性名2：属性值2,
  属性名3：属性值3,
  函数名称：function(形参列表){}//标准写法
  函数名称(形参列表){}//简化写法
};
```

调用格式：

```javascript
对象名.属性名;
```

```javascript
对象名.函数名();
```

#### JSON

概念：JavaScript Object Notation，JavaScript对象标记法
JSON是通过JavaScript对象标记法书写的 文本
由于其语法简单，层次结构鲜明，现多用于作为数据载体，在网络中进行数据传输

```json
{
 	"name":"Tom",
    "age":20,
    "gender":"male"
}
```

##### 基础语法

```json
var 变量名 = '{"key1":value1,"key2":value2}'
```

value的数据类型为：
				数字（整数或者浮点数）
				字符串（在双引号中）
				逻辑值（true或false）
				数组（在方括号中）
				对象（在花括号中）
				null

JSON字符串转为JS对象

```javascript
var jsObject = JSON.parse(userStr);
```

JS对象转换为JSON字符串

```javascript
var jsonStr = JSON.stringify(jsObject);
```

#### BOM

##### window

介绍：浏览器窗口对象。
获取：直接使用window，其中 `window.` 可以省略。`window.alert("Hello Window");`等同于 `alert("Hello Window");`

属性：
		history：对于history对象的只读引用。请参阅[History对象](https://www.w3school.com.cn/js/js_window_history.asp)
		location：用于窗口或框架的Location对象(地址栏)。请参阅[Location对象](https://www.w3school.com.cn/js/js_window_location.asp)
		navigator：对Navigator对象的只读引用。请参阅[Navigator对象](https://www.w3school.com.cn/js/js_window_navigator.asp)

方法：
		alert()：显示带有一段消息和一个确认按钮的警告框
		confirm()：显示带有一段消息以及确认按钮和取消按钮的对话框。
		setInterval()：按照指定的周期（以毫秒计）来调用函数或计算表达式。（执行多次）
		setTimeout()：在指定的毫秒数后调用函数或计算表达式。（执行一次）

##### Location

介绍：地址栏对象
获取：使用`window.location` 获取，其中 `window.`可以省略

​			`window.location.属性`        `location.属性`
属性：
​		href：设置或返回完整的URL
​		

```javascript
location.href = "https:www.bilibili.com" /*将当前地址设定为指定URL，并自动跳转*/
```

#### DOM

概念：Document Object Model ，文档对象模型
将标记语言的各个组成部分封装为对应的对象：
		Document：整个文档对象
		Element：元素对象
		Attribute：属性对象
		Text：文本对象
		Comment：注释对象

![image-20240414162149021](image\image-20240414162149021.png)

JavaScript通过DOM，就能够对HTML进行操作：
		改变HTML元素的内容
		改变HTML元素的样式（CSS）
		对HTML DOM 事件作出反应
		添加和删除HTML元素

##### 获取Element对象

HTML中的Elem对象可以通过Document对象获取，而Document对象是通过window对象获取的。
Document对象中提供了以下获取Element元素对象的函数：

1. 根据id属性获取，返回单个Element对象

   ```js
   var h1 = document.getElementById('h1');
   ```

2. 根据标签名称获取，返回Element对象数组

   ```js
   var divs = document.getElementsByTagName('div');
   ```

3. 根据 `name` 属性值获取，返回Element对象数组

   ```js
   var hobbys = document.getElementsByName('hobby');
   ```

4. 根据 `class` 属性值获取，返回Element对象数组

   ```js
   var clss = document.getElementsByClassName('cls');
   ```

获取到元素对象之后，查阅[参考手册](https://www.w3school.com.cn/jsref/index.asp)，找到对应方法来对其记录的内容进行操作

```js
/*假如需要修改 <div> 标签记录的值 为 "晚安玛卡巴卡" */
var divs = document.getElementsByTagName('div');
var div1 = divs[0];

div1.innerHTML = "晚安玛卡巴卡";
```

innerHTML属性可以识别HTML标签

### JS事件监听

事件：HTML事件是发生在HTML元素上的“事情”。比如：
		按钮被点击
		鼠标移动到元素上
		按下按键按钮

事件监听：JavaScript可以在事件被侦测到时执行指定代码。

#### 事件绑定

方式一：通过HTML标签中的事件属性进行绑定

```html
<input type = "button" onclick = "on()" value = "按钮1">

<script>
    function on(){
        //相应代码
    }
</script>
```

方式二：通过DOM元素属性绑定

```html
<input type = "button" id = "btn" value = "按钮1">
<script>
    document,getElementById('btn').onClick = function(){
        //相应代码
    }
</script>
```

#### 常见事件

|    事件名     |                 说明                 |
| :-----------: | :----------------------------------: |
|   onclick()   |             鼠标单击事件             |
|   onblur()    |             元素失去焦点             |
|   onfocus()   |             元素获得焦点             |
|   onload()    |       某个页面或图像被完成加载       |
|  onsubmit()   | 当表单提交时触发该事件（作用于form） |
|  onkeydown()  |     某个键盘的键被按下（任意键）     |
| onmouseover() |          鼠标移到某元素之上          |
| onmouseout()  |           鼠标从某元素移开           |

## 三、vue

- vue是一套前端框架，免除原生JavaScript中的DOM操作，简化书写
- 基于MVVM（Model-View-ViewModel）思想，实现数据的双向绑定，将编程的关注点放在数据上
- 官网：https://v2.cn.vuejs.org/

`框架：是一个半成品软件，是一套可重用的、通用的、软件基础代码模型。基于框架开发，更加快捷、更加高效。`

### Vue快速入门

- 新建HTML页面，引入Vue.js文件

  ```html
  <script src="js/js/vue.js"></script>
  ```

- 在JS代码区域（`<body>`外），创建Vue核心对象，定义数据模型

  ```html
  <script>
      //定义对象
      new Vue({
          el:"#app",//vue接管的区域
          data() {
              return {
                  massage:"Hello Vue"
              }
          },
      })
  </script>
  ```

- 编写视图（`<body>`内）

  ```html
  <div id="app">
      <input type="text" v-model="massage">
      <!-- 插值表达式 -->
      {{massage}}
  </div>
  ```

### 插值表达式

- 形式：{{表达式}}
- 内容可以是：
  - 变量
  - 三元运算符
  - 函数调用
  - 算术yun's

### Vue-常用指令

|   指令    |                        作用                         |
| :-------: | :-------------------------------------------------: |
|  v-bind   |     为HTML标签绑定属性值，如设置href、css样式等     |
|  v-model  |        在表单元(非表格)素上创建双向数据绑定         |
|   v-on    |                 为HTML标签绑定事件                  |
|   v-if    |  条件性的渲染某元素，判定为true时渲染，否则不渲染   |
| v-else-if |  条件性的渲染某元素，判定为true时渲染，否则不渲染   |
|  v-else   |  条件性的渲染某元素，判定为true时渲染，否则不渲染   |
|  v-show   | 根据条件展示某元素，区别在于切换的是display属性的值 |

### Vue-生命周期

**生命周期**：指一个对象从创建到销毁的整个过程

**生命周期的8个阶段**：每触发一个生命周期事件，会自动执行一个生命周期方法（钩子）。

|     状态      | 阶段周期 |
| :-----------: | :------: |
| beforeCreate  |  创建前  |
|    created    |  创建后  |
|  beforeMount  |  挂载前  |
|    mounted    | 挂载完成 |
| beforeUpdate  |  更新前  |
|    update     |  更新后  |
| beforeDestroy |  销毁前  |
|   destoryed   |  销毁后  |

![image-20240420190337259](image\image-20240420190337259.png)

每一个生命周期都有对应的方法，如beforeCreate（），created（），这些方法定义在Vue对象里，与method、data平级，并且是**自动调用**的。 

**重点关注**：mounted

- mounted：挂载完成，Vue初始化完成，HTML页面渲染完成。（这时可以利用mounted（）发送请求到服务端，加载数据）

## 四、Ajax

**概念**：Asynchronous JavaScript And XML，异步的JavaScript和XML

**作用**:

- 数据交换：通过Ajax可以给服务器发送请求，并获取服务器响应的数据。
- 异步交互：可以在不重新加载整个页面的情况下，与服务器交换数据并更新部分网页的技术，如：联想搜索，用户名是否可用校验等等。

**同步和异步**

![image-20240421095200769](image\image-20240421095200769.png)

**原生Ajax**（过于繁琐）

1. 准备数据地址
2. 创建XMLHttpRequest对象：用于和服务器交换数据
3. 想服务器发送请求
4. 获取服务器响应数据

### Axios入门

1. 引入Axios的js文件

   ```html
   <script src="js/axios-0.18.0.js"></script>
   ```

2. 使用Axios发送请求

```js
axios.get(url[,config])
axios.delete(url[,config])
axios.post(url[,data[,config]])
axios.put(url[,data[,config]])
```

如：

```html
<script>
    function get(){
        // 通过axios发送异步请求 - get
        // axios({
        //     method:"get",
        //     url:"http://yapi.smart-xwork.cn/mock/169327/emp/list"
        // }).then(result =>{
        //     console.log(result.data)
        // })

        // 简化写法
        axios.get("http://yapi.smart-xwork.cn/mock/169327/emp/list").then(result =>{
            console.log(result.data)
        })
    }

    function post(){
        // 通过axios发送异步请求 - get
        // axios({
        //     method:"post",
        //     url:"http://yapi.smart-xwork.cn/mock/169327/emp/deleteById",
        //     data:"id=1"
        // }).then(result =>{
        //     console.log(result.data)
        // })

        // 简化写法
        axios.post("http://yapi.smart-xwork.cn/mock/169327/emp/deleteById","id=1").then(result =>{
            console.log(result.data)
        })
    }
</script>
```

## 五、前后端分离开发

**介绍**

![image-20240421112041939](image\image-20240421112041939.png)

### YApi

可能需要本地部署

### 前端工程化

**概念**：指在企业级的前端开发中，把前端开发所需的工具、技术、流程、经验等进行规范化、标准化。

**环境准备**： 

**vue-cli**：Vue-cli是官方提供的一个脚手架，用于快速生成一个Vue的项目模板

Vue-cli提供了如下功能：

- ​	统一的目录结构
- 本地测试
- 热部署
- 单元测试
- 集成打包上线

依赖环境：NodeJS

### Vue组件库Element

**什么是Element？**

Element：是饿了么团队研发的，一套为开发者、设计师和产品经理准备的基于Vue 2.0的桌面端组件库

组件：组成网页的部件，例如：超链接、按钮、图片、表格、表单、分页单等。

官网：https://element.eleme.cn/#/zh-CNListener

#### 常见组件

[Element组件库](https://element.eleme.cn/#/zh-CN/component/installation)

- 表格table
- 分页pageination
- 对话框dialog
- 表单form

将js对象转换为字符串    JSON.stringify(Object)

#### 案例

**根据页面原型完成员工管理页面开发，并通过Axios完成数据异步加载**

步骤：

- 创建页面，完成页面的整体布局规划
- 布局中各个部分的组件实现
- 列表数据的异步加载，并渲染展示

### Vue路由

**Vue Router**

- 介绍：Vue Router是Vue的官方路由
- 组成
  - VueRouter：路由器类，根据路由请求在路由视图中动态渲染选中的组件
  - `<router-link>`：请求连接组件，浏览器会接解析成 `<a>`
  - `<router-view>`:动态视图组件，用来渲染展示与路由路径对应的组件

### 部署

- **前端**：Nginx
  - 介绍Nginx是一款轻量级的Web服务器/反向代理服务器即3电子邮件（IMAP/POP3）代理服务器。其特点是占用内存少，并发能力强，在各大型互联网公司都有非常广泛的使用。
  - 官网：https://nginx.org/
    ![image-20240423204638049](image\image-20240423204638049.png)

# 后端

## 一、maven

**什么是Maven？**

Maven是apache旗下的一个开源项目，是一款用于管理和构建Java项目的工具。

**作用**：

- 管理依赖：方便快捷地管理项目依赖地资源（jar包），避免版本冲突问题
-  统一项目结构：提供标准、统一地项目结构
  ![image-20240423210821708](image\image-20240423210821708.png)
- 项目构建：标准跨平台（Linux、Windows、MacOS）的自动化项目构建方式
- 官网：http://maven.apache.org/

### Maven概述

**介绍**：Apache Maven是一个项目管理的构建工具，它基于项目对象模型（POM,project object model）的概念，通过一小段信息来管理项目的构建。

**仓库**:用于存储资源，管理各种jar包。

- 本地仓库：自己计算机上的一个目录。
- 中央仓库：由Maven团队维护的全球唯一的。仓库地址：https://repo1.maven.org/maven2/
- 远程仓库（私服）：一般由公司团队搭建的私有仓库。

#### Maven坐标

**概念**:

- Maven中的坐标是资源的唯一标识，通过该坐标可以唯一定位资源的位置。
- 使用坐标来定义项目或引入项目中需要的依赖

**组成**：

- groupId：定义当前Maven项目隶属组织名称（通用是域名反写，例如：com.itheima）
- artifactId：定义当前Maven项目名称（通常是模块名称，例如order-service、goods-service）
- version：定义当前项目版本号

### IDEA导入Maven项目

**方式一**：打开IDEA，选择右侧Maven面板，点击 + 号，选中对应项目的pom.xml文件，双击即可

![image-20240428205739132](image\image-20240428205739132.png)

**方式二**：打开IDEA，File -> Project Structure -> Modules -> 点击 + 号 ->Import Module ->双击要导入项目的pom.xml文件

### 依赖管理

#### 依赖配置

**依赖**：指当前项目运行所需要的jar包，一个项目中可以引入多个依赖。

**配置**：

1. 在pom.xml中编写 `<dependencies>`标签
2. 在`<dependencies>`标签中使用 `<dependency>`引入坐标
3. 定义坐标的groupId、artifactId、version
4. 点击刷新按钮、引入最新加入的坐标

**注意**：

- 如果引入的依赖，在本地仓库中不存在，将会连接远程的仓库/中央仓库，然后下载依赖。
- 如果不知道依赖的坐标信息，可以到https://mvnrepository.com/中搜索。

#### 依赖传递

- 依赖具有传递性

  - 直接依赖：在当前项目中通过依赖配置建立的依赖关系
  - 间接依赖：别依赖的资源如果依赖其他资源，当前项目间接依赖其他资源
    ![image-20240428220217744](image\image-20240428220217744.png)

- 排除依赖使用 `<exclusions>`标签

  ```xml
  <dependency>
      <exclusions>
          <exclusion>
              <groupId>xxx.xxx</groupId>
              <artifactId>xxx-xxx</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  ```

#### 依赖范围

依赖的jar包，在默认情况下，可以在任何地方使用。可以通过 `<scope>...</scope>`设置其作用范围

作用范围：

- 主程序范围有效。（main文件夹范围内）
- 测试程序范围有效。（test文件夹范围内）
- 是否参与打包运行。（package指令范围内）

```xml
<-- 指定作用范围 -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
    
    <scope>test</scope>
    
</dependency>
```

|     scope值     | 主程序 | 测试程序 | 打包（运行） |    范例     |
| :-------------: | :----: | :------: | :----------: | :---------: |
| compile（默认） |   Y    |    Y     |      Y       |    log4j    |
|      test       |   -    |    Y     |      -       |    junit    |
|    provided     |   Y    |    Y     |      -       | servlet-api |
|     runtime     |   -    |    Y     |      Y       |  jdbc驱动   |

#### 生命周期

- clean：移除上一次构建生成的文件
- compile：编译项目源代码
- test：使用合适的单元测试框架运行测试（junit）
- package：将编译后的文件打包，如：jar、war等
- install：安装目录到本地仓库

![image-20240428223820027](image\image-20240428223820027.png)

**注意**：

- 每套生命周期包含一些阶段（phase），阶段是有顺序的，后面的阶段依赖于前面的阶段
- Maven的生命周期是一个抽象的概念，每个阶段要执行的功能由对应的插件完成

![image-20240428224445328](image\image-20240428224445328.png)

## 二、Web后端开发
### Spring

- **官网**：https://spring.io
- Spring发展到今天已经形成了一种开发生态圈，Spring提供了若干个子项目，每个项目用于完成指定的功能。

### SpringBootWeb快速入门

**步骤**：

1. 创建springboot工程，并勾选web开发相关依赖
2. 定义HelloController类，添加hello方法，并添加注解。
3. 运行测试

### HTTP协议

**协议**：约定好的数据的格式（或约定好的数据传输的规则）

#### HTTP-概述

- **概念**：Hyper Text Transfer Protocol，超文本传输协议，规定了浏览器和服务器之间数据传输的规则。
  ![image-20240429214203517](E:\TyporaNote\image\image-20240429214203517.png)
- **特点**：
  1. 基于TCP协议：面向连接，安全
  2. 基于请求-响应模型的：一次请求对应一次响应
  3. HTTP协议是无状态的协议：对于事务处理没有记忆能力，每次请求-响应都是独立的。
     - 缺点：多次请求之间不能共享数据
     - 优点：速度快

#### HTTP-请求协议

```tex
POST /javaweb_project/springboot-web-quickstart/static/01.GET-POST.html?name=Tom&password=123 HTTP/1.1
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6
Cache-Control: max-age=0
Connection: keep-alive
Content-Length: 21
Content-Type: application/x-www-form-urlencoded
Cookie: Idea-4244686b=768a9530-d880-40a7-b3d5-0898c4cae54a
Host: localhost:63342
Origin: http://localhost:63342
Referer: http://localhost:63342/javaweb_project/springboot-web-quickstart/static/01.GET-POST.html?name=Tom&password=123
Sec-Fetch-Dest: document
Sec-Fetch-Mode: navigate
Sec-Fetch-Site: same-origin
Sec-Fetch-User: ?1
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36
sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="122", "Google Chrome";v="122"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "Windows"
```

**请求行**：请求数据的第一行（请求方式、资源路径、协议及其版本）

**请求头**：第二行开始，格式key:value

- 常见的请求头

|      名称       | 作用                                                         |
| :-------------: | :----------------------------------------------------------- |
|      Host       | 请求的主机名                                                 |
|   User-Agent    | 浏览器版本，例如Chrome浏览器的标识类似Mozilla/5.0 ... Chrome/79，IE浏览器的标识类似Mozilla/5.0(Windows NT ...)like Gecko |
|     Accept      | 表示浏览器能接收的资源类型，如`text/*`文本, `image/*`图片 或者 `*/*`表示所有 |
| Accept-Language | 表示浏览器的偏好语言，服务器可以据此返回不同语言的网页       |
| Accept-Encoding | 表示浏览器可以支持的压缩类型，例如gzip，deflate等            |
|  Content-Type   | 请求主体的数据类型                                           |
| Content-Length  | 请求主体的大小（单位：字节）                                 |

**请求体**：POST请求，存放请求参数

- 请求方式-GET：请求参数在请求行中，没有请求体，如：/brand/findAll?name=OPPO&status=1。GET请求大小是有限制的。
- 请求方式-POST：请求参数在请求体中，POST请求大小是没有限制的。

#### HTTP-响应协议

```
HTTP/1.1 200 OK
content-type: text/html
date: Mon, 29 Apr 2024 14:19:46 GMT
x-frame-options: SameOrigin
X-Content-Type-Options: nosniff
x-xss-protection: 1; mode=block
accept-ranges: bytes
cache-control: private, must-revalidate
last-modified: Mon, 29 Apr 2024 14:19:30 GMT
content-length: 569
access-control-allow-origin: http://localhost:63342
vary: origin
access-control-allow-credentials: true
```

**响应行**：响应数据第一行（协议、状态码、描述）

**响应头**：第二行开始，格式key:value

**响应体**：最后一部分，存放响应数据

响应状态码的分类：

| 类型 | 含义                                                         |
| :--: | ------------------------------------------------------------ |
| 1xx  | 响应中-临时状态码，表示请求已经接收，告诉客户端应该继续请求或者如果它已经完成则忽略它 |
| 2xx  | 成功-表示请求已经被成功接收，处理已完成                      |
| 3xx  | 重定向-重定向到其他地方；让客户端再发起一次请求以完成这个处理 |
| 4xx  | 客户端错误-处理发生错误，责任在客户端。如：请求不了不存在的资源、客户端未被授权、进制访问等 |
| 5xx  | 服务器错误-处理发生错误，责任在服务端。如：程序抛出异常      |

[**常见的响应状态码**](E:\TyporaNote\响应状态码.md)

常见的响应头：

|       名称       | 含义                                                       |
| :--------------: | ---------------------------------------------------------- |
|   Content-Type   | 表示该响应内容的类型，例如text/html，application/json      |
|  Content-Length  | 表示该响应内容的长度（字节数）                             |
| Content-Encoding | 表示该相应压缩宣发，例如gzip                               |
|  Cache-Control   | 指示客户端应如何缓存，例如max-age=300表示可以最多缓存300秒 |
|    Set-Cookie    | 告诉浏览器为当前页面所在的域设置cookie                     |

#### HTTP-协议解析

根据协议规定编写服务器程序

### Web服务器-Tomcat

**Web服务器**：

- 对HTTP协议操作进行封装，简化web程序开发
- 部署web项目，对外提供网上信息浏览服务

**Tomcat**：

- 一个轻量级的web服务器，支持servlet，jsp等少量Java EE规范
- 也被称为web容器，servlet容器

#### 安装使用

- 下载：官网下载，地址： [Apache Tomcat® - Apache Tomcat 9 Software Downloads](https://tomcat.apache.org/download-90.cgi)

- 安装：绿色版，直接解压即可

- 卸载：直接删除目录即可

- 启动：双击：bin\startup,bat

  - 控制台中文乱码：修改conf/logging.properties，将控制台编码改为GBK

    ```properties
    java.util.logging.ConsoleHandler.level = FINE
    java.util.logging.ConsoleHandler.formatter = org.apache.juli.OneLineFormatter
    java.util.logging.ConsoleHandler.encoding = GBK
    ```

    

- 关闭：

  - 直接关闭运行窗口：强制关闭
  - bin\shutdown.bat：正常关闭
  - Ctrl+C：正常关闭

**起步依赖**：

- spring-boot-starter-web：包含了web应用开发所需要的常见依赖
- spring-boot-starter-test：包含了单元测试所需要的常见依赖
- 官方提供的starter：https://docs.spring.io/spring-boot/docs/3.2.5/reference/htmlsingle/#using.bulid-systems.starters

**内嵌tomcat服务器**：

- 基于springboot开发的web应用程序，内置了tomcat服务器，当启动类运行时，会自动启动内嵌的tomcat服务器

### 请求响应

![image-20240503165455300](E:\TyporaNote\image\image-20240503165455300.png)

- 请求（HttpServletRequest）：获取请求数据
- 响应（HttpServletResponse）：设置响应数据
- BS架构：Browser/Server，浏览器/服务器架构。客户端只需要浏览器，应用程序的逻辑和数据都存储在服务端。（维护方便，但体验一般）
- CS架构：Client/Sever。客户端/服务端架构模式。（开发、维护麻烦，但体验不错）

### 请求

#### Postman

- postman时一款功能强大的网页调试与发送网页HTTP请求的Chrome插件
- 作用：常用于进行接口测试

#### 常见参数

1. 原始方式获取请求参数

   - Controller方法形参中声明HttpServletRequest对象

   - 调用对象的getParameter（参数名），获取的值为String类型

     ```java
     //原始方式
     @RequestMapping("/simpleParam")
     public String simpleParam(HttpServletRequest request){
         //获取请求参数
         String name = request.getParameter("name");
         String ageStr = request.getParameter("age");
         int age = Integer.parseInt(ageStr);
     
         System.out.println(name +":"+ age);
         return "OK";
     }
     ```

2. SpringBoot中接收简单参数

   - 请求参数与方法形参变量相同就可以接收
   - 会进行自动转换

   ```java
   //springboot方式
   @RequestMapping("/simpleParam")
   public String simpleParam(String name,Integer age){
       System.out.println(name +":"+ age);
       return "OK";
   }
   ```

3. @RequestParam注解

   - 方法形参名称和请求参数的名称不匹配，通过该注解完成映射
   - 该注解的required属性默认值为true，代表该注解参数必须传递

   ```java
   //springboot方式
   @RequestMapping("/simpleParam")
   public String simpleParam(@RequestParam(name = "name") String username, Integer age){
       System.out.println(username +":"+ age);
       return "OK";
   }
   ```

#### 实体参数

- 简单实体对象：请求参数名和形参对象属性名相同，定义POJO（简单的Java对象）接收即可

```Java
public class User {
    private String name;
    private int age;
    }
```

| KEY  | VALUE |
| :--: | :---: |
| name |  TOM  |
| age  |  20   |

```Java
//简单实体参数
@RequestMapping("/simplePojo")
public String simplePojo(User user){
    System.out.println(user);
    return "OK";
}
```

- 复杂实体对象：请求参数与形参对象属性名相同，按照对象的层级结构关系即可接收嵌套POJO属性参数

```Java
public class User {
    private String name;
    private int age;
    private Address address;
    }
    
public class Address {
    private String province;
    private String city;
}
    
```

|       KEY        | VALUE |
| :--------------: | :---: |
|       name       |  TOM  |
|       age        |  20   |
| address.province | 北京  |
|   address.city   | 北京  |

```Java
//复杂实体参数
@RequestMapping("/complexPojo")
public String complexPojo(User user){
    System.out.println(user);
    return "OK";
}
```

#### 数组集合参数

|  KEY  |     VALUE      |
| :---: | :------------: |
| hobby | Genshin Impact |
| hobby |      Java      |
| hobby |    bicycle     |

- 数组参数：请求参数名与形参数组名称相同且请求参数为多个，定义数组类型的形参即可接收参数

```Java
//数组参数
@RequestMapping("/arrayParam")
public String arrayParam(String[] hobby){
    System.out.println(Arrays.toString(hobby));
    return "OK";
}
```

- 集合参数：请求参数名与形参集合名称相同且请求参数为多个，@RequestParam绑定参数关系

```Java
//集合参数
@RequestMapping("/listParam")
public String listParam(@RequestParam List<String> hobby){
    System.out.println(hobby);
    return "OK";
}
```

#### 日期时间参数

|    KEY     |        VALUE        |
| :--------: | :-----------------: |
| updateTime | 2024-05-03 23:55:20 |

- 使用@DateTimeFormat()注解完成时间日期格式转换

```Java
//时间日期参数
@RequestMapping("/dateParam")
public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime updateTime){
    System.out.println(updateTime);
    return "OK";
}
```

#### JSON参数

```json
{
	"name": "TOM",
	"age": 20,
	"address": {
		"province": "北京",
		"city": "北京"
	}
}
```

- JSON参数：JSON数据键名与形参对象属性名相同，定义POJO类型形参即可接收参数，需要使用@RequestBody标识绑定数据

```java
//JSON参数
@RequestMapping("/jsonParam")
public String jsonParam(@RequestBody User user){
    System.out.println(user);
    return "OK";
}
```

#### 路径参数

```http
http://localhost:8080/path/10/TOM
```



- 路径参数：通过请求URL直接传递参数，使用{...}来标识路径参数，需要使用获取路径参数

```Java
//路径参数(单个)
@RequestMapping("/path/{id}")
public String pathParam(@PathVariable Integer id){
    System.out.println(id);
    return "OK";
}

//路径参数(多个)
@RequestMapping("/path/{id}/{name}")
public String pathParam(@PathVariable Integer id,@PathVariable String name){
    System.out.println(id + "/" + name);
    return "OK";
}
```
