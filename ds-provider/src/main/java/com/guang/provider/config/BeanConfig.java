package com.guang.provider.config;


import cn.dev33.satoken.stp.StpInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author guangyong.deng
 * @date 2021-11-25 17:14
 */
@Configuration
public class BeanConfig {

    @Bean
    public StpInterface dsInterface() {

        return new StpInterface() {

            @Override
            public List<String> getPermissionList(Object o, String s) {
                // 查询数据库
                System.out.println("获取所有权限！");
                return null;
            }

            @Override
            public List<String> getRoleList(Object o, String s) {
                System.out.println("获取所有角色！");
                return null;
            }
        };
    }
}
