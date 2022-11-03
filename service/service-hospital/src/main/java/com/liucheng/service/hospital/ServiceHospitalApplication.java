package com.liucheng.service.hospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 刘呈
 * @createTime 2022/11/2 20:07
 * @description
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.liucheng")
public class ServiceHospitalApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospitalApplication.class,args);
    }
}
