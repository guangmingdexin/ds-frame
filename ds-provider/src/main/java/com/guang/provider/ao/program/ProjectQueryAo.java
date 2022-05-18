package com.guang.provider.ao.program;

import com.common.core.web.domain.PageBaseAo;
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
public class ProjectQueryAo extends PageBaseAo {


    private String programId;

    private String projectId;

    private String projectName;

    private String projectDeclarer;

    private String projectType;

    private String projectDesc;

    private String projectNote;

    private String status;

    private LocalDateTime projectDeclarerTime;


    private String sysStudentId;
}
