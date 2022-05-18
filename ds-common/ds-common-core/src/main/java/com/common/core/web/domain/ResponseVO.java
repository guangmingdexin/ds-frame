package com.common.core.web.domain;


import com.common.core.exception.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回给前端的封装对象
 *
 * @author guangyong.deng
 * @date 2021-10-26 17:03
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> implements Serializable {
    /**
     * 状态编码
     */
    private String code;
    /**
     * 返回数据
     */
    private T result;
    /**
     * 返回消息
     */
    private String msg;

    public static <T> ResponseVO success(T data) {
        return ResponseVO.<T>builder()
                .code(ResultCode.SUCCESS.getCode())
                .msg(ResultCode.SUCCESS.getMessage())
                .result(data)
                .build();
    }

    public static <T> ResponseVO error(ResultCode resultCode) {
        return ResponseVO.<T>builder()
                .code(resultCode.getCode())
                .msg(resultCode.getMessage())
                .build();
    }

    public static <T> ResponseVO fail(String msg) {
        return ResponseVO.<T>builder()
                .code(ResultCode.FAIL.getCode())
                .msg(msg)
                .build();
    }


    public static <T> ResponseVO fail(ResultCode code) {

        return ResponseVO.<T>builder()
                .code(code.getCode())
                .msg(code.getMessage())
                .build();
    }

    public static <T> ResponseVO fail() {
        return fail(ResultCode.FAIL.getMessage());
    }
}
