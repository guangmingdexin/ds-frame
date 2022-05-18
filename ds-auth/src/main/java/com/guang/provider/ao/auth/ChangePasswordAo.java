package com.guang.provider.ao.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guangyong.deng
 * @date 2022-02-15 9:45
 */
@Getter
@Setter
public class ChangePasswordAo {

    /**
     * 账号
     */
    String account;
    /**
     * 新密码
     */
    String newPwd;
    /**
     * 现密码
     */
    String oldPwd;
    /**
     * 验证码
     */
    String vfCode;
}
