package com.guang.provider.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@AllArgsConstructor
@Getter
public enum OperationType {


    /**
     * 课题审核
     */
    PROJECT_APPROVE("PROJECT_APPROVE", "流程通过"),
    PROJECT_REJECT("PROJECT_APPROVE", "课题驳回"),
    PROJECT_FINISH("PROJECT_APPROVE", "课题审核通过"),
    PROJECT_DECLARER("PROJECT_DECLARER", "课题申报"),

    NONE("NONE", "无定义的操作");


    String name;


    String desc;


}
