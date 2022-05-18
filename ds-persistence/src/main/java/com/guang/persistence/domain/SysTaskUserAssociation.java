package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author guangmingdexin
 * @date 2022/4/28
 */
@Getter
@Setter
@TableName("ds_task_user")
@AllArgsConstructor
@NoArgsConstructor
public class SysTaskUserAssociation {

    @TableId(value = "id", type = IdType.UUID)
    private String id;


    @TableField(value = "tk_id")
    private String taskId;

    @TableField(value = "sys_id")
    private String sysId;


    @TableField(value = "status")
    private String status;


    @TableField(value = "pr_id")
    private String programId;
}
