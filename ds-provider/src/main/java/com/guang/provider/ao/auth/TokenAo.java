package com.guang.provider.ao.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guangyong.deng
 * @date 2022-02-15 9:51
 */
@Setter
@Getter
public class TokenAo {

    /**
     * token
     */
    private String token;

    /**
     * 用户 id
     */
    private String uId;

}
