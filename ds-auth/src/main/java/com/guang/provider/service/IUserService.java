package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.provider.ao.user.*;
import com.guang.provider.bo.UserBo;
import com.guang.provider.vo.MenuVo;
import com.guang.provider.vo.PermissionVo;
import com.guang.provider.vo.RolePermissionVo;
import com.guang.provider.vo.UserVo;
import org.apache.catalina.User;

import java.util.List;
import java.util.Set;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:19
 */
public interface IUserService {




    /**
     *
     *  查询用户信息
     *
     * @param userQueryAo  查询对象
     * @return 用户信息
     */
    UserVo getUserInfo(UserQueryAo userQueryAo);


    /**
     *
     * 查询用户菜单列表
     *
     * @param userQueryAo 查询对象
     * @return
     */
    List<MenuVo> getUserNav(UserQueryAo userQueryAo);

    /**
     *
     * 查询用户基础组件
     *
     * @param userQueryAo 查询对象
     * @return
     */
    List<MenuVo> getUserBasic(UserQueryAo userQueryAo);

    /**
     *
     * 查询用户首页
     *
     * @param userQueryAo 查询对象
     * @return
     */
    MenuVo getUserHome(UserQueryAo userQueryAo);


    /**
     *
     * 获取所有角色权限（分页）
     *
     * @param queryAo 查询对象
     * @return
     */
    IPage<RolePermissionVo> getBatchRolePermissionBy(UserQueryAo queryAo);

    /**
     *
     * 根据角色获取所有权限
     *
     * @param queryAo
     * @return
     */
    Set<PermissionVo> getBatchPermissionBy(PermissionQueryAo queryAo);

    Set<PermissionVo> getBatchPermission();


    boolean addPermissionForRole(UserPermissionUpdateAo updateAo);


    String addRole(RoleUpdateAo updateAo);


    boolean updatePermission(PermissionUpdateAo updateAo);


    boolean updateRole(RolePermissionVo updateAo);


    boolean addPermission(PermissionUpdateAo updateAo);


    IPage<PermissionVo> getBatchPermissionPage(UserQueryAo queryAo);
}
