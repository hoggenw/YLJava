#添加依赖包及plugin



```
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<optional>true</optional>

		</dependency>

```


针对devtools的可以指定目录或者排除目录来进行热部署


```
#添加那个目录的文件需要restart
spring.devtools.restart.additional-paths=src/main/java
#排除那个目录的文件不需要restart
spring.devtools.restart.exclude=static/**,public/**

```


#idea 设置



![设置1](/Users/wangliugen/Desktop/SelfProject/YLJava/Java学习文档资料/images/20181113155800544.png)


commond + option + shift + /

![设置2](/Users/wangliugen/Desktop/SelfProject/YLJava/Java学习文档资料/images/设置2.png)