package com.guang.provider.ao.program;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProjectUpdateAo {

    @NotNull(message = "项目 id 不能为空")
    private String programId;


    private String projectId;


    @NotNull(message = "课题名称不能为空")
    private String projectName;


    @NotNull(message = "课题类型不能为空")
    private String projectType;


    @NotNull(message = "课题难度不能为空")
    private String projectLevel;


    @NotNull(message = "申报人不能为空")
    private String projectDeclarer;

    @NotNull(message = "申报时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime projectDeclarerTime;


    private String projectNote;


//    @NotNull(message = "状态不能为空")
    private String status;
    

    private String projectDesc;


    private String sysId;

}
