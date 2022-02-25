package com.guang.provider.controller;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.router.FriendRouteService;
import com.guang.provider.service.impl.FriendService;
import com.guang.provider.vo.FriendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangyong.deng
 * @date 2022-02-24 15:12
 */
@RestController
public class FriendController implements FriendRouteService {

    @Autowired
    FriendService friendService;

    @Override
    public ResponseVO<FriendVo> getFriends(UserQueryAo query) {
        FriendVo friends = friendService.getFriends(query);
        if(friends != null) {
            ResponseVO success = ResponseVO.success(friends);
            System.out.println(success);
            return success;
        }
        return ResponseVO.fail("获取好友失败");
    }

    @Override
    public ResponseVO<Boolean> addFriend(UserQueryAo query) {
        return null;
    }
}
