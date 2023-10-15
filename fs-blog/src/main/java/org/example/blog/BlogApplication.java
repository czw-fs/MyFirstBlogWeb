package org.example.blog;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: fs
 * @date: 2023/10/11 22:33
 * @Description: everything is ok
 */
@SpringBootApplication
@ComponentScan("org.example")
@MapperScan("org.example.framework.mapper")
@EnableScheduling
@EnableSwagger2
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
