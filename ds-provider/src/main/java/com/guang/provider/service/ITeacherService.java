package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.persistence.domain.SysTeacher;
import com.guang.provider.ao.user.TeacherDelAo;
import com.guang.provider.ao.user.TeacherQueryAo;
import com.guang.provider.ao.user.TeacherUpdateAo;
import com.guang.provider.vo.TeacherVo;

import java.io.File;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/13
 */
public interface ITeacherService {


    /**
     *
     * 获取教师列表
     *
     * @param queryAo 教师查询对象
     * @return
     */
    IPage<TeacherVo> getBatchTeacherBy(TeacherQueryAo queryAo);


    TeacherVo addTeacher(TeacherUpdateAo updateAo);


    boolean updateTeacher(TeacherUpdateAo updateAo);


    boolean delTeacher(TeacherDelAo delAo);


    boolean excelImport(File file, String programId);


    List<TeacherVo> getSearcherBy(TeacherQueryAo queryAo);




    List<SysTeacher> list(String programId);

}
