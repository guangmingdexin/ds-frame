package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.provider.ao.program.ScoreQueryAo;
import com.guang.provider.vo.StudentVo;

/**
 * @author guangmingdexin
 * @date 2022/5/2
 */
public interface IScoreService {


    /**
     * @param queryAo
     * @return vo
     */
    IPage<StudentVo> getBatchScoreBy(ScoreQueryAo queryAo);


    /**
     *
     *
     * @param queryAo ao
     * @return
     */
    IPage<StudentVo> getBatchReplyScoreBy(ScoreQueryAo queryAo);


    /**
     *
     * 判断是否为答辩组长，如果为答辩组长，则自动为该组评阅教师
     *
     * @param queryAo
     * @return
     */
    IPage<StudentVo> getBatchViewScoreBy(ScoreQueryAo queryAo);
}
