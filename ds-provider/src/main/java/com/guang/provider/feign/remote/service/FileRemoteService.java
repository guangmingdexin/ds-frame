package com.guang.provider.feign.remote.service;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.file.FileQueryAo;
import com.guang.provider.config.BeanConfig;
import com.guang.provider.vo.FileVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/13
 */
@FeignClient(value = "file-service", configuration = BeanConfig.class)
@Service
public interface FileRemoteService {


    /**
     *
     * feign 调用
     *
     * @param fileQueryAo
     * @return
     */
    @PostMapping("/api/file/path")
    ResponseVO<String> getFileUrl(FileQueryAo fileQueryAo);


    /**
     *
     * 批量上传文件（测试）
     *
     * @param source
     * @param fileParams
     * @return
     */
    @PostMapping(value = "/api/file/upload-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseVO<List<FileVo>> uploadFiles(@RequestPart("source")MultipartFile[] source,
                                         @RequestParam("fileParams") String fileParams);


    /**
     *
     * 获取所有文件（条件查询）
     *
     * @param fileQueryAo 文件查询对象
     * @return
     */
    @PostMapping("/api/file/info")
    ResponseVO<List<FileVo>> getBatchFilesBy(@Validated @RequestBody FileQueryAo fileQueryAo);

}
