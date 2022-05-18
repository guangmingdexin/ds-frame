package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Objects;

/**
 *
 * 课题审核流程
 *
 * @author guangmingdexin
 * @date 2022/4/24
 */
@Getter
@Setter
@TableName("ps_project")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysProcessProject {

    @TableId(value = "ps_pj_id", type = IdType.UUID)
    private String processId;

    @TableField(value = "ps_pj_name")
    private String processName;

    @TableField(value = "ps_pj_desc")
    private String processDesc;


    @TableField(value = "sys_id")
    private String sysId;

    @TableField(value = "pr_id")
    private String programId;

    private Boolean deleted;

    @TableField(value = "is_must")
    private Boolean isMust;


    @TableField(value = "leader")
    private String leader;


    @TableField(value = "next_ps_id")
    private String nextProcessId;

    @TableField(value = "prev_ps_id")
    private String prevProcessId;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysProcessProject that = (SysProcessProject) o;
        return processId.equals(that.processId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId);
    }
}
