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
 * @date 2022/4/26
 */
@Getter
@Setter
@TableName(value = "ds_task")
public class SysTask {


    @TableId(value = "tk_id", type = IdType.UUID)
    private String taskId;


    @TableField(value = "tk_name")
    private String taskName;


    @TableField(value = "tk_desc")
    private String taskDesc;


    @TableField(value = "tk_type")
    private String taskType;


    @TableField(value = "end_at")
    private LocalDateTime endAt;

    private String status;

    private Boolean deleted;

    @TableField(value = "pr_id")
    private String programId;


    @TableField(value = "is_delay")
    private Boolean isDelay;

}
