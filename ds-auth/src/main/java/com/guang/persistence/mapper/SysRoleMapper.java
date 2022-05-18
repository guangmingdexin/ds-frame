package com.guang.persistence.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guang.persistence.domain.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author asus
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {


    /**
     *
     * 更加 用户-角色关联表获取所有角色
     *
     * @param sysId 用户 id
     * @return 所有角色
     */
    @Select(
            "select " +
                "*, " +
                "id sys_user_role_id " +
            "from " +
                    "sys_user su " +
            "INNER JOIN " +
                    "sys_user_role sur " +
            "on " +
                "su.sys_id = sur.user_id " +
            "where " +
                "su.sys_id = #{sysId} " +
            "and " +
                "deleted = 0; "
    )
    List<SysRole> getRolesBy(String sysId);


    /**
     *
     * 删除角色-权限联系表数据
     *
     * @param roleId 角色 id
     * @param permissionId 权限 id
     * @return boolean
     */
    @Delete("delete from sys_role_action where res_id = #{permissionId} and role_id = #{roleId}")
    boolean deleteRolePermission(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
