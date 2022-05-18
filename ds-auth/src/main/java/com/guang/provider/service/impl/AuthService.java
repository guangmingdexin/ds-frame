package com.guang.provider.service.impl;

import cn.dev33.satoken.stp.StpUtil;
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
import org.springframework.util.DigestUtils;

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
        query.eq(SysUser::getSysAccount, accountAo.getUsername())
                .eq(SysUser::getDeleted, 0);

        // 对 account 添加了唯一索引，可以确保账户只有一个
        SysUser user = sysUserService.getOne(query);

        // 调用 ds-token 框架，生成一个 token 并返回
        // 判断用户账户的状态（暂时先不用）
        // 开启了sa-token框架的自动续签，不用 rtoken

        if(user != null && DigestUtils.md5DigestAsHex(user.getSysPwd().getBytes()).equals(accountAo.getPassword())) {
            StpUtil.login(user.getSysId());
            return new LoginVo()
                    .setId(user.getSysId())
                    .setToken(StpUtil.getTokenInfo().tokenValue)
                    .setId(user.getSysId());

        }

        return null;
    }

    @Override
    public void loginOut() {
        StpUtil.logout();
    }
}
