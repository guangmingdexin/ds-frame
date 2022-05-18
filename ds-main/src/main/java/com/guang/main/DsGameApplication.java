package com.guang.main;

import com.common.core.web.bean.BeanUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author guangyong.deng
 * @date 2022-02-14 17:13
 */
// 需要设置扫描路径，否则无法扫描到依赖包的路径
@SpringBootApplication(scanBasePackages = {"com.guang"})
@EnableEurekaClient
@MapperScan("com.guang.persistence.mapper")
@EnableFeignClients(basePackages = {"com.guang.provider.feign"})
@EnableTransactionManagement
public class DsGameApplication {


    public static void main(String[] args) {
        BeanUtil.applicationContext = SpringApplication.run(DsGameApplication.class, args);

    }
}
