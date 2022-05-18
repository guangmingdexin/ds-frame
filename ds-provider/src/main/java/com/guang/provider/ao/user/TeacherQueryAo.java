package com.guang.provider.ao.user;

import com.common.core.web.domain.PageBaseAo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author guangmingdexin
 * @date 2022/4/13
 */
@Getter
@Setter
@NoArgsConstructor
public class TeacherQueryAo extends PageBaseAo {

    /**
     * 用户 id
     */
    private String id;

    /**
     * 毕设 id
     */
    private String programId;

    private String no;

    private String name;

    private String dept;

    private String job;

    private String status;

    /**
     * 模糊搜索
     */
    private String content;



}
