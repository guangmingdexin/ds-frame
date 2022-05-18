package com.guang.provider.service;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.auth.AccountAo;
import com.guang.provider.ao.auth.TokenAo;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.bo.UserBo;
import com.guang.provider.vo.LoginVo;

/**
 * @author guangyong.deng
 * @date 2021-12-29 10:06
 */
public interface IAuthService {


    /**
     * 校验 token 是否合法
     * @param tokenAo 前端 token 对象
     * @return
     */
    Long auth(TokenAo tokenAo);


    /**
     *
     * 登录
     *
     * @param accountAo 登录对象
     * @return 用户信息
     */
    LoginVo login(AccountAo accountAo);


    /**
     * 注销登陆
     */
    void loginOut();
}
