package com.shuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@MapperScan("com.shuang.repository")
@SpringBootApplication
public class StudentApplication {

        public static void main(String[] args){

            SpringApplication.run(StudentApplication.class,args);
        }

}
