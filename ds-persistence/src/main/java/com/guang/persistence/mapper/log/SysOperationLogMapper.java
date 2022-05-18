package com.guang.persistence.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guang.persistence.domain.SysOperationLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Mapper
public interface SysOperationLogMapper extends BaseMapper<SysOperationLog> {
}
