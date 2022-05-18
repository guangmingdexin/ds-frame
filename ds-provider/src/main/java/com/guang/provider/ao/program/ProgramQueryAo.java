package com.guang.provider.ao.program;

import com.common.core.web.domain.PageBaseAo;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@Getter
@ToString
public class ProgramQueryAo extends PageBaseAo {

    @NotNull(message = "用户 id 不能为空")
    private String id;


    /**
     * 项目状态
     */
    private String status;


    /**
     * 保留字段 （模糊搜索内容）
     */
    private String query;


    private String programId;

}
