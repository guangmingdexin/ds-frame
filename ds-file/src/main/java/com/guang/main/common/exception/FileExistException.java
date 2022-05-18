package com.guang.main.common.exception;

/**
 * @author guangmingdexin
 * @date 2022/4/23
 */
public class FileExistException extends RuntimeException {

    public FileExistException(String message) {
        super(message);
    }
}
