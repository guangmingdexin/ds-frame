package com.guang.provider.ao.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OperationLogUpdateAo {

    private String logId;


    private String logType;


    private String logName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime logTime;

    private String logDesc;

    private String projectId;


    private String logNote;
}
