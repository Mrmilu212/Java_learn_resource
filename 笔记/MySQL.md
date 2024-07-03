# 初识MySQL

**课程链接**：[初识 MySQL_MySQL数据库（上）- 基础入门-慕课网 (imooc.com)](https://www.imooc.com/video/24916)

**使用的工具**：MySQL、navicat （数据库图形化操作软件）

### 什么是数据库

- 存储和管理数据的仓库，本质上就是一个软件
- 存储和管理：对数据的增删改查

### 数据库的分类

**关系型数据库**

- 以表的形式存储数据，表与表之间可以有很多复杂的关系
- MySQL、Oracle、SQL Server等

**非关系型数据库**

- 以数据集的方式存储数据，大量的数据集中存储在一起
- MongDB、Redis、Memcached等

### 什么是MySQL

- 关系型数据库
- 开源免费，最受欢迎的数据库之一
- 瑞典MySQL AB公司开发，现在属于Oracle

### MySQL如何存储数据

- 数据库：MySQL可以管理很多数据库
- 数据表：一个数据库可以有很多数据表
- 表中数据（记录）：一个表中可以有很多条数据

### 为什么要使用MySQL

- 持久化的存储数据需求
- 管理数据的需求
- 市场占有率很高

### MySQL学什么

- 认识SQL
- 数据库的增删改查
- 数据表的增删改查
- 表中额增删改查
- MySQL的预处理

# 认识SQL

### 什么是SQL

- Structured Query Language 结构化查询语言
- SQL是关系型数据库的语言标准

### SQL规范

- SQL对大小写不敏感
- 语句以 （;）结尾
- 以关键字或特殊字符（#@等）作为数据库名、表名或字段名，要用 ' ' 包裹

# 数据库的增删改查

### 查询数据库（查）

```sql
-- 显示所有数据库
SHOW DATABASES;
```

### 创建数据库（增）

```sql
-- 如果数据库存在会报错
CREATE DATABASE demo;
-- 如果数据库存在则创建，存在什么也不会做
-- 数据库存在不会报错（推荐使用）
CREATE DATABASE if NOT EXISTS demo;
```

### 删除数据库（删）

```SQL
-- 如果数据库存在则删除，否则什么都不做
-- 数据库不存在时不会报错（推荐使用）
DROP DATABASE if EXISTS demo
```

### 修改数据库（改）

```sql
-- 修改字符集编码
ALTER DATABASE demo CHARACTER set utf8mb4 COLLATE utf8mb4_0900_ai_ci
```

**查询当前使用的数据库**

```sql
-- 查询当前使用的数据库
SELECT databae();
```

**使用指定数据库**

```sql
-- 使用指定数据库
use demo;
```

# SQL数据类型

### 什么是SQL数据类型

- 数字、字符串、日期时间等类型

### 为什么需要数据类型

- 告诉数据库以多大的存储空间存储数据
- 合理的分配存储空间

### 常见的SQL数据类型

- 数字类型：整型、浮点型、定点型等
- 字符串类型：字符型、文本型、枚举型、集合型等
- 日期时间类型：日期型、日期时间型、时间戳型等

## 整数类型

- 专门用来保存整数
- 区分有符号和无符号，默认就是有符号的
- 可以在数据类型后加上unsigned表示无符号的

| 类型          | 字节 | 有符号取值范围                     | 无符号取值范围       | 说明     |
| ------------- | ---- | ---------------------------------- | -------------------- | -------- |
| tinyint       | 1    | (-128,127)                         | (0,255)              | 超小整数 |
| smallint      | 2    | (-32768,32768)                     | (0,65535)            | 小整数   |
| mediumint     | 3    | (-8388608,8388607)                 | (0,16777215)         | 中等整数 |
| int或interger | 4    | (-2147483648,2147483647)           | (0,4294967295)       | 整数     |
| bigint        | 8    | (-2<sup>63</sup>,2<sup>63</sup>-1) | (0,2<sup>64</sup>-1) | 大整数   |

- 在设置整形时，可以设置将来显示的位宽，不足的补足超出的不管（正常显示）
  - 默认填充空格
  - tinyint(2)：设置位宽是2，如果查询到的是一位，比如'1'，显示的就是 ' 1'
  - tinyint(2)zerofill：指定用0填充

## 浮点类型

- 专门保存小数，会丢失精度
- 不要用来保存不希望丢失精度的数据

| 类型   | 字节 | 说明               |
| ------ | ---- | ------------------ |
| float  | 4    | 单精度，精度是6位  |
| double | 8    | 双精度，精度是16位 |

## 定点类型

- 专门保存小数，不会丢失精度
- 可以用来保存不希望丢失精度的数据

| 类型            | 说明                                      |
| --------------- | ----------------------------------------- |
| decimal(size,d) | size指定数字的总位数，d指定小数部分的位数 |

## 字符串类型

| 类型            | 说明                                                         |
| --------------- | ------------------------------------------------------------ |
| char(size)      | 字符型，固定长度的字符串，可包含字母数字及特殊字符。<br />size是要存储字符串的长度，范围是0~255 |
| varchar(size)   | 字符型，可变长度的字符串，可以包含字母。数字及特殊字符。<br />size是最大长度，范围是0~65535 |
| tinytext        | 文本类型，长度是0~255的字符串文本                            |
| text            | 文本类型，长度为0~65535的字符串文本                          |
| mediumtext      | 文本类型，长度为0~16777215的字符串文本                       |
| longtext        | 文本类型，长度为0~4294967295的字符串文本                     |
| enum(v1,v2,...) | 枚举类型，插入的数据必须位于列表中，并且只能命中其中一个值   |
| set(v1,v2,...)  | 集合类型，插入的值可以命中其中的一个或多个值                 |

- 专门用来保存字符串
- 文本类型在表中并不会实际占用所保存的字节数
- 文本类型其实保存的是引用了实际保存数据的地址值

## 日期时间类型

| 类型      | 格式                | 取值范围                                        |
| --------- | ------------------- | ----------------------------------------------- |
| year      | YYYY                | 1901~2155、0000                                 |
| date      | YYYY-MM-DD          | 1000-01-01~9999-12-31                           |
| time      | hh:mm:ss或hhh:mm:ss | -838:59:59~838:59:59                            |
| datetime  | YYYY-MM-DD hh:mm:ss | 1000-01-01 00:00:00 ~9999-12-31 23:59:59        |
| timestamp | YYYY-MM-DD hh:mm:ss | 1970-01-01 00:00:01 UTC~2028-01-19 03:14:07 UTC |

- 专门用来日期和时间
- datetime和timestamp 可以自动初始化和更新为当前日期时间

```sql
updataAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
```

# 数据表的增删改查操作

### 查询数据表（查）

```sql
-- 查询数据表（查）
show tables;

-- 查看表结构
DESC student;
```

### 创建数据表（增）

```sql
-- 创建数据表（增）
CREATE TABLE IF NOT EXISTS student(
	id TINYINT UNSIGNED,
	name VARCHAR(20),
	age TINYINT UNSIGNED,
	gender ENUM('男','女','保密'),
	createAt TIMESTAMP
	)
```

### 删除数据表（删）

```sql
-- 删除数据表
-- 存在才删除
DROP TABLE IF EXISTS student;
```

### 修改数据表（改）

```sql
-- 修改数据表名
ALTER TABLE student RENAME TO stu;

-- 添加字段
ALTER TABLE student ADD updatedAt TIMESTAMP;
-- 删除字段
ALTER TABLE student DROP updatedAt;

-- 修改字段的数据类型
ALTER TABLE student MODIFY createAt DATETIME;
-- 修改字段名称
alter table student rename column createAt to createdAt;
-- 修改字段名称和数据类型
ALTER TABLE student CHANGE createdAt createAt TIMESTAMP;
```

# SQL约束

### 有哪些SQL约束

- primary key ：主键约束
- auto_increment：自动递增
- unique：唯一约束
- not null：非空约束
- default：默认值
- foreign key：外键约束

## 主键约束

```sql
-- 1.primary key 主键约束
-- 区分记录的唯一性
-- 值不能重复，也不能为空

-- 创建表不规定约束条件
CREATE TABLE if NOT EXISTS stu_1(
id TINYINT UNSIGNED,
name VARCHAR(20)
);
-- 创建表并规定主键约束
CREATE TABLE if NOT EXISTS stu_2(
id TINYINT UNSIGNED PRIMARY KEY,
name VARCHAR(20)
);
-- 另一种约束方式
CREATE TABLE if NOT EXISTS stu_3(
id TINYINT UNSIGNED ,
name VARCHAR(20),
PRIMARY KEY(id)
);

-- 一个表只能由一个主键，下面这段sql语句会报错
CREATE TABLE IF NOT EXISTS stu_4(
id TINYINT PRIMARY KEY,
name VARCHAR(20) PRIMARY KEY
);

-- 联合组件
-- 可以将多个字段作为主键，多个字段联合起来的值不能重复
-- 即至少要有一个值不能重复
CREATE TABLE IF NOT EXISTS stu_5(
id TINYINT,
name VARCHAR(20),
PRIMARY KEY(id,name)
);
```

## 自动递增

```sql
-- 2.auto_increment 自动递增
-- 传输数据时可以不用管添加了auto_increment的字段，sql会自动处理
CREATE TABLE IF NOT EXISTS stu_6(
id TINYINT UNSIGNED auto_increment,
name VARCHAR(20),
PRIMARY KEY(id,name)
);
```

## 唯一约束

```sql
-- 3.UNIQUE 唯一约束
-- 保证某个字段的值不能重复
-- 允许多个NULL值存在
-- 一张表中只能有一个主键，但是可以有多个unique
CREATE TABLE IF NOT EXISTS stu_7(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) UNIQUE,
email VARCHAR(50) UNIQUE
);
```

## 非空约束

```sql
-- 4.not null 非空约束
-- 指定字段不能为空
CREATE TABLE IF NOT EXISTS stu_8(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL
);
```

## 默认值

```sql
-- 5.default 默认值
-- 为字段添加默认值
CREATE TABLE IF NOT EXISTS stu_9(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL,
gender ENUM('男','女','保密') DEFAULT '保密',
-- 此处默认创建时间为当前时间
createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- 此处更新时间默认为当前时间，更新后会自动更改为更新时的时间
updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
); 
```

## 外键约束

### 主从关联

```sql
-- 外键约束
-- 如果一张表中的一个字段指向另一张表的主键，就将该字段叫做外键
-- 外键的数据类型必须和指向的主键一致
-- 定义外键的表称之为从表，被外键引用的表称之为主表

-- 案例 用户发表动态
-- 用户表(主表)
CREATE TABLE IF NOT EXISTS user(
	id TINYINT UNSIGNED PRIMARY KEY auto_increment,
	username VARCHAR(20) NOT NULL
);

DESC user;	
-- 动态表 (未添加外键约束)
CREATE TABLE IF NOT EXISTS dynamic(
	id TINYINT UNSIGNED PRIMARY KEY auto_increment,
	content VARCHAR(50) NOT NULL,
	user_id TINYINT UNSIGNED
);

desc dynamic;
-- 动态表 （从表）
CREATE TABLE IF NOT EXISTS dynamic_1(
	id TINYINT UNSIGNED PRIMARY KEY auto_increment,
	content VARCHAR(50) NOT NULL,
	user_id TINYINT UNSIGNED,
	FOREIGN KEY(user_id) REFERENCES user(id)
);
```

### 外键操作

```sql
-- 外键操作 action
-- 严格操作：restrict (默认)
-- 	 主表中不存在对应的数据，从表不允许添加
-- 	 从表中引用着数据，主表对应的数据不允许删除
-- 	 从表中引用着数据，主表对应的主键不允许修改
-- 置空操作：set null
-- 	 修改或删除主表id时，所偶跟它关联的从表字段都会被设置为null
-- 级联操作：cascade
-- 	 修改或删除主表id时，所有跟他关联的从表字段都会做同样的操作

CREATE TABLE IF NOT EXISTS dynamic_2(
	id TINYINT UNSIGNED PRIMARY KEY auto_increment,
	content VARCHAR(50) NOT NULL,
	user_id TINYINT UNSIGNED,
-- 	这里表示外键更新时采用级联操作，外键删除时采用置空操作
	FOREIGN KEY(user_id) REFERENCES user(id) ON UPDATE cascade ON DELETE set null
);

```

# 表中数据的增删改查

### 插入数据（增）

```sql
-- 1.插入数据（增）
-- 插入单条数据
INSERT INTO user (username) VALUES ('zhangsan');
INSERT INTO user (username,gender) VALUES ('zhangsan','男');
-- 插入多条数据
INSERT INTO user (username,gender) VALUES ('lisi','女'),('wangwu','男');
```

### 删除数据（删）

```sql
-- 2.删除数据（删）
DELETE FROM user; -- 删除所有数据
DELETE FROM user WHERE id = 2; -- 删除指定数据
```

### 更新数据（改）

```sql
-- 3.更新数据（改）
-- 更新所有数据
UPDATE user SET username = 'zhaoliu'; 
-- 更新指定数据
UPDATE user SET username = 'makabaka' ,gender = '女' WHERE id = 1;
```

# 表中数据的查询操作

```sql
-- 0.准备数据
CREATE TABLE IF NOT EXISTS student(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL,
age TINYINT UNSIGNED NOT NULL,
gender ENUM('男','女','保密') DEFAULT '保密',
class VARCHAR(20),
-- 默认创建时间为当前时间
createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- 默认更新时间为当前时间，更新后自动更改为更新时的时间
updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
); 

INSERT INTO student (name, age, gender, class) VALUES
('张三','20','男','一班'),
('李四','19','女','一班'),
('王五','21','女','二班'),
('赵六','18','女','二班'),
('孙七','19','男','三班'),
('周八','19','男','三班');
INSERT INTO student (name,age,gender) VALUES ('张三丰','15','男');
```



## 基础查询和where条件查询

### 基础查询

```sql
-- 1.基础查询
-- 查询指定的字段
SELECT id,name FROM student;
-- 查询所有数据
SELECT * FROM student;

-- 结果集
-- 	通过查询语句从数据表中查询出来的结果成为结果集
--  以表的形式呈现
--  结果集和查询的表不是同一张表，结果集来自于数据表
--  查询出来的表保存在内存中，数据表保存在硬盘上
```

### WHERE条件查询

```sql
-- 2.WHERE 条件查询
-- 	WHERE 支持：
--  >、>=、<、<=、=、!=
--  AND 与 、 OR 或 、NOT 非
--  IN(值1,值2)、BETWEEN...AND...
--  IS NULL、IS NOT NULL
--  LIKE 模糊查询
SELECT * FROM student WHERE id = 2;
SELECT * FROM student WHERE id > 2;

SELECT * FROM student WHERE id > 2 AND age > 20;
-- 范围限定在指定的值中
SELECT * FROM student WHERE id in(1,2,4);
-- 范围在一个范围里，包含边界
SELECT * FROM student WHERE id BETWEEN 1 AND 4;

SELECT * FROM student WHERE class IS NULL;
SELECT * FROM student WHERE class IS NOT NULL;

-- % 匹配任意个任意字符
-- _ 匹配一个任意字符
-- 查询以张开头，后面时任意个任意字符的数据
SELECT * FROM student WHERE name LIKE '张%';
-- 查询以张开头，后面是一个任意字符的数据
SELECT * FROM student WHERE name LIKE '张_';
-- 查询以三结尾，前面是任意个任意字符的数据
SELECT * FROM student WHERE name LIKE '%三';
-- 查询包含 三 的数据
SELECT * FROM student WHERE name LIKE '%三%';
-- 查询包含 三 ，且三后有一个任意字符的数据
SELECT * FROM student WHERE name LIKE '%三_';
```

## 排序

```sql
-- 3.排序
-- 默认升序
SELECT * FROM student ORDER BY age ASC;
-- 降序
SELECT * FROM student ORDER BY age DESC;
-- 先按年龄降序，再按id升序
SELECT * FROM student ORDER BY age DESC , id ASC;
```

### 分页查询

```sql
-- 4.分页查询 限制查询显示数量
-- LIMIT 一次查询的条数 OFFSET 偏移量
SELECT * FROM student LIMIT 2 OFFSET 0;
SELECT * FROM student LIMIT 2 OFFSET 2;
SELECT * FROM student LIMIT 2 OFFSET 4;
SELECT * FROM student LIMIT 2 OFFSET 6;
-- LIMIT 偏移量，一次查询额条数
SELECT * FROM student LIMIT 0,2;
```

## 聚合函数

```sql
-- 5.聚合函数
-- 对表中数据进行统计和计算，一般结合分组（GROUP BY）来使用，用于统计和计算分组数据
-- COUNT() 计算拆线呢到了多少条数据
-- SUM() 计算查询结果中所有指定字段的和
-- AVG() 计算查询加过中所有指定字段的平均值
-- MAX() 求查询结果中自定字段的最大值
-- MIN() 求查询结果中指定字段的最小值

SELECT * FROM student;
-- 得到一个值
SELECT COUNT(*) FROM student;
SELECT COUNT(id) FROM student;

SELECT SUM(age) FROM student;
SELECT MAX(age) FROM student;
SELECT MIN(age) FROM student;

-- 给查询出来的字段起别名
SELECT COUNT(*) AS total FROM student;
SELECT COUNT(*) total FROM student;
```

### 分组查询

```sql
-- 6.分组查询 GROUP BY
-- 再对数据进行分组的时候，SELECT后面必须是分组字段或者聚合函数
SELECT * FROM student;
-- SELECT * FROM student GROUP BY calss;
SELECT class FROM student GROUP BY class;
-- 先分组再统计 ：统计各班的平均年龄
SELECT class,AVG(age) FROM student GROUP BY class;
-- 起别名
SELECT gender,AVG(age) avgAge FROM student GROUP BY gender;
```

### HAVING条件查询

```sql
-- 7.HAVING条件查询
-- WHERE 是去数据表中插叙符合条件的数据返回结果集
-- HAVING 是去结果集中查询符合条件的数据，可以对分组之后查询到的结果进行筛选
SELECT class,AVG(age) avgAge FROM student GROUP BY class;
-- SELECT class,AVG(age) avgAge FROM student GROUP BY class WHERE AVG(age) <= 19; 
SELECT class,AVG(age) avgAge FROM student GROUP BY class HAVING AVG(age) <= 19; 
```

## 多表查询

**准备数据**

```sql
-- 0.准备数据
CREATE TABLE IF NOT EXISTS class(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL,
`desc` VARCHAR(25),
-- 默认创建时间为当前时间
createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- 默认更新时间为当前时间，更新后自动更改为更新时的时间
updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- 插入数据
INSERT INTO class (name,`desc`) VALUES 
('一班','火箭班'),
('二班','平行班'),
('三班','实验班'),
('四班','待定班');

0.准备数据 学生表（从表）
CREATE TABLE IF NOT EXISTS student(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL,
age TINYINT UNSIGNED NOT NULL,
gender ENUM('男','女','保密') DEFAULT '保密',
class_id TINYINT UNSIGNED,
-- 默认创建时间为当前时间
createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- 默认更新时间为当前时间，更新后自动更改为更新时的时间
updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
-- 当主表更新时更新，主表删除时置空
FOREIGN KEY(class_id) REFERENCES class(id) ON UPDATE cascade ON DELETE set null
);
INSERT INTO student (name,age,gender,class_id) VALUES
('张三','20','男','1'),
('李四','19','女','1'),
('王五','21','女','2'),
('赵六','18','女','2'),
('孙七','19','男','3'),
('周八','19','男','3');
INSERT INTO student (name,age,gender) VALUES ('张三丰',15,'男');
```



### 直接查询

```sql
-- 8.1 直接查询
SELECT * FROM student,class; -- 查询结果是两个表的乘积
-- 添加查询条件 查询学生的班级id与对应班级相同的结果
SELECT * FROM student,class WHERE student.class_id = class.id;
```

### 连接查询

```sql
-- 8.2 连接查询
-- 8.2.1 内连接查询（INSERT JOIN 或 JOIN）
SELECT * FROM student;
SELECT * FROM student JOIN class; -- 查询结果依然是两个表的乘积
-- 添加查询条件 所有字段
SELECT * FROM student JOIN class ON student.class_id = class.id;
-- 筛选字段
-- SELECT id,name FROM student JOIN class ON student.class_id = class.id;报错 
SELECT student.id,class.name FROM student JOIN class ON student.class_id = class.id;
-- 起别名，方便书写
SELECT stu.id,cls.name FROM student stu JOIN class cls ON stu.class_id = cls.id;

-- 8.2.2 外连接查询
-- 8.2.2.1 左外连接查询（LEFT OUTER JOIN 或 LEFT JOIN）
-- 左边的表是不看条件的，无论条件是否满足，都会返回左表的所有数据
-- 只有右边的表回看条件，对于右表，只有满足条件的，才会返回
SELECT * FROM student LEFT JOIN class on student.class_id = class.id;

-- 8.2.2.2 右外连接查询（RIGHT OUTER JOIN 或 RIGHT JOIN）
-- 右边的表是不看条件的，无论条件是否满足，都会返回右表的所有数据
-- 只有左边的表回看条件，对于左表，只有满足条件的，才会返回
SELECT * FROM student RIGHT JOIN class on student.class_id = class.id;
```

### UNION查询

```sql
-- 8.3 UNION 查询
-- 在纵向上将多张表的查询结果拼接起来返回
-- 必须保证多张表查询的字段个数一致
SELECT id,name FROM student UNION SELECT id,name FROM class;
```

### 子查询

```sql
-- 8.4 子查询
-- 8.4.1 将一个查询的结果作为另一个查询语句的条件来使用
-- 单个结果
SELECT class_id FROM student WHERE id = 3;
SELECT name FROM class WHERE id = (SELECT class_id FROM student WHERE id = 3);
-- 多个结果
SELECT class_id FROM student WHERE id >= 3;
SELECT name FROM class WHERE id in (SELECT class_id FROM student WHERE id >= 3);

-- 8.4.2 将一个查询语句结果作为另一个查询语句的表来使用
-- 必须给子查询表起别名
SELECT name FROM class WHERE id >= 2;
SELECT * FROM (SELECT name FROM class WHERE id >= 2) t;
```

### 一对一关系

```sql
-- 一对一查询
-- 需要外键，外键字段建在任意一方都可以
-- 学生和学生信息
-- 准备数据
CREATE TABLE IF NOT EXISTS student(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL,
age TINYINT UNSIGNED NOT NULL,
gender ENUM('男','女','保密') DEFAULT '保密'
);
 
 
CREATE TABLE IF NOT EXISTS student_info(
	id TINYINT UNSIGNED PRIMARY KEY auto_increment,
	phone VARCHAR(20),
	address VARCHAR(25),
	user_id TINYINT UNSIGNED,
	FOREIGN KEY(user_id) REFERENCES student(id) ON UPDATE cascade ON DELETE set null
);

-- 添加数据
INSERT INTO student (name,age,gender) VALUES 
('张三','20','男'),
('李四','19','女'),
('王五','21','女');

INSERT INTO student_info (phone ,address, user_id) VALUES ('15712345678','北京中关村','1'),('15187654321','武汉光谷','2');

SELECT * FROM student stu LEFT JOIN student_info sinfo on stu.id = sinfo.user_id;
```

### 一对多关系

```sql
-- 2. 一对多关系
-- 一个班级（一）可以有多个学生（多），一个学生只能属于一个班级
-- 班级表
CREATE TABLE IF NOT EXISTS class_2(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL,
`desc` VARCHAR(25)
);

-- 学生表
CREATE TABLE IF NOT EXISTS student_2(
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL,
class_id TINYINT UNSIGNED,
FOREIGN KEY(class_id) REFERENCES class_2(id) ON UPDATE cascade ON DELETE set null
);

-- 添加数据
INSERT INTO class_2 (name,`desc`) VALUES 
('一班','火箭班'),
('二班','平行班'),
('三班','实验班'),
('四班','待定班');

INSERT INTO student_2 (name,class_id) VALUES
('张三','1'),
('李四','1'),
('王五','2'),
('赵六','2'),
('孙七','3'),
('周八','3');

SELECT * FROM student_2 stu LEFT JOIN class_2 cls ON stu.class_id = cls.id;
SELECT * FROM student_2 stu RIGHT JOIN class_2 cls ON stu.class_id = cls.id;
```

### 多对多关系

```sql
-- 3.多对多关系
-- 一个学生可以学习多门课程，一个课程也可以被多个学生学习
CREATE TABLE IF NOT EXISTS student_3( -- 学生表
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL
);

-- 插入数据
INSERT INTO student_3 (name) VALUES
('张三'),
('李四'),
('王五'),
('赵六'),
('孙七'),
('周八');

CREATE TABLE IF NOT EXISTS course_3( -- 课程表
id TINYINT UNSIGNED PRIMARY KEY auto_increment,
name VARCHAR(20) NOT NULL
);

-- 添加课程表信息
INSERT INTO course_3 (name) VALUES
('民兵军事训练手册'),
('军地两用人才之友'),
('赤脚医生手册'),
('母猪的产后护理'),
('数理化');

CREATE TABLE IF NOT EXISTS stu_course(-- 学生课程关系表
	stu_id TINYINT UNSIGNED,
	course_id TINYINT UNSIGNED,
	-- 用联合主键的方式来确保学生的选课不重复
	PRIMARY KEY(stu_id,course_id),
	FOREIGN KEY(stu_id) REFERENCES student_3(id) ON UPDATE cascade ON DELETE cascade,
	FOREIGN KEY(course_id) REFERENCES course_3(id) ON UPDATE cascade ON DELETE cascade
);
-- 添加学生选课
INSERT INTO stu_course (stu_id,course_id) VALUES
('2','1'),
('2','2'),
('3','3'),
('1','4');

-- 查询所有学生的选课情况
-- 1.查询学生表和关系表的对应情况
SELECT * 
FROM student_3 
LEFT JOIN stu_course sc 
ON student_3.id = sc.stu_id;
-- 2.将第一步的结果左联课程表
-- 注释掉的是我自己想的代码
-- SELECT * 
-- FROM (
-- 	SELECT * 
-- 	FROM student_3 
-- 	LEFT JOIN stu_course sc 
-- 	ON student_3.id = sc.stu_id 
-- 	) result 
-- LEFT JOIN course_3 
-- ON result.course_id = course_3.id;
SELECT * 
FROM student_3 
LEFT JOIN stu_course sc 
ON student_3.id = sc.stu_id
LEFT JOIN course_3 c
ON sc.course_id = c.id;
-- 3.最后将学生id、名称和课程id、名称挑选出来
-- 注释掉的是我自己想的代码
-- SELECT result.id,result.name,course_3.id,course_3.name 
-- FROM (
-- 	SELECT * 
-- 	FROM student_3 
-- 	LEFT JOIN stu_course sc 
-- 	ON student_3.id = sc.stu_id 
-- 	) result 
-- LEFT JOIN course_3 
-- ON result.course_id = course_3.id;
SELECT stu.id,stu.name,c.id,c.name 
FROM student_3 stu
LEFT JOIN stu_course sc 
ON stu.id = sc.stu_id
LEFT JOIN course_3 c
ON sc.course_id = c.id;

-- 查询某个学生的选课情况
SELECT stu.id stuId,stu.name stuName,c.id cId,c.name cName 
FROM student_3 stu
LEFT JOIN stu_course sc 
ON stu.id = sc.stu_id
LEFT JOIN course_3 c
ON sc.course_id = c.id
WHERE stu.id = 1;

-- 查询哪些学生没有选课
SELECT *
FROM student_3 stu
LEFT JOIN stu_course sc 
ON stu.id = sc.stu_id
WHERE sc.course_id IS NULL;
```

# MySQL进阶

## MySQL预处理

1. **什么是预处理？**

   在执行SQL语句时，有时会遇到大量结构相同仅部分变量不同的语句，直接执行这些语句会消耗大量时间，而预处理则是为这种情况服务的语法——一种减轻服务器压力的技术。

2. **传统的SQL语句处理流程**

   - 发送SQL语句到MySQL服务器
   - MySQL服务器对SQL语句进行解析-编译-执行
   - 将执行结果返回给客户端

3. **传统流程的弊端**

   - 即使多次传递的语句大部分容都是相同的，每次还是要重新传递
   - 即使多次传递的语句大部分容都是相同的，每次执行之前还是要先解析、编译后才能执行

4. **预处理的流程**

   - 在客户端准备预处理的SQL语句
   - 发送预处理SQL语句到MySQL服务器
   - MySQL服务器对SQL语句进行解析-编译，但不执行
   - 在客户端准备相关数据
   - MySQL服务器对数据和预处理的SQL语句一起编译，然后执行该语句
   - 服务器将执行结果返回给客户端

5. **预处理的优点**

   - 只对SQL语句进行了一次解析
   - 重复内容大大减少，网络传输更快

### 如何使用预处理

```sql
-- 在客户端准备预处理的SQL语句(语句中ppstmt为预处理语句的名称)
PREPARE ppstmt FROM 'SELECT * FROM student WHERE id = ?;';
-- 发送预处理SQL语句到MySQL服务器
-- MySQL服务器对SQL语句进行解析-编译，但不执行
-- 在客户端准备相关数据
SET @id = 1;
-- MySQL服务器对数据和预处理的SQL语句一起编译，然后执行该语句
EXECUTE ppstmt USING @id;
-- 服务器将执行结果返回给客户端
```





PS：要仔细检查！写SQL语句报错大多数时候就是语法上的错误！（小白时期？）

​		查询时的报错，大多是数据库中数据的存储问题，语法没问题还报错，最好去看下数据库里的数据。
