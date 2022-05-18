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
 * @date 2022/4/30
 */
@Getter
@Setter
@TableName(value = "ds_group")
public class SysGroup {

    @TableId(value = "gp_id", type = IdType.UUID)
    private String groupId;

    @TableField(value = "gp_name")
    private String groupName;

    @TableField(value = "gp_time")
    private LocalDateTime groupTime;

    @TableField(value = "gp_location")
    private String groupLocation;

    @TableField(value = "gp_teachers")
    private String groupTeachers;

    @TableField(value = "gp_students")
    private String groupStudents;

    @TableField(value = "gp_leader")
    private String groupLeader;

    @TableField(value = "pr_id")
    private String programId;

    @TableField(value = "deleted")
    private Boolean deleted;
}
