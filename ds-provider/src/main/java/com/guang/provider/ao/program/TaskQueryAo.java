package com.guang.provider.ao.program;

import com.common.core.web.domain.PageBaseAo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author guangmingdexin
 * @date 2022/4/26
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskQueryAo extends PageBaseAo {

    /**
     * 项目 id
     */
    private String programId;


    /**
     * 任务 id
     */
    private String taskId;
}
