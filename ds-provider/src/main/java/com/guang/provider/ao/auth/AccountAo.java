package com.guang.provider.ao.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guangyong.deng
 * @date 2022-02-15 9:39
 */
@Getter
@Setter
public class AccountAo {

    /**
     * 账号
     */
    String username;
    /**
     * 密码(跟前端交互时不能使用明文)
     */
    String pwd;

}
