package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.program.ScoreQueryAo;
import com.guang.provider.vo.StudentVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author guangmingdexin
 * @date 2022/5/2
 */
public interface ScoreRouterService {

    @PostMapping(value = "/api/score/list")
    ResponseVO<IPage<StudentVo>> getBatchScoreBy(@RequestBody ScoreQueryAo queryAo);


    @PostMapping(value = "/api/reply-score/list")
    ResponseVO<IPage<StudentVo>> getBatchReplyScoreBy(@RequestBody ScoreQueryAo queryAo);


    @PostMapping(value = "/api/view-score/list")
    ResponseVO<IPage<StudentVo>> getBatchViewScoreBy(@RequestBody ScoreQueryAo queryAo);



}
