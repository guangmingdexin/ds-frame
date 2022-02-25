package com.guang.provider.router;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.vo.FriendVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author guangyong.deng
 * @date 2022-02-24 15:08
 */
public interface FriendRouteService {

    /**
     *
     * 获取好友列表
     *
     * @param query 封装对象
     * @return
     */
    @PostMapping("/ds-friend/friends")
    ResponseVO<FriendVo> getFriends(@Validated @RequestBody UserQueryAo query);


    /**
     *
     * 添加好友
     *
     * @param query 封装对象
     * @return 是否成功
     */
    ResponseVO<Boolean> addFriend(@Validated @RequestBody UserQueryAo query);
}
