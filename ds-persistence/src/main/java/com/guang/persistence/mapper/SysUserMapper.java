package com.guang.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guang.persistence.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author guangyong.deng
 * @date 2022-02-14 16:58
 */
@Mapper
@Service
public interface SysUserMapper extends BaseMapper<SysUser> {



}
