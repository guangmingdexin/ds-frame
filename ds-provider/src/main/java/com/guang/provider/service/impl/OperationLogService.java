package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.sql.WrapPage;
import com.guang.persistence.domain.SysOperationLog;
import com.guang.persistence.service.log.SysOperationLogService;
import com.guang.provider.ao.log.OperationLogQueryAo;
import com.guang.provider.ao.log.OperationLogUpdateAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IOperationLogService;
import com.guang.provider.vo.OperationLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Service
public class OperationLogService implements IOperationLogService {

    @Autowired
    SysOperationLogService sysOperationLogService;

    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public IPage<OperationLogVo> getOperationLogBatchBy(OperationLogQueryAo queryAo) {

        LambdaQueryWrapper<SysOperationLog> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(!StringUtils.isEmpty(queryAo.getProjectId()), SysOperationLog::getProjectId, queryAo.getProjectId());

        queryWrapper.orderByDesc(SysOperationLog::getLogTime);

        IPage<SysOperationLog> page;

        if(queryAo.getIsLimit()) {
           page  = sysOperationLogService.page(new Page<>(1, queryAo.getMaxLimitCount()));
        }else {
           page = sysOperationLogService.page(new Page<>(queryAo.getPage(), queryAo.getSize()));
        }

        IPage<OperationLogVo> convert = page.convert(this.convert::sysOperationLogConvertVo);

        return new WrapPage<>(convert);
    }

    @Override
    public boolean addOperationLog(OperationLogUpdateAo updateAo) {

        return sysOperationLogService.save(convert.operationLogAoConvertDo(updateAo));
    }
}
