package com.guang.provider.ao.log;

import com.common.core.web.domain.PageBaseAo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Getter
@Setter
public class OperationLogQueryAo extends PageBaseAo {


    private String projectId;


    /**
     * 是否只展示局部数据（默认为最多 5 条）
     */
    private Boolean isLimit;

    /**
     * 局部数据展示最大值
     */
    private Integer maxLimitCount = 5;


}
