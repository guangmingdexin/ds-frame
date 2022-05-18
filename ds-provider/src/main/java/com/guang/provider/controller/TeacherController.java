package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.exception.ResultCode;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.*;
import com.guang.provider.router.StudentRouterService;
import com.guang.provider.router.TeacherRouterService;
import com.guang.provider.service.impl.TeacherService;
import com.guang.provider.vo.StudentVo;
import com.guang.provider.vo.TeacherVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
@RestController
@CrossOrigin("*")
@Slf4j
@SuppressWarnings("unchecked")
public class TeacherController implements TeacherRouterService {

    @Autowired
    TeacherService teacherService;

    @Override
    public ResponseVO<IPage<TeacherVo>> getBatchTeacherBy(TeacherQueryAo queryAo) {

        log.info("调用教授查询接口");

        // 1.参数校验
        if(StringUtils.isEmpty(queryAo.getProgramId())) {
            return ResponseVO.fail("缺少参数 programId");
        }

        return ResponseVO.success(teacherService.getBatchTeacherBy(queryAo));
    }

    @Override
    public ResponseVO<TeacherVo> addTeacher(TeacherUpdateAo updateAo) {
        TeacherVo vo = teacherService.addTeacher(updateAo);
        return vo != null ? ResponseVO.success(vo) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> updateTeacher(TeacherUpdateAo updateAo) {
        return teacherService.updateTeacher(updateAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> delTeacher(TeacherDelAo delAo) {
        return teacherService.delTeacher(delAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> excelImport(MultipartFile file, String programId) {
        return null;
    }

    @Override
    public ResponseVO<List<TeacherVo>> getSearchInfo(TeacherQueryAo queryAo) {

        if(StringUtils.isEmpty(queryAo.getContent())) {
            return ResponseVO.success(null);
        }

        return ResponseVO.success(teacherService.getSearcherBy(queryAo));
    }
}
