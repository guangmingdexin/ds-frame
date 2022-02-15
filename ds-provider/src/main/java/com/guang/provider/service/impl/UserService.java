package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.guang.persistence.domain.SysUser;
import com.guang.persistence.service.SysUserService;
import com.guang.provider.bo.UserBo;
import com.guang.provider.mapstruct.UserConvert;
import com.guang.provider.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:20
 */
@Service("userService")
public class UserService implements IUserService {


    @Autowired
    SysUserService sysUserService;

    @Override
    public UserBo getUser(String uId) {
        LambdaQueryWrapper<SysUser> query = Wrappers.lambdaQuery();
        query.eq(SysUser::getSysId, uId);

        SysUser user = sysUserService.getOne(query);

        return UserConvert.INSTANCE.doConvertBo(user);
    }
}
