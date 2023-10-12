package org.example.blog;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: fs
 * @date: 2023/10/11 22:33
 * @Description: everything is ok
 */
@SpringBootApplication
@ComponentScan("org.example")
@MapperScan("org.example.framework.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
