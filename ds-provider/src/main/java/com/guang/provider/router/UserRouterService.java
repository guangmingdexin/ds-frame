package com.guang.provider.router;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.vo.UserVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

/**
 * @author guangyong.deng
 * @date 2022-02-15 9:34
 */
public interface UserRouterService {


    /**
     *
     * 根据 id 获取用户信息
     *
     * @param query 用户
     * @return BO
     */
    @PostMapping("/ds-user/user")
    ResponseVO<UserVo> getUser(@Validated @RequestBody UserQueryAo query);


    ResponseVO<UserVo> addUser();
}
