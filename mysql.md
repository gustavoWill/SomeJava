### 数据库操作：
- 显示所有数据库：
```sql
show databases;
```
- 创建数据库：
```sql
create database database_name;
```
- 切换数据库：
```sql
use database_name;
```
- 查看创建好的数据库的定义：
```sql
show create database database_name\G;
```
- 删除数据库：执行删除数据库，该数据库中的数据一同被删除
```sql
drop database database_name;
```
### 表的操作
- 主键约束：
```sql
create table S(
	SID int primary key,
	SName char(25),
	SSex char(1),
	SProId int,
	SAge int
);
```
- 多字段联合主键：
```sql
create table S(
	SID int,
	SName char(25),
	SSex char(1),
	SProId int,
	SAge int,
	PRIMARY KEY(SID,SName)
);
```
- 外键约束：
```sql
create table department(
	id int(11) primary key,
	name varchar(22) not null,
	location varchar(50)
);
create table employee(
	id int(11) primary key,
	name varchar(25) unique,
	deptId int(11),
	salary FLOAT,
	CONSTRAINT fk_emp_dept FOREIGN KEY(deptId) REFERENCES department(id)
);
```

- 查看表结构：
```sql
 	desc table_name
```
- 查看表的详细结构：
```sql
	show create table table_name\G;
```
- 修改表的存储类型
```sql
	alter table table_name engine=myisam;
```
- auto_increment
```sql
	create table student(
		id int primary key auto_increment,
		name varchar(25)
	) auto_increment=100;
```
- SELECT 和LIKE百分号通配符%匹配字符
-- 查询authors 以B开头的book表中的bookid,authors,info
```sql
	select bookid, authors,info from book where authors LIKE 'B%';
```

 ### 问题
 - mysql主从复制原理及流程
 - primary key 和 UNIQUE区别
 - 一张表,里面有ID自增主键,当insert了17条记录之后,删除了第15,16,17条记录,再把Mysql重启,再insert一条记录,这条记录的ID是18还是15 ?
- 请简述项目中优化sql语句执行效率的方法
- 为什么平衡二叉树不适合做mysql的索引
- 阿里手册里的mysql每项都要了解

### 索引
```sql
	create table book(
	bookid int not null,
	bookname varchar(255) not null,
	authors varchar(255) not null,
	info varchar(255) default null,
	comment varchar(255) default null,
	year_publication YEAR not null,
	INDEX(year_publication)
	);
```
使用show create table book\G; 可以查看已经在year_publication字段加上索引
使用explain语句查看索引是否正在使用，explain select * from book where year_publication=1990 \G;
在已经存在的表创建索引：
```sql
	alter table book add index BkNameIdx(bookname(30));
```
- 可以使用 show index from book \G; 查看表中的索引
- 在book表的bookId字段建立名称为UniquedIdx的唯一索引
```sql
	alter table book add unique index uniqidIdx(bookid);
```
- 在book表的authors和info字段建立组合索引
```sql
	alter table book add unique index BkAuAndInfoIdx(authors(20),info(50));
	create index BkAuAndInfoIdx ON book(authors(20),info(50));
```
- 查看表的索引： show index from book \G;


### 总结
- 外键约束不能夸引擎使用
- 一个表只能有一个字段使用auto_increment,且该字段必须为主键的一部分
- 可以通过建表时增加auto_increment=100或alter table student auto_increment=100的方式修改默认初始值，但是Innodb引擎如果mysql服务端重启之后auto_increment的默认初始值会被刷新，而MyIsam引擎是将auto_increment的默认初始值存在文件中，即使服务器重启也不会改变
