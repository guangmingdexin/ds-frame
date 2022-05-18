package com.guang.provider.ao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FileQueryAo {


//    @NotNull(message = "文件 id 不能为空")
    private String fileId;


    private String fileName;

    /**
     * 用户 id
     */
    private String id;


    /**
     * 业务类型
     */
    @JsonProperty(value = "type")
    private String businessType;

    /**
     * 是否覆盖
     */
    private Boolean cover;


    private List<String> fileIds;


    private String serviceId;


    private List<String> serviceIds;
}
