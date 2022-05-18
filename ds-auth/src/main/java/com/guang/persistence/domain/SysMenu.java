package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;

import java.util.Objects;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
@Getter
@TableName(value = "res_menu")
public class SysMenu extends BaseVO {

    @TableField(value = "res_id")
    String resId;

    @TableField(value = "res_name")
    String resName;

    @TableField(value = "res_path")
    String resPath;

    @TableField(value = "res_parent_id")
    String resParentId;

    @TableField(value = "res_component")
    String resComponent;

    @TableField(value = "res_meta")
    String resMeta;

    @TableField(value = "res_redirect")
    String resRedirect;

    @TableField(value = "deleted")
    Boolean deleted;

    @TableField(value = "res_type")
    String resType;


    @TableField(value = "res_vue_path")
    String resVuePath;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysMenu sysMenu = (SysMenu) o;
        return Objects.equals(resId, sysMenu.resId) && Objects.equals(deleted, sysMenu.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, deleted);
    }
}
