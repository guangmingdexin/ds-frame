package com.guang.provider.vo;

import com.guang.provider.vo.entity.ActionEntitySet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
@Getter
@Setter
public class PermissionVo {

    String permissionId;

    String permissionName;

    String type;

    String path;

    String actions;

    String component;

    String meta;

    String redirect;

    String status;


     List<ActionEntitySet> actionEntitySet;

    /**
     * 作为前端下拉框的标签值（不能使用对象）
     *
     */
    List<String> labels;


    /**
     * 新增角色时，默认不选中权限
     */
    boolean checked;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PermissionVo that = (PermissionVo) o;
        return Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId);
    }
}
