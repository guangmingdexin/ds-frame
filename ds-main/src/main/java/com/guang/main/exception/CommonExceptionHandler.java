package com.guang.main.exception;

import com.common.core.web.domain.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author guangyong.deng
 * @date 2022-02-16 13:39
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {


    /**
     * 其他异常捕捉
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseVO<String> exceptionHandler(Exception e) {
        log.error("== 未捕获的异常", e);
        return ResponseVO.fail(e.getMessage());
    }
}
