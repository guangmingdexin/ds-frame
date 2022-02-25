package com.guang.provider.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.auth.AccountAo;
import com.guang.provider.ao.auth.ChangePasswordAo;
import com.guang.provider.ao.auth.RefreshTokenAo;
import com.guang.provider.router.AuthRouteService;
import com.guang.provider.service.impl.AuthService;
import com.guang.provider.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author guangyong.deng
 * @date 2022-02-15 10:19
 */
@RestController
public class AuthController implements AuthRouteService {

    @Autowired
    AuthService authService;

    @Override
    public ResponseVO<LoginVo> login(AccountAo accountAo) {

        System.out.println("登录-------");

        LoginVo loginVo = authService.login(accountAo);

        if(loginVo == null) {
            System.out.println("登录失败！----------------");
            return ResponseVO.fail("用户名或者密码错误");
        }
        return ResponseVO.success(loginVo);
    }

    @Override
    public ResponseVO<Boolean> changePwd(ChangePasswordAo changePasswordAo) {
        return null;
    }

    @Override
    public ResponseVO<Boolean> auth() {
        // 判断是否有权限能够请求，鉴权的过程已经在拦截器中实现了，所以，走到这里返回结果就行了
        return ResponseVO.success(true);
    }

    @Override
    public ResponseVO<LoginVo> refresh(RefreshTokenAo refreshToken) {
        return null;
    }
}
