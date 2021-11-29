package com.guang.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author guangyong.deng
 * @date 2021-11-25 16:20
 */
//@Component
public class DsGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("调用全局过滤器！");
        // 1.获取请求头中的 token
        // 2.鉴权
        // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
//        DsRouter.match("/**", "/user/doLogin", r -> DsUtil.checkLogin());
//
//        // 权限认证 -- 不同模块, 校验不同权限
//        DsRouter.match("/user/**", r -> DsUtil.checkPermission("user"));
//        DsRouter.match("/admin/**", r -> DsUtil.checkPermission("admin"));
//        DsRouter.match("/goods/**", r -> DsUtil.checkPermission("goods"));
//        DsRouter.match("/orders/**", r -> DsUtil.checkPermission("orders"));

        return chain.filter(exchange);
    }
}
