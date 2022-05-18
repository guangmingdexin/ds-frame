package com.guang.provider.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.sql.WrapPage;
import com.common.core.web.util.Util;
import com.guang.persistence.domain.SysStudent;
import com.guang.persistence.mapper.user.SysStudentMapper;
import com.guang.persistence.service.user.SysStudentService;
import com.guang.provider.ao.program.ProjectQueryAo;
import com.guang.provider.ao.user.StudentDelAo;
import com.guang.provider.ao.user.StudentQueryAo;
import com.guang.provider.ao.user.StudentScoreUpdateAo;
import com.guang.provider.ao.user.StudentUpdateAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IStudentService;
import com.guang.provider.vo.ProjectVo;
import com.guang.provider.vo.StudentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
@Service(value = "studentService")
@Slf4j
public class StudentService implements IStudentService {

    @Autowired
    SysStudentService sysStudentService;

    @Autowired
    SysStudentMapper sysStudentMapper;

    @Autowired
    ProjectService projectService;

    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public IPage<StudentVo> getBatchStudentBy(StudentQueryAo queryAo) {

        LambdaQueryWrapper<SysStudent> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(SysStudent::getProgramId, queryAo.getProgramId())
                        .eq(SysStudent::getDeleted, 0);

        // 条件查询
        queryWrapper.eq(!StringUtils.isEmpty(queryAo.getNo()), SysStudent::getNo, queryAo.getNo())
                .eq(!StringUtils.isEmpty(queryAo.getName()), SysStudent::getName, queryAo.getName())
                .eq(!StringUtils.isEmpty(queryAo.getDept()), SysStudent::getDept, queryAo.getDept())
                .eq(!StringUtils.isEmpty(queryAo.getMajor()), SysStudent::getMajor, queryAo.getMajor());

        queryWrapper.orderByAsc(SysStudent::getNo);

        IPage<SysStudent> page = sysStudentService.page(new Page<>(queryAo.getPage(), queryAo.getSize()), queryWrapper);

        IPage<StudentVo> convert = page.convert(this.convert::sysStudentCovertVo);

        convert.getRecords().forEach(v -> {

            ProjectQueryAo ao = new ProjectQueryAo();
            ao.setSysStudentId(v.getSysId());

            ProjectVo project = projectService.getOneProjectByStId(ao);

            if(project == null || StringUtils.isEmpty(project.getPjDeclarer())) {
                v.setTcName("无");
            }else {
                v.setTcName(project.getPjDeclarer());
            }
        });

        return new WrapPage<>(convert);
    }

    @Override
    public List<StudentVo> getBatchStudentNotLimitBy(StudentDelAo queryAo) {

        LambdaQueryWrapper<SysStudent> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysStudent::getDeleted, 0)
                .in(SysStudent::getSysId, queryAo.getSysIds());

        return convert.sysStudentConvertVos(sysStudentService.list(queryWrapper));
    }

    @Override
    public StudentVo addStudent(StudentUpdateAo updateAo) {


        SysStudent sysStudent = convert.studentAoConvertDo(updateAo);
        sysStudentService.save(sysStudent);

        return convert.sysStudentCovertVo(sysStudent);
    }

    @Override
    public Boolean excelImport(File file, String programId) {

        try {
            log.info("开始读取数据 -------- ");
            EasyExcel.read(new FileInputStream(file), StudentUpdateAo.class, new PageReadListener<StudentUpdateAo>(dataList -> {

                dataList.forEach(data -> {
                    String id = Util.generatorUUID();
                    data.setSysId(id);
                    data.setProgramId(programId);
//                    data.setDeleted(false);
                    log.info("读取一条数据： {}", data);
                });

                // # 有点问题， 批量插入之前先查询
                sysStudentMapper.insertBatchOrUpdate(convert.studentAosCovertDos(dataList), SysStudent.class);


            })).sheet().doRead();;

            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Boolean updateStudent(StudentUpdateAo updateAo) {

        LambdaUpdateWrapper<SysStudent> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper
                .eq(SysStudent::getSysId, updateAo.getSysId())
                .eq(SysStudent::getDeleted, 0);


        SysStudent sysStudent = convert.studentAoConvertDo(updateAo);

        return sysStudentService.update(sysStudent, updateWrapper);
    }


    @Override
    public Boolean delStudent(StudentDelAo updateAo) {


        // TODO: 如果使用虚拟删除，就有可能面临唯一索引重复的问题，后续待解决

        LambdaUpdateWrapper<SysStudent> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper.eq(SysStudent::getDeleted, 0)
                        .in(SysStudent::getSysId, updateAo.getSysIds())
                                .set(SysStudent::getDeleted, 1);


//        return sysStudentService.update(updateWrapper);

        return sysStudentService.removeByIds(updateAo.getSysIds());
    }


    /* ----------------    内部服务调用方法，减少耦合       ------------ */
    @Override
    public List<SysStudent> list(String programId) {

        LambdaQueryWrapper<SysStudent> stQuery = Wrappers.lambdaQuery();
        stQuery.eq(SysStudent::getProgramId, programId).eq(SysStudent::getDeleted, 0);

        return sysStudentService.list(stQuery);
    }
}
