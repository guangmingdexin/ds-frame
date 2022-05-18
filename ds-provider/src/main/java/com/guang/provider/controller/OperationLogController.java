package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.log.OperationLogQueryAo;
import com.guang.provider.router.OperationLogRouterService;
import com.guang.provider.service.impl.OperationLogService;
import com.guang.provider.vo.OperationLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@RestController
@CrossOrigin("*")
public class OperationLogController implements OperationLogRouterService {

    @Autowired
    OperationLogService operationLogService;

    @Override
    public ResponseVO<IPage<OperationLogVo>> getOperationLogBatchBy(OperationLogQueryAo queryAo) {
        return ResponseVO.success(operationLogService.getOperationLogBatchBy(queryAo));
    }
}
