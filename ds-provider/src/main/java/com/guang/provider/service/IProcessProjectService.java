package com.guang.provider.service;

import com.guang.persistence.domain.SysProcessProject;
import com.guang.provider.ao.process.ProcessProjectDelAo;
import com.guang.provider.ao.process.ProcessProjectQueryAo;
import com.guang.provider.ao.process.ProcessProjectUpdateAo;
import com.guang.provider.ao.process.ProjectVerifyAo;
import com.guang.provider.vo.ProcessProjectVo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/24
 */
public interface IProcessProjectService {


    /**
     *
     * 查询项目-课题审核所有流程
     *
     * @param queryAo
     * @return
     */
    List<ProcessProjectVo> getProcessProjectBatchBy(ProcessProjectQueryAo queryAo);


    ProcessProjectVo getProjectCurProcessBy(ProcessProjectQueryAo queryAo);


    /**
     *
     * 是否通过
     *
     * @param verifyAo 审核对象
     * @return
     */
    boolean verifyProject(ProjectVerifyAo verifyAo);


    /**
     *
     * 获取课题的下一流程状态
     *
     * @param processId
     * @return
     */
    SysProcessProject getNextProcess(@NotNull String processId);


    SysProcessProject getPrevProcess(@NotNull String processId);


    /**
     * @param updateAo
     * @return
     */
    SysProcessProject addProcessProject(ProcessProjectUpdateAo updateAo);


    boolean editProcessProject(ProcessProjectUpdateAo updateAo);

    /**
     * @param delAo
     * @return
     */
    boolean delProcessProject(ProcessProjectDelAo delAo);
}
