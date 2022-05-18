package com.guang.provider.router;

import com.baomidou.mybatisplus.extension.api.R;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.FileQueryAo;
import com.guang.persistence.domain.SysFile;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
public interface FileRouterService {


    /**
     *
     * 返回 文件在线访问网址
     *
     * @param fileQueryAo 文件查询对象
     * @return
     */
    @PostMapping("/api/file/path")
    ResponseVO<String> getFilePath(@Validated @RequestBody FileQueryAo fileQueryAo);


    /**
     *
     * 上传文件
     *
     * @param source 上传文件
     * @param id 用户 id
     * @param type 资源所属类型（头像，课题）
     * @param cover 是否覆盖
     * @return
     */
    @PostMapping("/api/file/upload")
    ResponseVO<Boolean> uploadFile(@RequestParam("source") MultipartFile source,
                                   @RequestParam("fileParams") String fileParams);

    /**
     *
     * 批量上传文件（测试）
     *
     * @param source
     * @param fileParams
     * @return
     */
    @PostMapping(value = "/api/file/upload-files")
    ResponseVO<List<SysFile>> uploadFiles(@RequestPart("source") MultipartFile[] source,
                                    @RequestParam("fileParams") String fileParams);


    /**
     *
     * 获取所有文件（条件查询）
     *
     * @param fileQueryAo 文件查询对象
     * @return
     */
    @PostMapping("/api/file/info")
    ResponseVO<List<SysFile>> getBatchFilesBy(@Validated @RequestBody FileQueryAo fileQueryAo);


    /**
     *
     * 判断文件是否存在
     *
     * @param fileQueryAo 文件查询对象
     * @return
     */
    @PostMapping("/api/file/exist")
    ResponseVO<Boolean> existFile(@Validated @RequestBody FileQueryAo fileQueryAo);


    /**
     *
     * 下载文件
     *
     * @param fileQueryAo 文件查询对象
     * @return 文件下载
     */
    @PostMapping("/api/file/download")
    Resource downloadFile(@Validated @RequestBody FileQueryAo fileQueryAo);



}
