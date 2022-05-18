package com.guang.provider.ao.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
@Getter
@Setter
public class TeacherUpdateAo {

    private String sysId;

    @NotNull(message = "职工号不能为空")
    private String no;

    @NotNull(message = "姓名不能为空")
    private String name;

    @NotNull(message = "学院不能为空")
    private String dept;

    @NotNull(message = "职称不能为空")
    private String job;

    private String mail;


    private String tel;

    private String status;


    @NotNull(message = "项目 id 不能为空")
    private String programId;

}
