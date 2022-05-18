package com.guang.provider.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author guangmingdexin
 * @date 2022/4/24
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProcessProjectVo implements Serializable {


    private String processId;

    private String processName;

    private String processDesc;

    private String sysId;

    private String programId;

    private Boolean deleted;

    private Boolean isMust;

    private TeacherVo leader;

    private String nextProcessId;

    private String prevProcessId;

    private Integer current;
}
