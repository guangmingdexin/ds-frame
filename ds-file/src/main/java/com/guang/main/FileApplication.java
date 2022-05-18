package com.guang.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@SpringBootApplication(scanBasePackages = {"com.guang"})
@EnableEurekaClient
@MapperScan("com.guang.persistence.mapper")
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
