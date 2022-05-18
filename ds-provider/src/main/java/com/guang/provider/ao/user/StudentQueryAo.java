package com.guang.provider.ao.user;

import com.common.core.web.domain.PageBaseAo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class StudentQueryAo extends PageBaseAo {


    private String id;

    /**
     * 项目 id
     */
    private String programId;


    /**
     * 学号
     */
    private String no;

    /**
     * 姓名
     */
    private String name;


    private String dept;


    private String major;


    private String status;

}
