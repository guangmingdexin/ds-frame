package com.guang.provider.ao.process;

import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Getter
public class ProcessProjectDelAo {

    /**
     * 删除流程 ID
     */
    @NotNull(message = "processId 不能为空")
    private String processId;

}
