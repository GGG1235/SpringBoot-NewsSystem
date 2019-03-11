# SpringBoot-NewsSystem
大三上JavaWeb作业-新闻发布系统

Java环境 java 10.0.2 2018-07-17
mysql 5.7.22

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
[1]: https://blog.csdn.net/qq_33745799/article/details/70031641 "参考文章"

部分截图:
===
![新闻首页1](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/1.png)
![用户信息修改](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/2.png)
![新闻首页2](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/3.png)
![后台管理系统](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/4.png)
![文章管理](https://github.com/GGG1235/SpringBoot-NewsSystem/blob/master/5.png)
