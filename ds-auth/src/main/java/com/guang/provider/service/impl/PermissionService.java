package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.sql.SqlUtil;
import com.common.core.sql.WrapPage;
import com.common.core.web.util.Util;
import com.guang.persistence.domain.SysPermission;
import com.guang.persistence.mapper.SysPermissionMapper;
import com.guang.persistence.service.SysPermissionService;
import com.guang.provider.ao.user.PermissionQueryAo;
import com.guang.provider.ao.user.PermissionUpdateAo;
import com.guang.provider.ao.user.UserPermissionUpdateAo;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IPermissionService;
import com.guang.provider.vo.MenuVo;
import com.guang.provider.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
@Service("permissionService")
public class PermissionService implements IPermissionService {

    private final CommonConvert instance = CommonConvert.INSTANCE;

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Autowired
    SysPermissionService sysPermissionService;

    @Override
    public Set<PermissionVo> getBatchPermissionBy(List<String> roleIds) {

        Set<SysPermission> permissions = sysPermissionMapper.getBatchPermissionBy(SqlUtil.inArrayToString(roleIds));
        return instance.sysPermissionsCovertVos(permissions);

    }

    @Override
    public List<MenuVo> getBatchMenuBy(List<String> roleIds) {

        return instance.sysMenuConvertVos(
                sysPermissionMapper.getBatchMenuBy(SqlUtil.inArrayToString(roleIds))
        );
    }

    @Override
    public List<MenuVo> getBatchBasicBy(List<String> roleIds) {
        return instance.sysMenuConvertVos(
                sysPermissionMapper.getBatchBasicBy(SqlUtil.inArrayToString(roleIds))
        );
    }

    @Override
    public MenuVo getMenuHome(List<String> roleIds) {
        return instance.sysMenuConvertVo(
                sysPermissionMapper.getHomeBy(SqlUtil.inArrayToString(roleIds))
        );
    }

    @Override
    public Set<PermissionVo> getBatchPermission() {

        LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysPermission::getDeleted, 0);

        return instance.sysPermissionsCovertVos(
                new HashSet<>(sysPermissionService.list(queryWrapper)));
    }

    @Override
    public boolean addPermissionForRole(UserPermissionUpdateAo updateAo) {

        // 说明该数据已经存在
        if(sysPermissionMapper.isExistPermissionForRole(updateAo.getRoleId(), updateAo.getPermissionId()) != 0) {
            return true;
        }

        return sysPermissionMapper.addPermissionForRole(Util.generatorUUID(),
                updateAo.getRoleId(),
                updateAo.getPermissionId());
    }

    @Override
    public boolean updatePermission(PermissionUpdateAo updateAo) {
        return sysPermissionService.updateById(instance.permissionAoConvertDo(updateAo));
    }

    @Override
    public boolean updatePermissions(Set<PermissionUpdateAo> updateAos) {
        return sysPermissionService.updateBatchById(instance.permissionAosConvertDos(updateAos));
    }

    @Override
    public boolean addPermission(PermissionUpdateAo updateAo) {
        return sysPermissionService.save(instance.permissionAoConvertDo(updateAo));
    }

    @Override
    public IPage<PermissionVo> getBatchPermissionPage(UserQueryAo queryAo) {

        LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysPermission::getDeleted, 0);

        IPage<SysPermission> page = sysPermissionService.page(new Page<>(queryAo.getPage(), queryAo.getSize()), queryWrapper);

        IPage<PermissionVo> convert = page.convert(instance::sysPerConvertVo);
        return new WrapPage<>(convert);
    }
}
