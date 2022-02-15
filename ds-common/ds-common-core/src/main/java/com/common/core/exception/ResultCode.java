package com.common.core.exception;

import lombok.Getter;

/**
 *
 *
 * 系统错误：100001--100999
 * 用户模块：101001--101999
 *
 * @author guangyong.deng
 */
@Getter
public enum ResultCode {

    /**
     * 默认提供
     */
    SUCCESS("200","请求成功"),
    FAIL("500", "服务器内部错误"),

    /**
     * 公共异常
     */
    PARAM_ERROR("100001", "参数不合法"),

    /**
     * 用户模块
     */
    USER_ERROR("101000", "用户模块发生错误"),
    ;
    private String code;

    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
