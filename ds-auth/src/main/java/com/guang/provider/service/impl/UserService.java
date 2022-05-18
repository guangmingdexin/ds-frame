package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.sql.WrapPage;
import com.guang.persistence.domain.SysUser;
import com.guang.persistence.service.SysUserService;
import com.guang.provider.ao.user.*;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IUserService;
import com.guang.provider.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:20
 */
@Service("userService")
public class UserService implements IUserService {


    @Autowired
    SysUserService sysUserService;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;


    final CommonConvert convert = CommonConvert.INSTANCE;


    @Override
    public UserVo getUserInfo(UserQueryAo userQueryAo) {
        // 1. 通过 用户 id 获取用户角色信息
        LambdaQueryWrapper<SysUser> userQuery = Wrappers.lambdaQuery();

        userQuery.eq(SysUser::getSysId, userQueryAo.getId())
                        .eq(SysUser::getDeleted, '0');

        SysUser sysUser = sysUserService.getOne(userQuery);

        // 1.1 更加 avatarId 获取头像路径
        // 1.2 类型转换
        UserVo userVo = convert.sysUserConvertVo(sysUser);

        if(userVo == null) {
            return null;
        }

        // 2. 获取用户角色列表
        List<RoleVo> roles = roleService.getRolesBy(userQueryAo.getId());


        // 3.获取每个角色的所有权限(界面操作权限，菜单展示权限)

        List<String> roleIds = roles.stream().map(RoleVo::getId).collect(Collectors.toList());

        Set<PermissionVo> actions = permissionService.getBatchPermissionBy(roleIds);

        userVo.setPermissions(actions);
        userVo.setRoles(roles);

        return userVo;
    }

    @Override
    public List<MenuVo> getUserNav(UserQueryAo userQueryAo) {

        List<RoleVo> roles = roleService.getRolesBy(userQueryAo.getId());
        List<String> roleIds = roles.stream().map(RoleVo::getId).collect(Collectors.toList());

        return permissionService.getBatchMenuBy(roleIds);
    }

    @Override
    public List<MenuVo> getUserBasic(UserQueryAo userQueryAo) {
        List<RoleVo> roles = roleService.getRolesBy(userQueryAo.getId());
        List<String> roleIds = roles.stream().map(RoleVo::getId).collect(Collectors.toList());

        return permissionService.getBatchBasicBy(roleIds);
    }

    @Override
    public MenuVo getUserHome(UserQueryAo userQueryAo) {
        List<RoleVo> roles = roleService.getRolesBy(userQueryAo.getId());
        List<String> roleIds = roles.stream().map(RoleVo::getId).collect(Collectors.toList());

        return permissionService.getMenuHome(roleIds);
    }

    @Override
    public IPage<RolePermissionVo> getBatchRolePermissionBy(UserQueryAo queryAo) {
        // 先查询出所有角色
        List<RoleVo> roles = roleService.getBatchRoleBy(queryAo);
        List<RolePermissionVo> voList = convert.roleVosConvertVos(roles);

        // 根据角色获取权限

        for (RolePermissionVo vo : voList) {
            Set<PermissionVo> permissions = permissionService.getBatchPermissionBy(Collections.singletonList(vo.getId()));
            vo.setPermissions(permissions);

        }

        IPage<RolePermissionVo> page = new Page<>(queryAo.getPage(), queryAo.getSize());
        page.setRecords(voList);

        return new WrapPage<>(page);
    }

    @Override
    public Set<PermissionVo> getBatchPermissionBy(PermissionQueryAo queryAo) {
        return  permissionService.getBatchPermissionBy(Collections.singletonList(queryAo.getRoleId()));
    }

    @Override
    public Set<PermissionVo> getBatchPermission() {
        return permissionService.getBatchPermission();
    }

    @Override
    public boolean addPermissionForRole(UserPermissionUpdateAo updateAo) {

        return permissionService.addPermissionForRole(updateAo);
    }

    @Override
    public String addRole(RoleUpdateAo updateAo) {
        return roleService.addRole(updateAo);
    }

    @Override
    public boolean updatePermission(PermissionUpdateAo updateAo) {
        return permissionService.updatePermission(updateAo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(RolePermissionVo updateAo) {

        // 首先更新角色
        RoleUpdateAo roleUpdateAo = new RoleUpdateAo();
        roleUpdateAo.setCn(updateAo.getCn());
        roleUpdateAo.setId(updateAo.getId());
        roleUpdateAo.setDescribe(updateAo.getDescribe());
        roleUpdateAo.setName(updateAo.getName());
        roleUpdateAo.setStatus(updateAo.getStatus());

        boolean r1 = roleService.updateRoleBy(roleUpdateAo);

        // 其次更新角色的权限
        Set<PermissionVo> permissions = updateAo.getPermissions();

        // 判断是否直接删除了模块
        // 如果删除了模块，则不需要更新权限，否则需要重新更新权限
        permissions.stream()
                .filter(p -> p.getLabels() == null)
                .forEach(p -> roleService.deleteRolePermission(
                        new RolePermissionUpdateAo(roleUpdateAo.getId(), p.getPermissionId())
                ));

        Set<PermissionVo> collect = permissions
                .stream()
                .filter(p -> p.getLabels() != null)
                .collect(Collectors.toSet());

        // vo -> ao
        boolean r2 = permissionService.updatePermissions(CommonConvert.INSTANCE.permissionVosConvertAos(collect));

        return r1 && r2;
    }

    @Override
    public boolean addPermission(PermissionUpdateAo updateAo) {
        return permissionService.addPermission(updateAo);
    }

    @Override
    public IPage<PermissionVo> getBatchPermissionPage(UserQueryAo queryAo) {
        return permissionService.getBatchPermissionPage(queryAo);
    }
}
