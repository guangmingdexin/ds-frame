package com.guang.provider.ao.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author guangmingdexin
 * @date 2022/5/3
 */
@Getter
@Setter
@NoArgsConstructor
public class StudentScoreUpdateAo {


    private String guideScore;


    private String viewScore;

    private String replyScore;


    private String finalScore;


    /**
     * 更新的学生 id
     */
    private String sysId;

}
