package com.guang.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guang.persistence.domain.SysMenu;
import com.guang.persistence.domain.SysPermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author asus
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {


    /**
     *
     * 根据角色列表获取操作权限列表
     *
     * @param roleIds 角色集合
     * @return permissions
     */
    @Select(value =
            "select " +
                    "*, " +
                    "id sys_role_action_id " +
            "from " +
                    "res_action ra " +
            "INNER JOIN " +
                    "sys_role_action sra " +
            "on " +
                    "ra.res_id = sra.res_id " +
            "where " +
                    "sra.role_id " +
            "in " +
                    "( ${roleIds} ) " +
            "and " +
                    "ra.deleted = 0"
    )
    Set<SysPermission> getBatchPermissionBy(@Param("roleIds") String roleIds);


    /**
     *
     * 根据角色列表获取菜单列表
     *
     * @param roleIds 角色集合
     * @return menu
     */
    @Select(
            "select " +
                    "*, " +
                    "id sys_role_menu_id " +
            "from " +
                    "res_menu rm " +
            "INNER JOIN " +
                    "sys_role_menu srm " +
            "on " +
                    "rm.res_id = srm.res_id " +
            "where " +
                    "srm.role_id " +
            "in " +
                    "( ${roleIds} ) " +
            "and " +
                    "rm.deleted = 0 " +
            "and " +
                    "rm.is_menu = 1 order by rm.priority asc"
    )
    List<SysMenu> getBatchMenuBy(@Param("roleIds") String roleIds);

    /**
     *
     * 获取用户的基础组件
     *
     * @param roleIds 用户角色集合
     * @return 基础组件集合
     */
    @Select(
            "select " +
                    "*, " +
                    "id sys_role_menu_id " +
            "from " +
                    "res_menu rm " +
            "INNER JOIN " +
                    "sys_role_menu srm " +
            "on " +
                    "rm.res_id = srm.res_id " +
            "where " +
                    "srm.role_id " +
            "in " +
                    "( ${roleIds} ) " +
            "and " +
                    "rm.deleted = 0 " +
            "and " +
                    "rm.is_basic = 1 "
    )
    List<SysMenu> getBatchBasicBy(@Param("roleIds") String roleIds);


    /**
     *
     * 获取用户角色的首页
     * 1. 存在多个角色，有不同的首页，则根据组件之间的优先级展示（默认：非菜单首页 > 菜单首页， 两个组件优先级一致，则随机展示）
     *
     * @param roleIds 角色 id 集合
     * @return 首页界面
     */
    @Select(
            "select " +
                    "* " +
            "from " +
                    "res_menu " +
            "where " +
                    "res_id = " +
            "(" +
              "select " +
                    "res_id " +
              "from " +
                    "sys_role_menu srm " +
              "where " +
                    "srm.res_priority = " +
                    "(" +
                        "select " +
                            "max(res_priority) " +
                        "from " +
                            "sys_role_menu" +
                    ")  " +
              "and " +
                    "srm.role_id " +
              "in " +
                    "('admin', 'head-depart') " +
              "and " +
                    "srm.is_home = 1" +
              " LIMIT 1" +
            ")"
    )
    SysMenu getHomeBy(@Param("roleIds") String roleIds);


    /**
     * @param roleId
     * @param permissionId
     * @return
     */
    @Insert("insert IGNORE into sys_role_action (id, role_id, res_id) values ( #{id}, #{roleId}, #{permissionId} )")
    boolean addPermissionForRole(@Param("id")String id, @Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Select("select count(*) from sys_role_action where res_id = #{permissionId} and role_id = #{roleId}")
    int isExistPermissionForRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
