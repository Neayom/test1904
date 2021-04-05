package edu.dlnu.gatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //使用nacos的注册
public class gatewayapplication {
    public static void main(String[] args) {
        SpringApplication.run(gatewayapplication.class,args);
    }
}
