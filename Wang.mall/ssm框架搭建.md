#SSM框架搭建

##### 1.java版本jdk1.8.0_131
##### TomCat 8.5
##### Maven 3.3.9

###### Dynamic Web Module 3.1 支持Java 1.7 or newer
 
 ```
 1. 复制该项目路径
 2. cd 到该项目目录下
 3. cd .settings/
 4. 找到配置文件org.eclipse.wst.common.project.facet.core.xml
 5. vi org.eclipse.wst.common.project.facet.core.xml
 6. 修改<installed facet="jst.web" version="2.3"/>为<installed facet="jst.web" version="3.1"/> 并保存
 ```
 
 ##### 添加pom.xml依赖库，有注解说明
 
 ##### 构建项目结构
 
 ```
 1. src/main/java
 2. src/main/resouices
 3. src/test/java
 4. src/test/resouices
 5. src/main/resouices/spring
 6. src/main/resouices/mapper
 ```
 
 ##### 添加src/main/resouices配置文件
 
 ```
 1. jdbc.properties  (数据库账号密码加密)
 2. logback.xml  (日志，这块我还没搞的很懂)
 3. mybatis-config.xml
 4. redis.properties
 
 ```
  ##### 添加src/main/resouices/spring配置文件
  
  ```
  1. spring-dao.xml
  2. spring-redis.xml
  3. spring-service.xml
  4. spring-web.xml (controller)
  ```
 
 配置完毕，测试dao ，service，controller测试redis功能成功，完毕
 