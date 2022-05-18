package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.sql.SqlUtil;
import com.common.core.sql.WrapPage;
import com.common.core.web.util.Util;
import com.guang.persistence.domain.SysStudent;
import com.guang.persistence.domain.SysTask;
import com.guang.persistence.domain.SysTaskUserAssociation;
import com.guang.persistence.domain.SysTeacher;
import com.guang.persistence.mapper.program.SysTaskMapper;
import com.guang.persistence.service.program.SysTaskService;
import com.guang.provider.ao.program.TaskDelAo;
import com.guang.provider.ao.program.TaskQueryAo;
import com.guang.provider.ao.program.TaskUpdateAo;
import com.guang.provider.ao.user.StudentQueryAo;
import com.guang.provider.ao.user.TeacherQueryAo;
import com.guang.provider.common.Status;
import com.guang.provider.common.TaskType;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.ITaskService;
import com.guang.provider.vo.StudentVo;
import com.guang.provider.vo.TaskVo;
import com.guang.provider.vo.TeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guangmingdexin
 * @date 2022/4/26
 */
@Service
public class TaskService implements ITaskService {

    @Autowired
    SysTaskService sysTaskService;

    @Autowired
    StudentService studentService;


    @Autowired
    TeacherService teacherService;

    @Autowired
    SysTaskMapper sysTaskMapper;


    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public IPage<TaskVo> getTaskBatchBy(TaskQueryAo queryAo) {

        LambdaQueryWrapper<SysTask> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(SysTask::getProgramId, queryAo.getProgramId())
                .eq(SysTask::getDeleted, 0);

        IPage<SysTask> page = sysTaskService.page(new Page<>(queryAo.getPage(), queryAo.getSize()), queryWrapper);

        IPage<TaskVo> convert = page.convert(this.convert::sysTaskConvertVo);

        return new WrapPage<>(convert);
    }

    @Override
    public TaskVo addTask(TaskUpdateAo updateAo) {
        //  有附件类型的参数，必须使用 form-data（当时为什么不使用两个接口？）
        //         统一封装更符合编程风格（其实两个接口更加简单，效率也应该更高？）
        // 1.根据任务类型确定后续流程
        SysTask sysTask = convert.taskAoConvertDo(updateAo);
        boolean r = sysTaskService.save(sysTask);

        if(TaskType.Student_Task_Type.getType().equals(sysTask.getTaskType())){

            List<String> stList = studentService.list(updateAo.getProgramId()).stream().map(SysStudent::getSysId).collect(Collectors.toList());
            extracted(sysTask, stList);

        }else if(TaskType.Teacher_Task_Type.getType().equals(sysTask.getTaskType())) {

            List<String> tcList = teacherService.list(updateAo.getProgramId()).stream().map(SysTeacher::getSysId).collect(Collectors.toList());
            extracted(sysTask, tcList);
        }else {
            throw new IllegalArgumentException("不支持的任务类型： " + sysTask.getTaskType());
        }


        if(!r) {
            return null;
        }
        return convert.sysTaskConvertVo(sysTask);

    }


    private void extracted(SysTask updateAo, List<String> idList) {
        List<SysTaskUserAssociation> associations = new ArrayList<>(idList.size());

        // 构造对象
        idList.forEach(sysId -> {
            associations.add(
                    new SysTaskUserAssociation(
                    Util.generatorUUID(),
                            updateAo.getTaskId(),
                            sysId,
                            Status.Error.getStatus(),
                            updateAo.getProgramId()
            ));
        });

        sysTaskMapper.addTaskUserAssociation(associations, SysTaskUserAssociation.class);
    }

    @Override
    public boolean updateTask(TaskUpdateAo updateAo) {

        LambdaUpdateWrapper<SysTask> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper.eq(SysTask::getDeleted, 0)
                .eq(SysTask::getTaskId, updateAo.getTaskId());

        return sysTaskService.update(convert.taskAoConvertDo(updateAo), updateWrapper);
    }

    @Override
    public boolean delTask(TaskDelAo delAo) {
        return false;
    }

    @Override
    public TaskVo getTaskOneBy(TaskQueryAo queryAo) {
        return convert.sysTaskConvertVo(sysTaskService.getById(queryAo.getTaskId()));
    }

    @Override
    public IPage<StudentVo> getTaskStudentBy(TaskQueryAo queryAo) {
        // 获取所有该任务的学生
        // 将所有学生的状态替换为 任务状态

        StudentQueryAo stQueryAo = new StudentQueryAo();

        stQueryAo.setProgramId(queryAo.getProgramId());
        stQueryAo.setPage(queryAo.getPage());
        stQueryAo.setSize(queryAo.getSize());

        IPage<StudentVo> page = studentService.getBatchStudentBy(stQueryAo);

        List<StudentVo> source = page.getRecords();
        List<String> status = sysTaskMapper.getTaskUserStatus(SqlUtil.inArrayToString(source.stream().map(StudentVo::getSysId).collect(Collectors.toList())),
                queryAo.getTaskId());

        if(status.isEmpty() || status.size() != source.size()) {
            return new WrapPage<>();
        }

        for (int i = 0; i < source.size(); i++) {
            source.get(i).setStatus(status.get(i));
        }

        return page;
    }

    @Override
    public IPage<TeacherVo> getTaskTeacherBy(TaskQueryAo queryAo) {

        TeacherQueryAo tcQueryAo = new TeacherQueryAo();

        tcQueryAo.setProgramId(queryAo.getProgramId());
        tcQueryAo.setPage(queryAo.getPage());
        tcQueryAo.setSize(queryAo.getSize());

        IPage<TeacherVo> page = teacherService.getBatchTeacherBy(tcQueryAo);

        List<TeacherVo> source = page.getRecords();
        List<String> status = sysTaskMapper.getTaskUserStatus(SqlUtil.inArrayToString(source.stream().map(TeacherVo::getSysId).collect(Collectors.toList())),
                queryAo.getTaskId());

        if(status.isEmpty() || status.size() != source.size()) {
            return new WrapPage<>();
        }

        for (int i = 0; i < source.size(); i++) {
            source.get(i).setStatus(status.get(i));
        }

        return page;
    }
}
