package com.guang.provider.controller;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.file.FileQueryAo;
import com.guang.provider.feign.remote.service.FileRemoteService;
import com.guang.provider.router.FileRouterService;
import com.guang.provider.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/27
 */
@RestController
@CrossOrigin("*")
public class FileController implements FileRouterService {

    @Autowired
    FileRemoteService fileService;

    @Override
    public ResponseVO<List<FileVo>> uploadFiles(MultipartFile[] source, String fileParams) {
        return fileService.uploadFiles(source, fileParams);
    }

    @Override
    public ResponseVO<List<FileVo>> getBatchFilesBy(FileQueryAo fileQueryAo) {
        System.out.println("获取文件： " + fileQueryAo);
        return fileService.getBatchFilesBy(fileQueryAo);
    }


}
