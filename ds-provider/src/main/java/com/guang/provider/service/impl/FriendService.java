package com.guang.provider.service.impl;

import com.guang.persistence.domain.SysFriend;
import com.guang.persistence.service.SysFriendService;
import com.guang.persistence.service.SysUserService;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.mapstruct.UserConvert;
import com.guang.provider.service.IFriendService;
import com.guang.provider.vo.FriendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guangyong.deng
 * @date 2022-02-24 15:35
 */
@Service
public class FriendService implements IFriendService {


    @Autowired
    SysFriendService sysFriendService;

    @Autowired
    SysUserService sysUserService;

    @Override
    public FriendVo getFriends(UserQueryAo queryAo) {
        // 1.获取所有 好友 id
        // 2.根据 id 获取所有好友信息
       List<SysFriend> friends = sysFriendService.getFriends(queryAo.getUserId());

        return UserConvert.INSTANCE.dtoConvertVo(queryAo.getUserId(), friends);
    }
}
