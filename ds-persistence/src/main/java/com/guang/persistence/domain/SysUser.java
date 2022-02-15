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
    private String sysId;

    @TableField(value = "sys_name")
    private String sysName;

    @TableField(value = "sys_pwd")
    private String sysPwd;

    @TableField(value = "sys_vip")
    private Integer sysVip;

    @TableField(value = "sys_score")
    private Integer sysScore;
}
