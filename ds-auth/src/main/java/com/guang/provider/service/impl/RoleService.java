package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.guang.persistence.domain.SysRole;
import com.guang.persistence.mapper.SysRoleMapper;
import com.guang.persistence.service.SysRoleService;
import com.guang.provider.ao.user.RolePermissionUpdateAo;
import com.guang.provider.ao.user.RoleUpdateAo;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IRoleService;
import com.guang.provider.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
@Service("roleService")
public class RoleService implements IRoleService {

    private final CommonConvert instance = CommonConvert.INSTANCE;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<RoleVo> getRolesBy(String sysId) {

        // 2. 获取用户角色信息
        List<SysRole> roles = sysRoleMapper.getRolesBy(sysId);

        // 3.筛选用户角色（不必要，在多个角色中，如果存在继承关系，则添加角色直接失败）
        // 3.1 已有父角色 在添加子角色直接失败，因为 父角色已拥有所有子角色权限
        // 3.2 已有子角色 添加父角色，将用户角色变更为父角色
        // 3.3 已有子角色，添加 子角色 直接添加成功
        return instance.sysRoleConvertVos(roles);
    }

    @Override
    public List<RoleVo> getBatchRoleBy(UserQueryAo queryAo) {

        LambdaQueryWrapper<SysRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysRole::isDeleted, 0);

        return instance.sysRoleConvertVos(sysRoleService.list(queryWrapper));
    }

    @Override
    public boolean updateRoleBy(RoleUpdateAo updateAo) {
        return sysRoleService.updateById(instance.roleUpdateAoConvertDo(updateAo));
    }

    @Override
    public boolean deleteRolePermission(RolePermissionUpdateAo updateAo) {
        return sysRoleMapper.deleteRolePermission(updateAo.getRoleId(), updateAo.getPermissionId());
    }

    @Override
    public String addRole(RoleUpdateAo updateAo) {

        SysRole sysRole = instance.roleUpdateAoConvertDo(updateAo);
        sysRole.setCreateTime(LocalDateTime.now());
        sysRoleService.save(sysRole);
        return sysRole.getRoleId();
    }
}
