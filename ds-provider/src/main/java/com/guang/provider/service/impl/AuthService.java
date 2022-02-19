package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.guang.persistence.domain.SysUser;
import com.guang.persistence.service.SysUserService;
import com.guang.provider.ao.auth.AccountAo;
import com.guang.provider.ao.auth.TokenAo;
import com.guang.provider.service.IAuthService;
import com.guang.provider.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guangyong.deng
 * @date 2022-02-15 10:03
 */
@Service("authService")
public class AuthService implements IAuthService {

    @Autowired
    SysUserService sysUserService;

    @Override
    public Long auth(TokenAo tokenAo) {
        return null;
    }

    @Override
    public LoginVo login(AccountAo accountAo) {
        // 1.查询数据库，获取
        LambdaQueryWrapper<SysUser> query = Wrappers.lambdaQuery();
        query.eq(SysUser::getSysName, accountAo.getUsername())
                .eq(SysUser::getSysPwd, accountAo.getPwd());

        SysUser user = sysUserService.getOne(query);

        // 调用 ds-token 框架，生成一个 token 并返回

        LoginVo loginVo = new LoginVo()
                .setToken("123")
                .setUid(user.getSysId())
                .setRtoken("123");



        return loginVo;
    }
}
