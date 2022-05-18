package com.guang.provider.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/26
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskVo implements Serializable {

    private String taskId;

    private String taskName;

    private String taskDesc;

    private String taskType;

    private LocalDateTime endAt;

    private String status;

    private String programId;

    private Boolean isDelay;

}
