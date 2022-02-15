package com.guang.provider.controller;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.mapstruct.UserConvert;
import com.guang.provider.router.UserRouterService;
import com.guang.provider.service.impl.UserService;
import com.guang.provider.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:19
 */
@RestController
@RequestMapping("/majiang")
public class UserController implements UserRouterService {

    @Autowired
    UserService userService;

    @Override
    public ResponseVO<UserVo> getUser(String uId) {
        UserVo userVo = UserConvert.INSTANCE.boConvertVo(userService.getUser(uId));
        return ResponseVO.success(userVo);
    }
}
