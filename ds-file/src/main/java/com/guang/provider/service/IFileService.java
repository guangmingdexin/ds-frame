package com.guang.provider.service;

import com.guang.provider.ao.FileQueryAo;
import com.guang.persistence.domain.SysFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
public interface IFileService {


    /**
     *
     * 获取文件 url 地址
     *
     * @param fileQueryAo 文件查询对象
     * @return
     */
    String getFilePath(FileQueryAo fileQueryAo);


    /**
     *
     * 上传文件
     *
     * @param source 文件资源
     * @return
     */
    SysFile uploadFile(MultipartFile source, FileQueryAo fileQueryAo);


    /**
     *
     * 多个文件同时上传
     *
     * @param source
     * @param queryAo
     * @return
     */
    List<SysFile> uploadFiles(MultipartFile[] source, FileQueryAo queryAo);

    /**
     *
     * 判断用户当中是否存在某个文件
     *
     * @param fileQueryAo 文件查询对象
     * @return
     */
    Boolean existFile(FileQueryAo fileQueryAo);


    /**
     *
     * 获取文件列表
     *
     * @param fileQueryAo 文件查询对象
     * @return
     */
    List<SysFile> getBatchFileBy(FileQueryAo fileQueryAo);


    /**
     *
     * 下载文件
     *
     * @param fileQueryAo  文件查询对象
     * @return
     */
    Resource downloadFile(FileQueryAo fileQueryAo);

}
