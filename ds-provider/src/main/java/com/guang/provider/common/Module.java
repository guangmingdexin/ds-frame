package com.guang.provider.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@AllArgsConstructor
@Getter
public enum Module {

    /**
     * 初始模块
     */
    NONE("NONE", "初始模块"),

    /**
     * 课题模块
     */
    PROJECT("PROJECT", "课题模块"),

    /**
     * 项目模块
     */
    PROCESS("PROCESS", "项目模块"),

    PROJECT_VERIFY("PROJECT_VERFIY", "课题审核模块"),

    /**
     * 学生模块
     */
    STUDENT("STUDENT", "学生模块");


    public String module;


    String desc;
}
