package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.*;
import com.guang.provider.vo.TeacherVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
public interface TeacherRouterService {

    /**
     *
     * 学生列表
     *
     * @param queryAo 查询对象
     * @return vo
     */
    @PostMapping("/api/teacher/list")
    ResponseVO<IPage<TeacherVo>> getBatchTeacherBy(@Validated @RequestBody TeacherQueryAo queryAo);

    /**
     *
     * 增加
     *
     * @param updateAo ao
     * @return vo
     */
    @PostMapping("/api/teacher/save")
    ResponseVO<TeacherVo> addTeacher(@Validated @RequestBody TeacherUpdateAo updateAo);

    /**
     * @param updateAo
     * @return
     */
    @PostMapping("/api/teacher/update")
    ResponseVO<Boolean> updateTeacher(@Validated @RequestBody TeacherUpdateAo updateAo);


    /**
     * @param delAo
     * @return
     */
    @PostMapping("/api/teacher/del")
    ResponseVO<Boolean> delTeacher(@Validated @RequestBody TeacherDelAo delAo);



    /**
     *
     * 导入
     *
     * @param file 文件
     * @param programId 项目 id
     * @return boolean
     */
    @PostMapping("/api/teacher/import")
    ResponseVO<Boolean> excelImport(@RequestParam("file") MultipartFile file,
                                    @RequestParam("programId") String programId);


    /**
     *
     * 模糊搜索提示
     *
     * @param queryAo 查询对象
     * @return
     */
    @PostMapping(value = "api/teacher/searcher")
    ResponseVO<List<TeacherVo>> getSearchInfo(@RequestBody TeacherQueryAo queryAo);
}
