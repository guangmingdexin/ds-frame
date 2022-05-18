package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.log.OperationLogQueryAo;
import com.guang.provider.vo.OperationLogVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
public interface OperationLogRouterService {


    @PostMapping(value = "/api/operation/list")
    ResponseVO<IPage<OperationLogVo>> getOperationLogBatchBy(@RequestBody @Validated OperationLogQueryAo queryAo);
}
