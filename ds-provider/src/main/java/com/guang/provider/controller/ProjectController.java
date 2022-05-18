package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.exception.ResultCode;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.program.ProjectDelAo;
import com.guang.provider.ao.program.ProjectQueryAo;
import com.guang.provider.ao.program.ProjectUpdateAo;
import com.guang.provider.router.ProjectRouterService;
import com.guang.provider.service.impl.ProjectService;
import com.guang.provider.vo.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
@RestController
@CrossOrigin("*")
public class ProjectController implements ProjectRouterService {


    @Autowired
    ProjectService projectService;



    @Override
    public ResponseVO<IPage<ProjectVo>> getBatchProjectBy(ProjectQueryAo queryAo) {

        // 1.参数校验
        if(StringUtils.isEmpty(queryAo.getProgramId())) {
            return ResponseVO.fail("缺少参数 programId");
        }

        return ResponseVO.success(projectService.getBatchProjectBy(queryAo));
    }

    @Override
    public ResponseVO<ProjectVo> getOneProjectBy(ProjectQueryAo queryAo) {
        return ResponseVO.success(projectService.getOneProjectBy(queryAo));
    }

    @Override
    public ResponseVO<ProjectVo> getOneProjectByStId(ProjectQueryAo queryAo) {

        if(StringUtils.isEmpty(queryAo.getSysStudentId())) {
            return ResponseVO.fail(ResultCode.PARAM_ERROR);
        }

        return ResponseVO.success(projectService.getOneProjectByStId(queryAo));
    }

    @Override
    public ResponseVO<ProjectVo> addProject(ProjectUpdateAo updateAo) {

        ProjectVo vo = projectService.addProject(updateAo);
        return vo != null ? ResponseVO.success(vo) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> updateProject(ProjectUpdateAo updateAo) {
        return projectService.updateProject(updateAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> delProject(ProjectDelAo delAo) {
        return projectService.delProject(delAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }
}
