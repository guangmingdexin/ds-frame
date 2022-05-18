package com.guang.provider.ao.process;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author guangmingdexin
 * @date 2022/4/24
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class ProcessProjectUpdateAo {


    private String processId;


    private String processName;


    private String processDesc;


    private String programId;


    private String sysId;


    private Boolean isMust;


    private String leader;


    private String nextProcessId;


    private String prevProcessId;

}
