package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.StudentDelAo;
import com.guang.provider.ao.user.StudentQueryAo;
import com.guang.provider.ao.user.StudentUpdateAo;
import com.guang.provider.ao.user.TeacherQueryAo;
import com.guang.provider.vo.StudentVo;
import com.guang.provider.vo.TeacherVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
public interface StudentRouterService {


    /**
     *
     * 学生列表
     *
     * @param queryAo 查询对象
     * @return vo
     */
    @PostMapping("/api/student/list")
    ResponseVO<IPage<StudentVo>> getBatchStudentBy(@Validated @RequestBody StudentQueryAo queryAo);

    /**
     * @param updateAo
     * @return
     */
    @PostMapping("/api/student/save")
    ResponseVO<StudentVo> addStudent(@Validated @RequestBody StudentUpdateAo updateAo);

    /**
     * @param updateAo
     * @return
     */
    @PostMapping("/api/student/update")
    ResponseVO<Boolean> updateStudent(@Validated @RequestBody StudentUpdateAo updateAo);


    /**
     * @param updateAo
     * @return
     */
    @PostMapping("/api/student/del")
    ResponseVO<Boolean> delStudent(@Validated @RequestBody StudentDelAo updateAo);



    /**
     *
     * 导入
     *
     * @param file 文件
     * @param programId 项目 id
     * @return boolean
     */
    @PostMapping("/api/student/import")
    ResponseVO<Boolean> excelImport(@RequestParam("file") MultipartFile file,
                                    @RequestParam("programId") String programId);

}
