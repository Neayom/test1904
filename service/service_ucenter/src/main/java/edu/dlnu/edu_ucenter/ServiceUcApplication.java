package edu.dlnu.edu_ucenter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@ComponentScan({"edu.dlnu"})
@SpringBootApplication
@MapperScan("edu.dlnu.edu_ucenter.mapper")
@CrossOrigin
public class ServiceUcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }
}