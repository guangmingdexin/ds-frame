package com.guang.provider.router;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.auth.AccountAo;
import com.guang.provider.ao.auth.ChangePasswordAo;
import com.guang.provider.ao.auth.RefreshTokenAo;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.bo.UserBo;
import com.guang.provider.vo.LoginVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * 用户登录 api
 * @author guangyong.deng
 * @date 2021-12-27 17:31
 */
public interface AuthRouteService {

    /**
     * 登录
     * @param accountAo 登陆对象
     * @return
     */
    @PostMapping(value = "/auth/login")
    ResponseVO<LoginVo> login(@RequestBody @Validated AccountAo accountAo);


    /**
     *
     * 注销登陆（暂时后端不需要作什么）
     *
     * @return boolean
     */
    @PostMapping("/auth/logout")
    ResponseVO<Boolean> loginOut();

    /**
     *
     *
     *
     * @param step2Code
     * @return
     */
    @PostMapping("/auth/2step-code")
    ResponseVO<Boolean> get2Step(@RequestBody Object step2Code);

    /**
     * 修改密码
     * @param changePasswordAo
     * @return
     */
    @PutMapping(value = "/auth/pwd")
    ResponseVO<Boolean> changePwd(@RequestBody ChangePasswordAo changePasswordAo);

    /**
     * 认证
     * @return
     */
    @PostMapping(value = "api/auth")
    ResponseVO<Boolean> auth();



    /**
     * 刷新token(AccessToken和RefreshToken)
     * @param refreshToken
     * @return
     */
    @PutMapping(value = "/auth/refresh")
    ResponseVO<LoginVo> refresh(@RequestBody RefreshTokenAo refreshToken);
}
