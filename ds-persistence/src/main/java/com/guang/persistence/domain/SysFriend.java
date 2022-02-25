package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author guangyong.deng
 * @date 2022-02-24 15:16
 */
@Getter
@Setter
@ToString
public class SysFriend extends BaseVO {

    /**
     *  关系表 id
     */
    @TableId(value = "id")
    private String id;


    /**
     * 好友 id
     */
    @TableField(exist = false)
    private SysUser friend;

}
