package com.guang.provider.ao.user;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentUpdateAo {

    private String sysId;

    @NotNull(message = "学号不能为空")
    @ExcelProperty(value = "学号")
    private String no;

    @NotNull(message = "姓名不能为空")
    @ExcelProperty(value = "姓名")
    private String name;

    @NotNull(message = "学院不能为空")
    @ExcelProperty(value = "学院")
    private String dept;

    @NotNull(message = "专业不能为空")
    @ExcelProperty(value = "专业")
    private String major;

    @NotNull(message = "年级不能为空")
    @ExcelProperty(value = "年级")
    private String grade;

//    @Email(message = "邮箱格式错误")
    @ExcelProperty(value = "邮箱")
    private String mail;


    @ExcelProperty(value = "电话")
    private String tel;


    @ExcelProperty(value = "预审结果")
    private Boolean verify;


    @ExcelProperty(value = "状态")
    private String status;


    @NotNull(message = "项目 id 不能为空")
    private String programId;

    private String guideScore;

    private String guideReview;

    private String viewScore;

    private String viewReview;

    private String replyScore;

    private String replyReview;

    private String finalScore;
}
