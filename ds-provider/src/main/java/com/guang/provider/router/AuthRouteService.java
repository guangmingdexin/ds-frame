package com.guang.provider.router;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.auth.AccountAo;
import com.guang.provider.ao.auth.ChangePasswordAo;
import com.guang.provider.ao.auth.RefreshTokenAo;
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
     * @param accountAo
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/ds-auth/login")
    ResponseVO<LoginVo> login(@RequestBody @Validated AccountAo accountAo);

    /**
     * 修改密码
     * @param changePasswordAo
     * @return
     */
    @PutMapping(value = "/ds-auth/pwd")
    ResponseVO<Boolean> changePwd(@RequestBody ChangePasswordAo changePasswordAo);

    /**
     * 认证
     * @param token AccessToken
     * @return
     */
//    @PostMapping(value = "/pt-unified-auth/auth")
//    ResponseVO<Long> auth(@RequestBody TokenVo token);

    /**
     * 刷新token(AccessToken和RefreshToken)
     * @param refreshToken
     * @return
     */
    @PutMapping(value = "/ds-auth/refresh")
    ResponseVO<LoginVo> refresh(@RequestBody RefreshTokenAo refreshToken);
}
