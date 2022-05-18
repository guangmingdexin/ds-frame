package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
@Getter
@Setter
@TableName("sys_st")
@ToString
public class SysStudent extends BaseVO {

    @TableId(value = "sys_id", type = IdType.UUID)
    private String sysId;

    @TableField(value = "sys_st_no")
//    @ExcelProperty(value = "学号")
    private String no;


    @TableField(value = "sys_st_name")
//    @ExcelProperty(value = "姓名")
    private String name;


    @TableField(value = "sys_st_dept")
//    @ExcelProperty(value = "学院")
    private String dept;


    @TableField(value = "sys_st_major")
//    @ExcelProperty(value = "专业")
    private String major;


    @TableField(value = "sys_st_grade")
//    @ExcelProperty(value = "年级")
    private String grade;


    @TableField(value = "sys_st_email")
//    @ExcelProperty(value = "邮箱")
    private String mail;


    @TableField(value = "sys_st_tel")
//    @ExcelProperty(value = "电话")
    private String tel;


    @TableField(value = "sys_st_verify")
//    @ExcelProperty(value = "预审结果")
    private Boolean verify;


    @TableField(value = "status")
//    @ExcelProperty(value = "状态")
    private String status;


    @TableField(value = "deleted")
    private Boolean deleted;

    @TableField(value = "pr_id")
    private String programId;

    @TableField(value = "guide_score")
    private String guideScore;

    @TableField(value = "guide_review")
    private String guideReview;

    @TableField(value = "view_score")
    private String viewScore;

    @TableField(value = "view_review")
    private String viewReview;

    @TableField(value = "reply_score")
    private String replyScore;

    @TableField(value = "reply_review")
    private String replyReview;

    @TableField(value = "final_score")
    private String finalScore;
}
