package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.program.ScoreQueryAo;
import com.guang.provider.router.ScoreRouterService;
import com.guang.provider.service.impl.ScoreService;
import com.guang.provider.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangmingdexin
 * @date 2022/5/2
 */
@RestController
@CrossOrigin("*")
public class ScoreController implements ScoreRouterService {

    @Autowired
    ScoreService scoreService;

    @Override
    public ResponseVO<IPage<StudentVo>> getBatchScoreBy(ScoreQueryAo queryAo) {
        return ResponseVO.success(scoreService.getBatchScoreBy(queryAo));
    }

    @Override
    public ResponseVO<IPage<StudentVo>> getBatchReplyScoreBy(ScoreQueryAo queryAo) {
        return ResponseVO.success(scoreService.getBatchReplyScoreBy(queryAo));
    }

    @Override
    public ResponseVO<IPage<StudentVo>> getBatchViewScoreBy(ScoreQueryAo queryAo) {
        return ResponseVO.success(scoreService.getBatchViewScoreBy(queryAo));
    }


}
