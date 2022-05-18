package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.provider.ao.user.PermissionQueryAo;
import com.guang.provider.ao.user.PermissionUpdateAo;
import com.guang.provider.ao.user.UserPermissionUpdateAo;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.vo.MenuVo;
import com.guang.provider.vo.PermissionVo;

import java.util.List;
import java.util.Set;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
public interface IPermissionService {


    /**
     *
     * 获取权限列表
     *
     * @param roleIds 角色 id集合
     * @return 权限列表
     */
    Set<PermissionVo> getBatchPermissionBy(List<String> roleIds);


    /**
     *
     * 获取菜单列表
     *
     * @param roleIds 角色 id集合
     * @return 菜单列表
     */
    List<MenuVo> getBatchMenuBy(List<String> roleIds);

    /**
     *
     * 获取基础组件集合
     *
     * @param roleIds 角色 id 集合
     * @return 基础组件集合
     */
    List<MenuVo> getBatchBasicBy(List<String> roleIds);

    /**
     *
     * 获取用户首页
     *
     * @param roleIds 角色 id集合
     * @return 首页
     */
    MenuVo getMenuHome(List<String> roleIds);


    /**
     *
     * 获取系统的所有 permission
     *
     * @return  vo
     */
    Set<PermissionVo> getBatchPermission();


    /**
     *
     * 为角色添加权限
     *
     * @param
     * @return
     */
    boolean addPermissionForRole(UserPermissionUpdateAo updateAo);


    boolean updatePermission(PermissionUpdateAo updateAo);

    boolean updatePermissions(Set<PermissionUpdateAo> updateAos);


    boolean addPermission(PermissionUpdateAo updateAo);


    IPage<PermissionVo> getBatchPermissionPage(UserQueryAo queryAo);

}
