package com.guang.provider.ao.process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author guangmingdexin
 * @date 2022/4/24
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class ProcessProjectQueryAo {


    /**
     * 项目 id
     */
    @NotNull(message = "programId 不能为空")
    private String programId;


    /**
     * 用户 id
     */
    private String sysId;


    private String projectId;
}
