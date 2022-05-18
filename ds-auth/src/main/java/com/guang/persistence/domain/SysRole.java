package com.guang.persistence.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * @author asus
 */
@Getter
@Setter
@TableName(value ="sys_role")
public class SysRole extends BaseVO {

    @TableId(value = "role_id", type = IdType.UUID)
    String roleId;

    @TableField(value = "role_name")
    String roleName;

    @TableField(value = "role_cn")
    String roleCn;

    @TableField(value = "role_pt_id")
    String rolePtId;

    @TableField(value = "role_desc")
    String roleDesc;

    @TableField(value = "deleted")
    boolean deleted;


    @TableField(value = "status")
    String status;

    @TableField(value = "create_time")
    LocalDateTime createTime;

    /**
     * 联系表 id （预留字段 便于增加接口的扩张性）
     */
//    String sysUserRoleId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("\"super\":").append(super.toString()).append(", ")
                .append("\"roleId\":").append(roleId)
                .append(", \"roleName\":").append(roleName)
                .append(", \"roleCn\":").append(roleCn)
                .append(", \"rolePtId\":").append(rolePtId)
                .append(", \"roleDesc\":").append(roleDesc)
                .append(", \"deleted\":").append(deleted)
                .append('}');
        return sb.toString();
    }
}
