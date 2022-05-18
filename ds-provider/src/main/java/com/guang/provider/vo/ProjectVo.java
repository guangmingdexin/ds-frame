package com.guang.provider.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
@Getter
@Setter
@NoArgsConstructor
public class ProjectVo {


    private String pjId;

    private String pjName;

    private String pjDesc;


    private String pjType;


    private String pjLevel;

    private String pjDeclarer;

    private String pjNote;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime pjDeclarerTime;

    private String status;

    /**
     * 申报人 ID
     */
    private String sysId;


    /**
     * 选择学生 ID
     */
    private String sysStudentId;

}
