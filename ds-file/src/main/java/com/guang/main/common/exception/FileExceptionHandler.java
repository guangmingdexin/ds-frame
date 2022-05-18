package com.guang.main.common.exception;

import com.common.core.web.domain.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author guangmingdexin
 * @date 2022/4/23
 */
@RestControllerAdvice
@CrossOrigin("*")
@Slf4j
public class FileExceptionHandler {


    /**
     * 其他异常捕捉
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(Exception.class)
    public ResponseVO<String> exceptionHandler(Exception e) {
        log.error("== 捕获的异常", e);
        return ResponseVO.fail(e.getMessage());
    }
}
