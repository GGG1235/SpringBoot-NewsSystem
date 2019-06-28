# SpringBoot-NewsSystem
大三上JavaWeb作业-新闻发布系统

java 10.0.2 2018-07-17

mysql 5.7.22

Apache Maven 3.6.0

--------------------
**ROLE_USER:普通用户,ROLE_USER_S:二级用户,ROLE_ADMIN:管理员**

普通用户:只能浏览和改自己的密码

二级用户:可以发新闻,相当于记者(需要审核)

管理员:可以增删改查新闻,用户,更改用户权限最高只能改为二级用户



SpringBoot
===
使用SpringSecurity配置来进行权限控制
```
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)

```
数据库使用Jpa来操纵
```
public interface FileRepository extends JpaRepository<File,Long>{
}
```
前端使用了Bootstrap框架,富文本编辑器使用百度的ueditor,使用模版引擎thymeleaf。

springboot+ueditor参考了
[参考文章](https://blog.csdn.net/qq_33745799/article/details/70031641)

部分截图:
===
![新闻首页1](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/img/1.png)
![用户信息修改](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/img/2.png)
![新闻首页2](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/img/3.png)
![后台管理系统](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/img/4.png)
![文章管理](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/img/5.png)
