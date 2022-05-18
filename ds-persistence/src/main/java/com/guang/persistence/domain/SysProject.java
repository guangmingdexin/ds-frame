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
 * @date 2022/4/19
 */
@Getter
@Setter
@TableName(value = "ds_project")
public class SysProject extends BaseVO {

    @TableId(value = "pj_id", type = IdType.UUID)
    private String projectId;

    @TableField(value = "pj_name")
    private String projectName;


    @TableField(value = "pj_desc")
    private String projectDesc;


    @TableField(value = "pj_type")
    private String projectType;


    @TableField(value = "pj_level")
    private String projectLevel;


    @TableField(value = "pj_declarer")
    private String projectDeclarer;


    @TableField(value = "pj_declarer_time")
    private LocalDateTime projectDeclarerTime;


    @TableField(value = "pj_note")
    private String projectNote;


    @TableField(value = "ds_code")
    private String programId;

    private String status;

    private Boolean deleted;


    @TableField(value = "ps_pj_id")
    private String processProjectId;


    @TableField(value = "sys_id")
    private String sysId;

    @TableField(value = "sys_st_id")
    private String sysStudentId;
}
