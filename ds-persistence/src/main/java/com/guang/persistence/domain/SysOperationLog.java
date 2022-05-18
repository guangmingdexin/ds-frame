package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Getter
@Setter
@TableName(value = "ds_log")
@ToString
@Accessors(chain = true)
public class SysOperationLog {

    @TableId(value = "log_id", type = IdType.UUID)
    private String logId;


    @TableField(value = "log_type")
    private String logType;

    /**
     * 直接通过 name(module) 来区别不同业务日志，以及查询
     */
    @TableField(value = "log_name")
    private String logName;


    @TableField(value = "log_time")
    private LocalDateTime logTime;


    @TableField(value = "log_desc")
    private String logDesc;


    @TableField(value = "log_note")
    private String logNote;


    @TableField(value = "deleted")
    private Boolean deleted;


    @TableField(value = "pj_id")
    private String projectId;
}
