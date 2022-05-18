package com.guang.provider.ao.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class FileQueryAo {


//    @NotNull(message = "文件 id 不能为空")
    private String fileId;

    private List<String> fileIds;


    private String fileName;

    /**
     * 用户 id
     */
    private String id;


    /**
     * 业务类型
     */
    private String businessType;


    private String serviceId;


    private List<String> serviceIds;
}
