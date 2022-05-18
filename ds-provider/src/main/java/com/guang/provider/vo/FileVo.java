package com.guang.provider.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author guangmingdexin
 * @date 2022/4/23
 */
@Getter
@Setter
@ToString
public class FileVo {

    private String fileId;


    private String fileName;


    private String fileType;


    private Long fileSize;


    private String filePath;

    private Boolean deleted;


    private String businessType;


    private String sysId;


    private String status;


    private String serviceId;
}
