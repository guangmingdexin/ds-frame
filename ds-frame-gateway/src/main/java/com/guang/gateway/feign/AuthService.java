package com.guang.gateway.feign;

import com.common.core.web.domain.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * 调用内部服务
 *
 * @author guangyong.deng
 * @date 2022-02-16 10:56
 */
@FeignClient(name = "auth-service")
@Service
public interface AuthService {

    /**
     * 统一鉴权
     *
     *
     * @return 是否能够访问
     */
    @PostMapping(value = "/api/auth")
    ResponseVO<Boolean> auth();


}
