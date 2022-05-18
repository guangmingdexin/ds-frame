package com.guang.provider.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author guangmingdexin
 * @date 2022/5/2
 */
@Getter
@AllArgsConstructor
public enum ScoreType {

    /**
     * 分数类型 指导，评阅，答辩
     */
    Guide_Score("guide", 0.35),
    View_Score("view", 0.20),
    Reply_Score("reply", 0.45);


    private String type;

    private double ratio;
}
