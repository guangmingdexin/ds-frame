package com.guang.persistence.domain;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;

/**
 *
 * 系统用户实体
 *
 * @author guangyong.deng
 * @date 2022-02-14 16:45
 */
@Getter
@TableName(value ="sys_user")
public class SysUser extends BaseVO {

    @TableId(type = IdType.UUID, value = "sys_id")
     String sysId;

    @TableField(value = "sys_account")
     String sysAccount;

    @TableField(value = "sys_username")
    String sysUsername;

    @TableField(value = "sys_pwd")
     String sysPwd;

    @TableField(value = "sys_avatar_id")
     String sysAvatarId;

    @TableField(value = "deleted")
     Boolean deleted;
}
