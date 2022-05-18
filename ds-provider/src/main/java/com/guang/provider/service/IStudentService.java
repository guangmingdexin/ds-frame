package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.persistence.domain.SysStudent;
import com.guang.provider.ao.user.StudentDelAo;
import com.guang.provider.ao.user.StudentQueryAo;
import com.guang.provider.ao.user.StudentScoreUpdateAo;
import com.guang.provider.ao.user.StudentUpdateAo;
import com.guang.provider.vo.StudentVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
public interface IStudentService {


    /**
     *
     * 根据查询条件获取学生
     *
     * @param queryAo 查询对象
     * @return vo
     */
    IPage<StudentVo> getBatchStudentBy(StudentQueryAo queryAo);


    /**
     *
     * 根据 id 批量查询
     *
     * @param queryAo
     * @return
     */
    List<StudentVo> getBatchStudentNotLimitBy(StudentDelAo queryAo);


    /**
     *
     * 更新学生信息
     *
     * @param updateAo 更新对象
     * @return vo
     */
    StudentVo addStudent(StudentUpdateAo updateAo);


    /**
     *
     * 解析 excel
     *
     * @param file excel
     * @param programId 项目 id
     *
     * @return boolean
     */
    Boolean excelImport(File file, String programId);



    Boolean updateStudent(StudentUpdateAo updateAo);


    Boolean delStudent(StudentDelAo updateAo);



    /* --------------------- */

    List<SysStudent> list(String programId);
}
