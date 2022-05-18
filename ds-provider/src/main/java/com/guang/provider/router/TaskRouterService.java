package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.count.CountQueryAo;
import com.guang.provider.ao.program.TaskDelAo;
import com.guang.provider.ao.program.TaskQueryAo;
import com.guang.provider.ao.program.TaskUpdateAo;
import com.guang.provider.vo.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/26
 */
public interface TaskRouterService {


    /**
     * @param queryAo
     * @return
     */
    @PostMapping(value = "/api/task/list")
    ResponseVO<IPage<TaskVo>> getTaskBatchBy(@RequestBody @Validated TaskQueryAo queryAo);


    @PostMapping(value = "/api/task/save")
    ResponseVO<TaskVo> addTask(@RequestBody TaskUpdateAo updateAo);


    @PostMapping(value = "/api/task/edit")
    ResponseVO<Boolean> updateTask(@RequestBody @Validated TaskUpdateAo updateAo);


    @PostMapping(value = "/api/task/del")
    ResponseVO<Boolean> delTask(@RequestBody @Validated TaskDelAo delAo);


    @PostMapping(value = "/api/task/one")
    ResponseVO<TaskVo> getOneTaskBy(@RequestBody @Validated TaskQueryAo queryAo);


    @PostMapping(value = "/api/task/numerical")
    ResponseVO<TaskStaticCountVo> countTask(@RequestBody CountQueryAo countQueryAo);



    @PostMapping(value = "/api/task/student")
    ResponseVO<IPage<StudentVo>> getTaskStudentStatus(@RequestBody TaskQueryAo queryAo);


    @PostMapping(value = "/api/task/teacher")
    ResponseVO<IPage<TeacherVo>> getTaskTeacherStatus(@RequestBody TaskQueryAo queryAo);

}
