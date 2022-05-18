package com.guang.provider.ao.process;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author guangmingdexin
 * @date 2022/4/24
 */
@Getter
@Setter
public class ProjectVerifyAo {


    /**
     * 课题 ID
     */
    @NotNull(message = "课题 ID 不能为空")
    private String projectId;


    @NotNull(message = "流程 ID 不能为空")
    private String processId;


    /**
     * 确定审核是否通过
     */
    @NotNull(message = "审核结果不能为空")
    private Boolean approve;
}
