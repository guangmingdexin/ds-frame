package com.guang.provider.config;


import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
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


    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

}
