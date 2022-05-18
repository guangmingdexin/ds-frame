package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guang.persistence.domain.SysTeacher;
import com.guang.persistence.service.user.SysTeacherService;
import com.common.core.sql.WrapPage;
import com.guang.provider.ao.user.TeacherDelAo;
import com.guang.provider.ao.user.TeacherQueryAo;
import com.guang.provider.ao.user.TeacherUpdateAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.ITeacherService;
import com.guang.provider.vo.TeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guangmingdexin
 * @date 2022/4/18
 */
@Service
public class TeacherService implements ITeacherService {


    @Autowired
    SysTeacherService sysTeacherService;

    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public IPage<TeacherVo> getBatchTeacherBy(TeacherQueryAo queryAo) {

        LambdaQueryWrapper<SysTeacher> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(SysTeacher::getProgramId, queryAo.getProgramId())
                .eq(SysTeacher::getDeleted, 0);

        // 条件查询
        queryWrapper.eq(!StringUtils.isEmpty(queryAo.getNo()), SysTeacher::getNo, queryAo.getNo())
                .eq(!StringUtils.isEmpty(queryAo.getName()), SysTeacher::getName, queryAo.getName())
                .eq(!StringUtils.isEmpty(queryAo.getDept()), SysTeacher::getDept, queryAo.getDept())
                .eq(!StringUtils.isEmpty(queryAo.getJob()), SysTeacher::getJob, queryAo.getJob());

        queryWrapper.orderByAsc(SysTeacher::getNo);

        IPage<SysTeacher> page = sysTeacherService.page(new Page<>(queryAo.getPage(), queryAo.getSize()), queryWrapper);

        IPage<TeacherVo> convert = page.convert(this.convert::sysTeacherCovertVo);
        return new WrapPage<>(convert);
    }

    @Override
    public TeacherVo addTeacher(TeacherUpdateAo updateAo) {
        SysTeacher sysTeacher = convert.teacherAoConvertDo(updateAo);
        sysTeacherService.save(sysTeacher);
        return convert.sysTeacherCovertVo(sysTeacher);
    }

    @Override
    public boolean updateTeacher(TeacherUpdateAo updateAo) {
        LambdaUpdateWrapper<SysTeacher> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper
                .eq(SysTeacher::getSysId, updateAo.getSysId())
                .eq(SysTeacher::getDeleted, 0);


        SysTeacher sysTeacher = convert.teacherAoConvertDo(updateAo);

        return sysTeacherService.update(sysTeacher, updateWrapper);
    }

    @Override
    public boolean delTeacher(TeacherDelAo delAo) {
        LambdaUpdateWrapper<SysTeacher> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper.eq(SysTeacher::getDeleted, 0)
                .in(SysTeacher::getSysId, delAo.getSysIds())
                .set(SysTeacher::getDeleted, 1);

//        return sysTeacherService.update(updateWrapper);
        return sysTeacherService.removeByIds(delAo.getSysIds());
    }

    @Override
    public boolean excelImport(File file, String programId) {
        return false;
    }

    @Override
    public List<TeacherVo> getSearcherBy(TeacherQueryAo queryAo) {

        // 只能根据名字进行模糊匹配（不适用进行大数据的匹配）
        LambdaQueryWrapper<SysTeacher> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.like(SysTeacher::getName, queryAo.getContent());

        return sysTeacherService.list(queryWrapper).stream().map(convert::sysTeacherCovertVo).collect(Collectors.toList());
    }

    @Override
    public List<SysTeacher> list(String programId) {

        LambdaQueryWrapper<SysTeacher> tcQuery = Wrappers.lambdaQuery();
        tcQuery.eq(SysTeacher::getProgramId, programId).eq(SysTeacher::getDeleted, 0);

        return sysTeacherService.list(tcQuery);
    }
}
