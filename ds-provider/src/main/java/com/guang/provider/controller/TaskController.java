package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.exception.ResultCode;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.count.CountQueryAo;
import com.guang.provider.ao.program.TaskDelAo;
import com.guang.provider.ao.program.TaskQueryAo;
import com.guang.provider.ao.program.TaskUpdateAo;
import com.guang.provider.router.TaskRouterService;
import com.guang.provider.service.impl.StaticService;
import com.guang.provider.service.impl.TaskService;
import com.guang.provider.vo.StudentVo;
import com.guang.provider.vo.TaskStaticCountVo;
import com.guang.provider.vo.TaskVo;
import com.guang.provider.vo.TeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangmingdexin
 * @date 2022/4/26
 */
@RestController
@CrossOrigin("*")
public class TaskController implements TaskRouterService {

    @Autowired
    TaskService taskService;

    @Autowired
    StaticService staticService;


    @Override
    public ResponseVO<IPage<TaskVo>> getTaskBatchBy(TaskQueryAo queryAo) {
        return ResponseVO.success(taskService.getTaskBatchBy(queryAo));
    }

    @Override
    public ResponseVO<TaskVo> addTask(TaskUpdateAo updateAo) {

        TaskVo vo = taskService.addTask(updateAo);
        return vo != null  ? ResponseVO.success(vo) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }


    @Override
    public ResponseVO<Boolean> updateTask(TaskUpdateAo updateAo) {
        return ResponseVO.success(taskService.updateTask(updateAo));
    }

    @Override
    public ResponseVO<Boolean> delTask(TaskDelAo delAo) {
        return null;
    }

    @Override
    public ResponseVO<TaskVo> getOneTaskBy(TaskQueryAo queryAo) {
        TaskVo vo = taskService.getTaskOneBy(queryAo);
        return vo != null ? ResponseVO.success(vo) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<TaskStaticCountVo> countTask(CountQueryAo countQueryAo) {
        return ResponseVO.success(staticService.countStaticTask(countQueryAo));
    }

    @Override
    public ResponseVO<IPage<StudentVo>> getTaskStudentStatus(TaskQueryAo queryAo) {
        return ResponseVO.success(taskService.getTaskStudentBy(queryAo));
    }

    @Override
    public ResponseVO<IPage<TeacherVo>> getTaskTeacherStatus(TaskQueryAo queryAo) {
        return ResponseVO.success(taskService.getTaskTeacherBy(queryAo));
    }


}
