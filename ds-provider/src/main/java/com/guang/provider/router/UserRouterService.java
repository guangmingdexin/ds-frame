package com.guang.provider.router;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author guangyong.deng
 * @date 2022-02-15 9:34
 */
public interface UserRouterService {


    /**
     *
     * 根据 id 获取用户信息
     *
     * @param uId 用户 Id
     * @return BO
     */
    @PostMapping("/ds-user/user")
    ResponseVO<UserVo> getUser(String uId);
}
