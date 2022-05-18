package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@Getter
@Setter
@TableName(value = "ds_program")
public class SysProgram extends BaseVO {

    @TableId(value = "pr_id", type = IdType.UUID)
    private String programId;

    @TableField(value = "pr_title")
    private String programTitle;


    @TableField(value = "pr_desc")
    private String programDesc;


    @TableField(value = "pr_owner")
    private String programOwner;

    @TableField(value = "pr_schedule")
    private String programSchedule;


    @TableField(value = "sys_id")
    private String sysId;

    /**
     * 直接通过返回网址进行显示
     */
    @TableField(value = "sys_avatar_id")
    private String avatar;

    @TableField(value = "create_time")
    private LocalDateTime startAt;

    @TableField(value = "end_at")
    private LocalDateTime endAt;


    private String status;


    private Boolean deleted;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("\"super\":").append(super.toString()).append(", ")
                .append("\"programId\":").append(programId)
                .append(", \"programTitle\":").append(programTitle)
                .append(", \"programDesc\":").append(programDesc)
                .append(", \"programOwner\":").append(programOwner)
                .append(", \"programSchedule\":").append(programSchedule)
                .append(", \"sysId\":").append(sysId)
                .append(", \"avatar\":").append(avatar)
                .append(", \"status\":").append(status)
                .append('}');
        return sb.toString();
    }
}
