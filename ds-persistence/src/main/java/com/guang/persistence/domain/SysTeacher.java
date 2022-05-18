package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author guangmingdexin
 * @date 2022/4/13
 */
@Getter
@Setter
@TableName(value = "sys_tc")
public class SysTeacher {

    @TableId(value = "sys_id", type = IdType.UUID)
    private String sysId;

    @TableField(value = "sys_tc_no")
    private String no;

    @TableField(value = "sys_tc_name")
    private String name;

    @TableField(value = "sys_tc_job")
    private String job;

    @TableField(value = "sys_tc_dept")
    private String dept;

    @TableField(value = "sys_tc_mail")
    private String mail;

    @TableField(value = "sys_tc_tel")
    private String tel;

    @TableField(value = "sys_tc_status")
    private String status;

    private Boolean deleted;

    @TableField(value = "pr_id")
    private String programId;
}
