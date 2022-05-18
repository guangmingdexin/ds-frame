package com.guang.provider.ao.program;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.guang.provider.vo.entity.Progress;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/14
 */
@Getter
@NoArgsConstructor
public class ProgramUpdateAo {


    /**
     * 为了新增和修改复用，即这里单独校验 id
     */
    private String programId;

    @NotNull(message = "参数错误，title 不能为空")
    private String title;

    @NotNull(message = "参数错误，description 不能为空")
    private String description;

    @NotNull(message = "参数错误，owner 不能为空")
    private String owner;


    private Progress progress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startAt;

    @NotNull(message = "参数错误，endAt 不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endAt;

    private String status;

    @NotNull(message = "用户 id 不能为空")
    private String id;


//    public static void main(String[] args) throws JsonProcessingException {
//
//        ProjectUpdateAo ao = new ProjectUpdateAo();
//
//        ao.setProgramId("10674c9128581ef82c0e036724fe9713");
//        ao.setProjectDesc("超级人工管理系统");
//        ao.setStatus("审核未通过");
//        ao.setProjectDeclarerTime(LocalDateTime.now());
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        System.out.println(mapper.writeValueAsString(ao));
//
//
//
//    }

}
