# 创建一个Vue应用程序

官网：[快速上手 | Vue.js (vuejs.org)](https://cn.vuejs.org/guide/quick-start.html)

- **渐进式**：是只可以按需引入Vue.js的部分功能，而不必全量引入整个框架

1. 创建一个demo文件夹，拖动到VScode打开，新建一个demo.html文件，输入html选择第二个 5

   ```vue
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>demo</title>
   </head>
   <body>
       
   </body>
   </html>
   ```

2. 引入js文件

   ```vue
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>demo</title>
       <!-- 引入js文件 -->
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
   </head>
   <body>
      
       </script>
   </body>
   </html>
   ```

3. 创建一个vue程序

   ```vue
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>demo</title>
       <!-- 引入js文件 -->
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
   </head>
   <body>
       <script>
           // 创建一个vue应用程序
           Vue.createApp({
               // setup选项 用于设置响应式数据和方法等
               setup() {
                   // 设置返回数据
                   return {
                       msg:"success",
                   }
               }
           })
       </script>
   </body>
   </html>
   ```

4. 创建一个页面div元素，用于显示返回信息

   ```vue
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>demo</title>
       <!-- 引入js文件 -->
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
   </head>
   <body>
   	// 创建一个页面div元素，用于显示返回信息
       <div id="app">
           // 用插值表达式 {{}} 获取返回数据
           {{msg}}
       </div>
       
       <script>
           // 创建一个vue应用程序
           Vue.createApp({
               // setup选项 用于设置响应式数据和方法等
               setup() {
                   
                   return {
                       msg:"success",
                   }
               }
           }).mount("#app") // 用.mount将返回数据挂载到 app
       </script>
   </body>
   </html>
   ```

5. 返回数据不仅限于字符串，还可以是其他，比如对象

   ```vue
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>demo</title>
       <!-- 引入js文件 -->
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
   </head>
   <body>
       // 创建一个页面div元素，用于显示返回信息
       <div id="app">
           {{msg}}
   
           <h2>{{ web.title }}</h2>
           <h2>{{ web.description }}</h2>
       </div>
       
       <script>
           // 解构赋值语法 将括号中的属性赋值给对应变量 ，这样在引用方法时就不用写 Vue. 了
           const {createApp,reactive} = Vue
           // 创建一个vue应用程序
           createApp({
               // setup选项 用于设置响应式数据和方法等
               setup() {
                   const web = reactive({
                       title:"醉卧梦学vue",
                       description: "为有牺牲多壮志，敢叫日月换新天"
                   })
                   
                   return {
                       msg:"success",
                       web
                   }
               }
           }).mount("#app")
       </script>
   </body>
   </html>
   ```

6. 右键——》open in default browser

   ![image-20240901112031684](E:\TyporaNote\image\image-20240901112031684.png)

# Vue模块化开发

使用`vue.global.js`时传统开发方式，模块化开发使用的是`vue.esm-browser.js`，对上一节代码进行修改

1. 删除引入的js文件

2. 在 `script`标签添加属性

   ```vue
   <script type="module">
   ```

3. 将解构赋值语句改为

   ```vue
   import {createApp,reactive} from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
   ```

4. 安装 Lives Server插件（不安装会无法正确获得数据）

   ![image-20240901113942078](E:\TyporaNote\image\image-20240901113942078.png)

5. 右键 ——>open with Live Server

6. 完整代码

   ```vue
   <!DOCTYPE html>
   <html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>demo</title>
   </head>
   <body>
       <div id="app">
           {{msg}}
   
           <h2>{{ web.title }}</h2>
           <h2>{{ web.description }}</h2>
       </div>
       
       <script type="module">
          
           import {createApp,reactive} from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'
           // 创建一个vue应用程序
           createApp({
               // setup选项 用于设置响应式数据和方法等
               setup() {
                   const web = reactive({
                       title:"醉卧梦学vue",
                       description: "为有牺牲多壮志，敢叫日月换新天"
                   })
                   
                   return {
                       msg:"success",
                       web
                   }
               }
           }).mount("#app")
       </script>
   </body>
   </html>
   ```

## ref和reactive

- **ref()**：一般用于存储单个基本类型的数据，如：数字，字符串等，但也可以存储数组

  - 通过 .value属性来访问和修复ref定义的内容
  - 将ref添加到`import {createApp,reactive,ref} from 'https://unpkg.com/vue@3/dist/vue.esm-browser.js'`，才能不写`Vue.`

  ```js
  const number1 = ref(10)
  const number2 = ref[{1,2,3}]
  ```

- **reactive()**：一般用于存储复杂的数据类型，如：对象和数组等

  ```js
  const web = reactive({
       title:"醉卧梦学vue",
       description: "为有牺牲多壮志，敢叫日月换新天"
    })
  ```

## 绑定事件v-on 简写@

### 点击事件

- 在`script`标签内的**`setup`**中创建一个函数，特定事件被触发时调用

  ```js
  // 定义方法，将web中description改为
  const edit = () => {
        web.description = "大鹏一日同风起，扶摇直上九万里"
    }
  return {
      msg:"success",
      number,
      web,
      edit //将方法添加到return内
     }
  ```

- 在`div`标签内创建button，绑定`edit`函数

  ```html
  <button v-on:click="edit">修改</button>
  <!-- 也可以简写为 -->
  <button @click="edit">简化版修改</button>
  ```

- 这样，当按钮被**点击**时，`web.description` 的内容就会变成："大鹏一日同风起，扶摇直上九万里"

### 键盘监听事件

- **`@keyup`**：按键按下被松开的瞬间触发
- **`@keydown`**：按键按下即触发
- `@keyup.X.Y="xxx()"` 表示用户按下X + Y 按键后松开的时刻触发`xxx()`函数

**演示**：

- 同上，先在`script`标签内的**`setup`**中创建一个函数，并添加到`return`中

  ```js
  const web = reactive({
     title:"醉卧梦学vue",
     description: "为有牺牲多壮志，敢叫日月换新天",
     user: 0 // 新建一个属性，表示用户数量
   })
  const add = (a,b) => {
           web.user += a+b
         }
  return {
      msg:"success",
      number,
      web,
      edit,
      add
   }
  ```

- 在`div`标签内创建一个文本框，用于绑定事件

  ```html
  回车<input type="text" @keyup.enter="add(40,60)">
  ```

- 这里表示当按下并松开`enter`键后，将会触发事件执行`add(40，60)`函数，将响应式对象`web`的`user`字段的值自增 `40+60` 

## 显示和隐藏 v-show

一般vue组件都会有一个 `v-show`属性，包括自定义

- **`v-show="true"`**，表示内容可以正常浏览
- **`v-show="false"`** ，表示内容不可浏览

而用`reactive`创建的响应式对象也有一个布尔类型的属性`show`，默认值为 `true`（暂时不知道有什么用）

**演示**：

- 在`script`标签内的**`setup`**中创建一个函数，创建一个对象和函数

  ```js
   // 定义对象
   const web = reactive({
       // 为了方便演示，这里直接对show属性赋值
      show: true
   })
   // 定义函数
   const toggle = () =>{
       // 当函数被调用时，对web.show的值进行取反操作，true变false，false变true
       web.show = !web.show 
   }
   return {
    toggle,
    web
    }
  ```

- 在`div`标签内创建显示内容

  ```html
          {{ web.show }}
          <hr>
          <!-- web.show为true时显示  泰山陨石坠 -->
          出招：<p v-show="web.show">泰山陨石坠</p>    
          <button @click="toggle">切换显示状态</button>
  ```

- 点击的时候`切换显示状态`按钮即可将 `泰山陨石坠`显示或隐藏

**PS**：浏览器按`F12`，选中 **`泰山陨石坠`**文字，控制台切换到元素界面，可以看到文字的显示和隐藏是通过改变 `display`属性实现的

## 条件渲染 v-if

> v-show通过css display属性，来控制元素的显示和隐藏
>
> v-if用于对元素进行条件渲染。当条件为true时，渲染该元素，为false时，则不渲染

- **`v-show`**适用于频繁切换元素的显示状态，因为只改变了display属性，不需要重新渲染整个组件
- **`v-if`**适用于较少改变的场景，因为频繁从dom中删除或添加元素，会导致性能下降

```html
<p v-if="web.show"></p>
```

- 有if就有else

```js
        <p v-if="web.user < 1000">新网站</p>
        <p v-else-if="web.user >= 1000 && web.user < 10000">优秀网站</p>
        <p v-else="">资深网站</p>
```

