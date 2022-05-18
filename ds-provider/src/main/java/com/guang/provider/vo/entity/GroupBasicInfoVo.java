package com.guang.provider.vo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author guangmingdexin
 * @date 2022/4/30
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GroupBasicInfoVo {


    private String dept;


    private String major;


    private String grade;


    private Integer total;


    /**
     * 未能参加人数
     */
    private Integer notParticipate;
}
