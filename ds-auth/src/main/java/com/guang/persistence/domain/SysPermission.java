package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 *
 * 资源-权限表
 *
 * @author guangmingdexin
 */
@Getter
@Setter
@TableName(value ="res_action")
public class SysPermission extends BaseVO {

    @TableId(value = "res_id")
    String resId;

    @TableField(value = "res_name")
    String resName;

    @TableField(value = "res_type")
    String resType;

    @TableField(value = "res_path")
    String resPath;

    @TableField(value = "res_actions")
    String resActions;

    @TableField(value = "deleted")
    Boolean deleted;

    @TableField(value = "status")
    String status;

    /**
     * 联系表 id 便于后续处理
     */
//    String sysRoleActionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysPermission that = (SysPermission) o;
        return Objects.equals(resId, that.resId) && Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, deleted);
    }
}
