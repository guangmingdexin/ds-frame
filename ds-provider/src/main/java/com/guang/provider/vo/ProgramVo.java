package com.guang.provider.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guang.provider.vo.entity.Progress;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@Getter
@Setter
public class ProgramVo {

    private String programId;

    private String title;

    private String avatar;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endAt;

    private Progress progress;

    private String owner;

    private String status;

}
