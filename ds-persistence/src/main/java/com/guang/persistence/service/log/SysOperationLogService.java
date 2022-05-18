package com.guang.persistence.service.log;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guang.persistence.domain.SysOperationLog;
import com.guang.persistence.mapper.log.SysOperationLogMapper;
import org.springframework.stereotype.Service;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Service
public class SysOperationLogService extends ServiceImpl<SysOperationLogMapper, SysOperationLog> {
}
