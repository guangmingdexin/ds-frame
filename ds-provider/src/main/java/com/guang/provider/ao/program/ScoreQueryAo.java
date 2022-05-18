package com.guang.provider.ao.program;

import com.common.core.web.domain.PageBaseAo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author guangmingdexin
 * @date 2022/5/2
 */
@Getter
@Setter
@ToString
public class ScoreQueryAo extends PageBaseAo {


    private String sysId;

    private String programId;

    /**
     * 获取分数类型
     */
    private String type;

}
