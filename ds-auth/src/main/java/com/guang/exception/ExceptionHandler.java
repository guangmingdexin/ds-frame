package com.guang.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.common.core.web.domain.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author guangmingdexin
 * @date 2022/4/21
 */
@RestControllerAdvice
@CrossOrigin("*")
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotLoginException.class)
    public ResponseVO<String> exceptionHandler(NotLoginException e) {
//        log.error("== 未捕获的异常", e);
        return ResponseVO.fail(e.getMessage());
    }
}
