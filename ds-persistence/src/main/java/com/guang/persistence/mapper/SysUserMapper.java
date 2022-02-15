package com.guang.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guang.persistence.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author guangyong.deng
 * @date 2022-02-14 16:58
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
