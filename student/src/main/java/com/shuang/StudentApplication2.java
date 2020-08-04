package com.shuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@MapperScan("com.shuang.repository")
@SpringBootApplication
public class StudentApplication2 {

    public static void main(String[] args){

        SpringApplication.run(StudentApplication2.class,args);
    }

}
