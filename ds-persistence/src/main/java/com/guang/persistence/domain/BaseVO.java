package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author guangyong.deng
 * @date 2022-02-14 16:48
 */
@Getter
@Setter
@ToString
public class BaseVO implements Serializable {


    @TableField("created_by")
    private String createdBy;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_by")
    private String updatedBy;

    @TableField("updated_time")
    private Date updatedTime;
}
