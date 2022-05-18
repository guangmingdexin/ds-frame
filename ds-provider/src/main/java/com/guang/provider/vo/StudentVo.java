package com.guang.provider.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class StudentVo implements Serializable {


    private String sysId;


    private String stNo;


    private String stName;


    private String stDept;


    private String stMajor;


    private String grade;

    private String tel;


    private String verify;


    private String status;


    private String programId;


    /**
     * 指导教师
     */
    private String tcName;


    private String guideScore;

    private String guideReview;

    private String viewScore;

    private String viewReview;

    private String replyScore;

    private String replyReview;

    private String finalScore;


}
