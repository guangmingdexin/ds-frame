package com.guang.provider.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guangmingdexin
 * @date 2022/4/28
 */
@Getter
@AllArgsConstructor
public enum Status {


    /**
     * 状态
     */
    Success("success"),


    Process("processing"),


    Error("error");


    String status;
}
