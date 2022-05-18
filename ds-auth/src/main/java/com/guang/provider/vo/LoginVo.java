package com.guang.provider.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

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
     * 用户中心用户ID
     */
    String id;


    /**
     * 玩家状态
     */
    String status;


}
