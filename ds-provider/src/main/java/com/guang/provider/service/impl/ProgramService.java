package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guang.persistence.domain.SysProgram;
import com.guang.persistence.service.program.SysProgramService;
import com.guang.provider.ao.program.ProgramQueryAo;
import com.guang.provider.ao.program.ProgramUpdateAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IProgramService;
import com.guang.provider.vo.ProgramVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@Service(value = "programService")
@SuppressWarnings("unchecked")
public class ProgramService implements IProgramService {

    @Autowired
    SysProgramService sysProgramService;

    /**
     * 是否为线程安全？
     */
    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public IPage<ProgramVo> getBatchProgramBy(ProgramQueryAo programQueryAo) {
       // 1.根据条件生成条件 sql

        LambdaQueryWrapper<SysProgram> programQuery = Wrappers.lambdaQuery();

        // 条件查询类似
        programQuery.eq(programQueryAo.getId() != null, SysProgram::getSysId, programQueryAo.getId())
                .eq(programQueryAo.getStatus() != null, SysProgram::getStatus, programQueryAo.getStatus());

        String query = programQueryAo.getQuery();
        if(!StringUtils.isEmpty(query)) {

            System.out.println("模糊搜索条件： " + query);

            programQuery.and(
                    wrapper -> wrapper.like(SysProgram::getProgramTitle, query)
                            .or()
                            .like(SysProgram::getProgramDesc, query)
                            .or()
                            .like(SysProgram::getProgramOwner, query)).orderByDesc(SysProgram::getStartAt);
        }

        IPage page = sysProgramService.page(new Page<>(programQueryAo.getPage(),
                programQueryAo.getSize()), programQuery);

        List<SysProgram> records = page.getRecords();

        List<ProgramVo> voList = convert.sysProgramConvertVos(records);
        page.setRecords(voList);

        return page;
    }

    @Override
    public ProgramVo addProgram(ProgramUpdateAo updateAo) {

        int nextInt = new Random().nextInt(4);

        // 先将 Ao -> Do
        SysProgram sysProgram = CommonConvert.INSTANCE.programAoConvertDo(updateAo);
        sysProgram.setAvatar("default_" + (nextInt + 1));
        sysProgram.setStatus("process");

        if(sysProgramService.save(sysProgram)) {
            return convert.sysProgramConvertVo(sysProgram);
        }

        return null;
    }

    @Override
    public boolean updateProgram(ProgramUpdateAo updateAo) {

        LambdaUpdateWrapper<SysProgram> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper.eq(SysProgram::getProgramId, updateAo.getProgramId())
                .eq(SysProgram::getDeleted, 0);

        return sysProgramService.update(convert.programAoConvertDo(updateAo), updateWrapper);
    }

    @Override
    public boolean delProgram(ProgramQueryAo queryAo) {

        return sysProgramService.removeById(queryAo.getProgramId());

    }
}
