package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.provider.ao.program.ProgramQueryAo;
import com.guang.provider.ao.program.ProgramUpdateAo;
import com.guang.provider.vo.ProgramVo;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
public interface IProgramService {


    /**
     *
     * 获取项目列表
     *
     * @param programQueryAo 项目查询对象
     * @return 项目列表
     */
    IPage<ProgramVo> getBatchProgramBy(ProgramQueryAo programQueryAo);


    /**
     *
     * 新增项目
     *
     * @param updateAo 修改对象
     * @return vo
     */
    ProgramVo addProgram(ProgramUpdateAo updateAo);


    /**
     *
     * 修改项目
     *
     * @param updateAo 修改对象
     * @return boolean
     */
    boolean updateProgram(ProgramUpdateAo updateAo);


    /**
     *
     * 删除项目
     *
     * @param queryAo 查询对象
     * @return boolean
     */
    boolean delProgram(ProgramQueryAo queryAo);

}
