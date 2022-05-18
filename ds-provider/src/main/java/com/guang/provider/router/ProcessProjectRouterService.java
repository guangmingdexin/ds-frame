package com.guang.provider.router;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.process.ProcessProjectDelAo;
import com.guang.provider.ao.process.ProcessProjectQueryAo;
import com.guang.provider.ao.process.ProcessProjectUpdateAo;
import com.guang.provider.ao.process.ProjectVerifyAo;
import com.guang.provider.common.Module;
import com.guang.provider.common.OperationType;
import com.guang.provider.log.OperationLog;
import com.guang.provider.vo.ProcessProjectVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author guangmingdexin
 * @date 2022/4/24
 */
public interface ProcessProjectRouterService {


    /**
     * @param queryAo
     * @return
     */
    @PostMapping(value = "/api/process/project/list")
    ResponseVO<ProcessProjectVo> getProcessProjectBatchBy(@RequestBody @Validated ProcessProjectQueryAo queryAo);


    @PostMapping(value = "/api/process/project/save")
    ResponseVO<Boolean> addProcessProject(@RequestBody ProcessProjectUpdateAo updateAo);


    @PostMapping(value = "/api/process/project/edit")
    ResponseVO<Boolean> editProcessProject(@RequestBody @Validated ProcessProjectUpdateAo updateAo);

    @PostMapping(value = "/api/process/project/del")
    ResponseVO<Boolean> delProcessProject(@RequestBody @Validated ProcessProjectDelAo delAo);


    @PostMapping(value = "/api/process/project/cur")
    ResponseVO<ProcessProjectVo> getProcessCurBy(@RequestBody @Validated ProcessProjectQueryAo queryAo);

    @PostMapping(value = "/api/process/project/approve")
    @OperationLog(operationModule = Module.PROJECT_VERIFY,
            operationType = OperationType.PROJECT_APPROVE,
            operationDesc = OperationType.PROJECT_APPROVE)
    ResponseVO<Boolean> approveProcessProject(@RequestBody @Validated ProjectVerifyAo verifyAo);
}
