package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.program.ProgramQueryAo;
import com.guang.provider.ao.program.ProgramUpdateAo;
import com.guang.provider.vo.ProgramVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
public interface ProgramRouteService {


    /**
     *
     * 批量获取项目根据条件
     *
     * @param programQueryAo 项目查询对象
     * @return 项目列表
     */
    @PostMapping("/api/program/list")
    ResponseVO<IPage<ProgramVo>> getBatchProgramBy(@Validated @RequestBody ProgramQueryAo programQueryAo);


    /**
     *
     * 新增
     *
     * @param updateAo 更新对象
     * @return
     */
    @PostMapping("api/program/save")
    ResponseVO<ProgramVo> addProgram(@Validated @RequestBody ProgramUpdateAo updateAo);


    /**
     *
     * 更新
     *
     * @param updateAo 更新对象
     * @return
     */
    @PostMapping("/api/program/edit")
    ResponseVO<Boolean> updateProgram(@Validated @RequestBody ProgramUpdateAo updateAo);


    /**
     *
     * 删除
     *
     * @param queryAo
     * @return
     */
    @PostMapping("/api/program/del")
    ResponseVO<Boolean> delProgram(@Validated @RequestBody ProgramQueryAo queryAo);
}
