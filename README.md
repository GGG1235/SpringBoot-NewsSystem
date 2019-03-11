# SpringBoot-NewsSystem
大三上JavaWeb作业-新闻发布系统

Java环境 java 10.0.2 2018-07-17
mysql 5.7.22

###SpringBoot
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
