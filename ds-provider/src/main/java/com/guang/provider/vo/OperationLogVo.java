package com.guang.provider.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Getter
@Setter
@NoArgsConstructor
public class OperationLogVo {


    private String logId;


    private String logType;


    private String logName;


    private LocalDateTime logTime;


    private String logDesc;


    private String projectId;
}
