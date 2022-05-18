package com.guang.provider.ao.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author guangmingdexin
 * @date 2022/5/5
 */
@Getter
@Setter
@NoArgsConstructor
public class UserPermissionUpdateAo {

    @NotNull(message = "roleId 不能为空")
    private String roleId;


    @NotNull(message = "permissionId 不能为空")
    private String permissionId;
}
