package com.guang.provider.filter;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author guangyong.deng
 * @date 2022-02-16 9:47
 */
@Configuration
public class AuthFilter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义认证规则
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler)->{
            // 根据路由划分模块，不同模块不同鉴权
            // 根据路由划分模块，不同模块不同鉴权
            // 登录验证
            SaRouter.match("/**", r -> StpUtil.checkLogin());
            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));

        })).addPathPatterns("/**")
        .excludePathPatterns("/ds-auth/login");
    }
}
