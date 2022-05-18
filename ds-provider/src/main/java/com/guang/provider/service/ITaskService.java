package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.provider.ao.program.TaskDelAo;
import com.guang.provider.ao.program.TaskQueryAo;
import com.guang.provider.ao.program.TaskUpdateAo;
import com.guang.provider.vo.StudentVo;
import com.guang.provider.vo.TaskVo;
import com.guang.provider.vo.TeacherVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/26
 */
public interface ITaskService {


    IPage<TaskVo> getTaskBatchBy(TaskQueryAo queryAo);


    TaskVo addTask(TaskUpdateAo updateAo);


    boolean updateTask(TaskUpdateAo updateAo);


    boolean delTask(TaskDelAo delAo);


    TaskVo getTaskOneBy(TaskQueryAo queryAo);


    IPage<StudentVo> getTaskStudentBy(TaskQueryAo queryAo);


    IPage<TeacherVo> getTaskTeacherBy(TaskQueryAo queryAo);
}
