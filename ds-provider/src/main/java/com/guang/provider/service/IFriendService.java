package com.guang.provider.service;

import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.vo.FriendVo;

/**
 * @author guangyong.deng
 * @date 2022-02-24 15:29
 */
public interface IFriendService {


    FriendVo getFriends(UserQueryAo query);
}
