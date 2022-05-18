package com.guang.provider.controller;

import com.common.core.exception.ResultCode;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.process.ProcessProjectDelAo;
import com.guang.provider.ao.process.ProcessProjectQueryAo;
import com.guang.provider.ao.process.ProcessProjectUpdateAo;
import com.guang.provider.ao.process.ProjectVerifyAo;
import com.guang.provider.common.Module;
import com.guang.provider.router.ProcessProjectRouterService;
import com.guang.provider.service.impl.ProcessProjectService;
import com.guang.provider.vo.ProcessProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangmingdexin
 * @date 2022/4/24
 */
@RestController
@CrossOrigin("*")
public class ProcessProjectController implements ProcessProjectRouterService {

    @Autowired
    ProcessProjectService processProjectService;

    @Override
    public ResponseVO<ProcessProjectVo> getProcessProjectBatchBy(ProcessProjectQueryAo queryAo) {
        return ResponseVO.success(processProjectService.getProcessProjectBatchBy(queryAo));
    }

    @Override
    public ResponseVO<Boolean> addProcessProject(ProcessProjectUpdateAo updateAo) {
        return processProjectService.addProcessProject(updateAo) != null ? ResponseVO.success(true) : ResponseVO.error(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> editProcessProject(ProcessProjectUpdateAo updateAo) {

        if(StringUtils.isEmpty(updateAo.getProcessId())) {

            return ResponseVO.fail(ResultCode.OPERATION_FAIL);
        }

        return ResponseVO.success(processProjectService.editProcessProject(updateAo));
    }

    @Override
    public ResponseVO<Boolean> delProcessProject(ProcessProjectDelAo delAo) {
        return ResponseVO.success(processProjectService.delProcessProject(delAo));
    }

    @Override
    public ResponseVO<ProcessProjectVo> getProcessCurBy(ProcessProjectQueryAo queryAo) {
        return ResponseVO.success(processProjectService.getProjectCurProcessBy(queryAo));
    }

    @Override
    public ResponseVO<Boolean> approveProcessProject(ProjectVerifyAo verifyAo) {

        return processProjectService.verifyProject(verifyAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }
}
