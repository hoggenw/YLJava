package hello;


import hello.storage.StorageProperties;
import hello.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
////开启属性注入。
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    /**
     * 我们在开发中可能会有这样的情景。需要在容器启动的时候执行一些内容。比如读取配置文件，数据库连接之类的
     * 。SpringBoot给我们提供了两个接口来帮助我们实现这种需求。这两个接口分别为CommandLineRunner和ApplicationRunner。
     * 他们的执行时机为容器启动完成的时候。

     这两个接口中有一个run方法，我们只需要实现这个方法即可。
     这两个接口的不同之处在于：ApplicationRunner中run方法的参数为ApplicationArguments，
     而CommandLineRunner接口中run方法的参数为String数组
     * */
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
