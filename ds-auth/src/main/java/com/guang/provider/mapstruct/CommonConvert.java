package com.guang.provider.mapstruct;

import com.guang.persistence.domain.SysMenu;
import com.guang.persistence.domain.SysPermission;
import com.guang.persistence.domain.SysRole;
import com.guang.persistence.domain.SysUser;
import com.guang.provider.ao.user.PermissionUpdateAo;
import com.guang.provider.ao.user.RoleUpdateAo;
import com.guang.provider.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @author guangmingdexin
 * @date 2022/4/21
 */
@Mapper(uses = SpecialTypeConvert.class)
public interface CommonConvert {


    CommonConvert INSTANCE = Mappers.getMapper(CommonConvert.class);


    /**
     *
     * 返回 RoleVo
     *
     * @param sysRole SysRole
     * @return RoleVo
     */
    List<RoleVo> sysRoleConvertVos(List<SysRole> sysRole);

    @Mappings({
            @Mapping(source = "roleId", target = "id"),
            @Mapping(source = "roleName", target = "name"),
            @Mapping(source = "roleCn", target = "cn"),
            @Mapping(source = "roleDesc", target = "describe")
    })
    RoleVo sysRoleConvertVo(SysRole sysRole);


    @Mappings({
            @Mapping(target = "roleId", source = "id"),
            @Mapping(target = "roleName", source = "name"),
            @Mapping(target = "roleCn", source = "cn"),
            @Mapping(target = "roleDesc", source = "describe"),
            @Mapping(target = "status", source = "status")
    })
    SysRole roleUpdateAoConvertDo(RoleUpdateAo updateAo);


    RolePermissionVo roleVoConvertVo(RoleVo roleVo);


    List<RolePermissionVo> roleVosConvertVos(List<RoleVo> roleVos);


    /**
     *
     * 返回 PermissionVo
     *path
     * @param permissions Permissions
     * @return Set
     */

    Set<PermissionVo> sysPermissionsCovertVos(Set<SysPermission> permissions);

    @Mappings({
            @Mapping(source = "resId", target = "permissionId"),
            @Mapping(source = "resName", target = "permissionName"),
            @Mapping(source = "resType", target = "type"),
            @Mapping(source = "resPath", target = "path"),
            @Mapping(source = "resActions", target = "actions"),
            @Mapping(source = "resActions", target = "actionEntitySet", qualifiedByName = "actionEntitySet"),
            @Mapping(source = "resActions", target = "labels", qualifiedByName = "labels")

    })
    PermissionVo sysPerConvertVo(SysPermission permission);


    /**
     *
     * ao -> do
     *
     * @param updateAo ao
     * @return do
     */
    @Mappings({
            @Mapping(target = "resId", source = "permissionId"),
            @Mapping(target = "resName", source = "permissionName"),
            @Mapping(target = "resActions", source = "labels", qualifiedByName = "actions"),
    })
    SysPermission permissionAoConvertDo(PermissionUpdateAo updateAo);


    Set<SysPermission> permissionAosConvertDos(Set<PermissionUpdateAo> updateAos);


    PermissionUpdateAo permissionVoConvertAo(PermissionVo permissionVo);


    Set<PermissionUpdateAo> permissionVosConvertAos(Set<PermissionVo> vos);


    /**
     *
     * 返回菜单列表
     *
     * @param menus Menu
     * @return MenuVo
     */
    List<MenuVo> sysMenuConvertVos(List<SysMenu> menus);

    /**
     *
     * do -> vo
     *
     * @param menu
     * @return
     */
    @Mappings({
            @Mapping(source = "resId", target = "id"),
            @Mapping(source = "resName", target = "name"),
            @Mapping(source = "resParentId", target = "parentId"),
            @Mapping(source = "resPath", target = "path"),
            @Mapping(source = "resComponent", target = "component"),
            @Mapping(source = "resMeta", target = "meta", qualifiedByName = "meta"),
            @Mapping(source = "resRedirect", target = "redirect")

    })
    MenuVo sysMenuConvertVo(SysMenu menu);

    /**
     *
     * 返回 UserVo
     *
     * @param sysUser SysUser
     * @return vo
     */
    @Mappings({
            @Mapping(source = "sysId", target = "id"),
            @Mapping(source = "sysAccount", target = "name"),
            @Mapping(source = "sysUsername", target = "username"),
            @Mapping(source = "sysAvatarId", target = "avatar", qualifiedByName = "avatar")

    })
    UserVo sysUserConvertVo(SysUser sysUser);
}
