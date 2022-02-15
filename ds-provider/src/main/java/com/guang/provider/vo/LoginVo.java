package com.guang.provider.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author guangyong.deng
 * @date 2022-02-15 9:49
 */
@Getter
@Setter
@Accessors(chain = true)
public class LoginVo {

    /**
     * AccessToken
     */
    String token;
    /**
     * RefreshToken
     */
    String rtoken;

    /**
     * 用户中心用户ID
     */
    String uid;
}
