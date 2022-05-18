package com.guang.provider.ao.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * @author guangmingdexin
 * @date 2022/5/13
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionUpdateAo {


    private String permissionId;

    private String permissionName;


    private String status;


    /**
     * 前端下拉框选择的操作权限
     */
    private List<String> labels;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PermissionUpdateAo that = (PermissionUpdateAo) o;
        return Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId);
    }
}
