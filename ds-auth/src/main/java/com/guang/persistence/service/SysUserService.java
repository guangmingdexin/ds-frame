package com.guang.persistence.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guang.persistence.domain.SysUser;
import com.guang.persistence.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * @author guangyong.deng
 * @date 2022-02-14 16:59
 */
@Service("sysUserService")
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {
}
