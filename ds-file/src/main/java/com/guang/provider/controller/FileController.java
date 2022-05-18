package com.guang.provider.controller;

import com.common.core.exception.ResultCode;
import com.common.core.web.domain.ResponseVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guang.persistence.domain.SysFile;
import com.guang.provider.ao.FileQueryAo;
import com.guang.provider.common.core.FileType;
import com.guang.provider.common.util.CommonUtil;
import com.guang.provider.router.FileRouterService;
import com.guang.provider.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
@RestController
@CrossOrigin("*")
public class FileController implements FileRouterService {


    @Autowired
    FileService fileService;


    @Autowired
    ObjectMapper objectMapper;


    @Override
    public ResponseVO<String> getFilePath(FileQueryAo fileQueryAo) {
        // 1.只需要判断 fileId 存在即可
        if(fileQueryAo.getFileId() == null) {
            return ResponseVO.fail("empty-avatar");
        }
        return ResponseVO.success(fileService.getFilePath(fileQueryAo));
    }

    @Override
    public ResponseVO<Boolean> uploadFile(
             MultipartFile source,
             String fileParams) {
        if(source == null || source.isEmpty()) {
            return ResponseVO.fail("上传失败");
        }
        try {

            FileQueryAo fileQueryAo = objectMapper.readValue(fileParams, FileQueryAo.class);
            // 参数校验
            String type = fileQueryAo.getBusinessType();
            FileType fileType = FileType.getFileTypeBy(type);

            if(fileType != null && !fileType.getRange().contains(CommonUtil.getFileExtension(source.getOriginalFilename()))) {
                return ResponseVO.fail("不支持该类型文件");
            }

            SysFile sysFile = fileService.uploadFile(source, fileQueryAo);

            if(sysFile != null) {
                return ResponseVO.success(true);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



        return ResponseVO.fail("上传失败");
    }

    @Override
    public ResponseVO<List<SysFile>> uploadFiles(MultipartFile[] source, String fileParams) {

        try {
            List<SysFile> fileList = fileService.uploadFiles(source, objectMapper.readValue(fileParams, FileQueryAo.class));
            if(fileList != null && (!fileList.isEmpty())) {
                return ResponseVO.success(fileList);
            }else {
                return ResponseVO.fail(ResultCode.OPERATION_FAIL);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseVO.fail("上传出现错误");
    }

    @Override
    public ResponseVO<List<SysFile>> getBatchFilesBy(FileQueryAo fileQueryAo) {
        return ResponseVO.success(fileService.getBatchFileBy(fileQueryAo));
    }

    @Override
    public ResponseVO<Boolean> existFile(FileQueryAo fileQueryAo) {
        return null;
    }

    @Override
    public Resource downloadFile(FileQueryAo fileQueryAo) {
        // 参数校验

        return null;
    }

}
