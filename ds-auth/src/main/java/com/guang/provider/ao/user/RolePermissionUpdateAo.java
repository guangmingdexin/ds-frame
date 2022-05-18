package com.guang.provider.ao.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author guangmingdexin
 * @date 2022/5/18
 */
@Data
@AllArgsConstructor
public class RolePermissionUpdateAo {


    private String roleId;


    private String permissionId;


}
