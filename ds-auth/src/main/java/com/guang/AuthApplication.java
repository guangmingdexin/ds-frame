package com.guang;

import com.common.core.web.bean.BeanUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author guangmingdexin
 * @date 2022/4/21
 */
@SpringBootApplication(scanBasePackages = {"com.guang"})
@EnableEurekaClient
@MapperScan("com.guang.persistence.mapper")
@EnableFeignClients(basePackages = {"com.guang.provider.feign"})
public class AuthApplication {

    public static void main(String[] args) {
        BeanUtil.applicationContext = SpringApplication.run(AuthApplication.class, args);
    }
}
