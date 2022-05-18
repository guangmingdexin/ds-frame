package com.guang.provider.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author guangmingdexin
 * @date 2022/4/27
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskStaticCountVo {


    /**
     * 完成任务数
     */
    int complete;


    /**
     * 未完成任务数
     */
    int fail;


    /**
     * 总共任务数
     */
    int total;
}
