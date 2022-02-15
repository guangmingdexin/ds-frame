package com.guang.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author guangyong.deng
 * @date 2022-02-14 17:13
 */
// 需要设置扫描路径，否则无法扫描到依赖包的路径
@SpringBootApplication(scanBasePackages = "com.guang")
public class DsGameApplication {


    public static void main(String[] args) {

        try {
            SpringApplication.run(DsGameApplication.class, args);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
