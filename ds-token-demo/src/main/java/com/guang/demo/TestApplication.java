package com.guang.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guangyong.deng
 * @date 2021-11-25 17:28
 */
@SpringBootApplication
@MapperScan(value = "com.guang.demo.mapper.base.*")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);

    }
}
