package com.guang.gateway.filter;

import com.common.core.web.domain.Constant;
import com.common.core.web.domain.ResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guang.gateway.feign.AuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author guangyong.deng
 * @date 2021-11-25 16:20
 */
@Component
public class DsGlobalFilter implements GlobalFilter {

    @Autowired
    AuthService authService;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("调用全局过滤器！");
        // 1.获取请求头中的 token
        // 2.鉴权
        // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
        // 1.调用远程鉴权服务
        // 2.注册到  eurake 中心

        // 1.首先校验请求是否完整，是否有 token，uId
        HttpHeaders headers = exchange.getRequest().getHeaders();

        System.out.println(exchange.getRequest().getURI());

        if(headers.containsKey(Constant.TOKEN_NAME) ) {

            ResponseVO<Boolean> auth = authService.auth();

            if(auth.getResult()) {
                return chain.filter(exchange);
            }
        }

        ServerHttpResponse response = exchange.getResponse();
        // 设置返回消息
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = response.bufferFactory().wrap(new ObjectMapper().writeValueAsBytes(ResponseVO.fail("鉴权失败")));
        return response.writeWith(Mono.just(buffer));

//        return response.setComplete();

    }
}
