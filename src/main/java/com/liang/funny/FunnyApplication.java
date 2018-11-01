package com.liang.funny;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liang.funny.dao")
public class FunnyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunnyApplication.class, args);
    }
}
