package com.guang.persistence.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("ds_file")
public class SysFile {

    @TableId(value = "file_id", type = IdType.UUID)
    private String fileId;

    @TableField(value = "file_name")
    private String fileName;

    @TableField(value = "file_type")
    private String fileType;

    @TableField(value = "file_size")
    private Long fileSize;

    @TableField(value = "file_path")
    private String filePath;

    private Boolean deleted;

    @TableField(value = "bus_type")
    private String businessType;

    @TableField(value = "sys_id")
    private String sysId;


    @TableField(value = "service_id")
    private String serviceId;


    private String status;
}
