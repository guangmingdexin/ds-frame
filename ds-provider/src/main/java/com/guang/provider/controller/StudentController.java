package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.exception.ResultCode;
import com.common.core.file.FileTools;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.user.StudentDelAo;
import com.guang.provider.ao.user.StudentQueryAo;
import com.guang.provider.ao.user.StudentUpdateAo;
import com.guang.provider.router.StudentRouterService;
import com.guang.provider.service.impl.StudentService;
import com.guang.provider.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
@RestController
@CrossOrigin("*")
public class StudentController implements StudentRouterService {


    @Autowired
    StudentService studentService;


    @Override
    public ResponseVO<IPage<StudentVo>> getBatchStudentBy(StudentQueryAo queryAo) {

        // 1.参数校验
        if(StringUtils.isEmpty(queryAo.getProgramId())) {
            return ResponseVO.fail("缺少参数 programId");
        }


        return ResponseVO.success(studentService.getBatchStudentBy(queryAo));
    }

    @Override
    public ResponseVO<StudentVo> addStudent(StudentUpdateAo updateAo) {

        StudentVo vo = studentService.addStudent(updateAo);
        return vo != null ? ResponseVO.success(vo) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> updateStudent(StudentUpdateAo updateAo) {
        return studentService.updateStudent(updateAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> delStudent(StudentDelAo updateAo) {

        return studentService.delStudent(updateAo) ? ResponseVO.success(true) : ResponseVO.fail(ResultCode.OPERATION_FAIL);
    }

    @Override
    public ResponseVO<Boolean> excelImport(MultipartFile file, String programId) {

        try {

            if(file.isEmpty()) {
                return ResponseVO.fail("文件不能为空");
            }

            String extension = FileTools.getFileExtension(file.getOriginalFilename());
            File temp = File.createTempFile(
                    UUID.randomUUID().toString().substring(0, 8), "." + extension);

            file.transferTo(temp);

            if(!FileTools.check(temp, true, "xls", "xlsx")) {
                return ResponseVO.fail("不支持的文件类型");
            }


            Boolean b = studentService.excelImport(temp, programId);

            return b ? ResponseVO.success(true) : ResponseVO.fail("上传失败");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseVO.fail("上传失败");
    }
}
