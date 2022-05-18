package com.guang.persistence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guang.persistence.domain.SysPermission;
import com.guang.persistence.mapper.SysPermissionMapper;
import org.springframework.stereotype.Service;

/**
 * @author guangmingdexin
 */
@Service(value = "sysPermissionService")
public class SysPermissionService extends ServiceImpl<SysPermissionMapper, SysPermission> {
}
