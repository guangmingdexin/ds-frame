package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.exception.ResultCode;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.program.ProgramQueryAo;
import com.guang.provider.ao.program.ProgramUpdateAo;
import com.guang.provider.router.ProgramRouteService;
import com.guang.provider.service.impl.ProgramService;
import com.guang.provider.vo.ProgramVo;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@RestController
@CrossOrigin("*")
@SuppressWarnings("unchecked")
public class ProgramController implements ProgramRouteService {


    @Autowired
    ProgramService programService;

    @Override
    public ResponseVO<IPage<ProgramVo>> getBatchProgramBy(ProgramQueryAo programQueryAo) {
        System.out.println("ProgramQueryAo: " + programQueryAo);
        return ResponseVO.success(programService.getBatchProgramBy(programQueryAo));
    }

    @Override
    public ResponseVO<ProgramVo> addProgram(ProgramUpdateAo updateAo) {

        ProgramVo vo = programService.addProgram(updateAo);

        if(vo != null) {
            return ResponseVO.success(vo);
        }

        return ResponseVO.fail("新增失败");
    }

    @Override
    public ResponseVO<Boolean> updateProgram(ProgramUpdateAo updateAo) {

        // 单独对 项目 id 做检查
        if(StringUtils.isEmpty(updateAo.getProgramId())) {
            return ResponseVO.fail("缺少参数 programId 为空");
        }

        return programService.updateProgram(updateAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> delProgram(ProgramQueryAo queryAo) {

        if(StringUtils.isEmpty(queryAo.getProgramId())) {
            return ResponseVO.fail("缺少参数 programId 为空");
        }
        return programService.delProgram(queryAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }


}
