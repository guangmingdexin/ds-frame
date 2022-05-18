package com.guang.provider.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guangmingdexin
 * @date 2022/4/27
 */
@Getter
@AllArgsConstructor
public enum TaskType {

    /**
     * 任务类型
     */
    Student_Task_Type("学生任务"),

    Teacher_Task_Type("教师任务"),

    All_Task_Type("全体任务");


    String type;


}
