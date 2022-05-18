package com.guang.provider.service;

import com.guang.provider.ao.user.RolePermissionUpdateAo;
import com.guang.provider.ao.user.RoleUpdateAo;
import com.guang.provider.ao.user.UserQueryAo;
import com.guang.provider.vo.RoleVo;

import java.util.List;

/**
 * @author asus
 */
public interface IRoleService {


    /**
     *
     * 用户角色信息，权限信息
     *
     * @param sysId
     * @return
     */
     List<RoleVo> getRolesBy(String sysId);


    /**
     * @param queryAo 查询对象
     * @return
     */
     List<RoleVo> getBatchRoleBy(UserQueryAo queryAo);


     boolean updateRoleBy(RoleUpdateAo updateAo);


     boolean deleteRolePermission(RolePermissionUpdateAo updateAo);


     String addRole(RoleUpdateAo updateAo);
}
