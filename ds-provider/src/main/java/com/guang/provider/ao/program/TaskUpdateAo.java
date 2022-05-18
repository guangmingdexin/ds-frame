package com.guang.provider.ao.program;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/26
 */
@Getter
@Setter
public class TaskUpdateAo {

    private String taskId;

    private String taskName;

    private String taskDesc;

    private String taskType;

    private LocalDateTime endAt;

    private String status;

    private String programId;

    private Boolean isDelay;
}
