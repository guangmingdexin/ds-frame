package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.provider.ao.log.OperationLogQueryAo;
import com.guang.provider.ao.log.OperationLogUpdateAo;
import com.guang.provider.vo.OperationLogVo;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
public interface IOperationLogService {


    /**
     *
     * 批量查询
     *
     * @param queryAo
     * @return
     */
    IPage<OperationLogVo> getOperationLogBatchBy(OperationLogQueryAo queryAo);



    boolean addOperationLog(OperationLogUpdateAo updateAo);
}
