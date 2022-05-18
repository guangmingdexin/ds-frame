package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.common.core.web.domain.ResponseVO;
import com.guang.main.common.exception.FileExistException;
import com.guang.provider.ao.FileQueryAo;
import com.guang.persistence.domain.SysFile;
import com.guang.persistence.service.SysFileService;
import com.guang.provider.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
@Service("fileService")
public class FileService implements IFileService {

    @Autowired
    SysFileService sysFileService;

    @Autowired
    HttpServletRequest request;

    String rootPath = "D://avatar";

    /**
     * windows 下默认的文件保存根目录
     */
    final String winDefaultRootPath = "";

    /**
     * Linunx 下默认的文件保存根目录
     */
    final String linDefaultRootPath = "";


    @Override
    public String getFilePath(FileQueryAo fileQueryAo) {
        // 1.获取文件 id
        // 2.根据文件 id 直接获取路径
        System.out.println("receive fileId: " + fileQueryAo.getFileId());
        LambdaQueryWrapper<SysFile> fileQuery = Wrappers.lambdaQuery();
        fileQuery.eq(SysFile::getFileId, fileQueryAo.getFileId())
                .eq(SysFile::getDeleted, 0);

        SysFile sysFile = sysFileService.getOne(fileQuery);

        return sysFile.getFilePath();
    }

    @Override
    public SysFile uploadFile(MultipartFile source, FileQueryAo fileQueryAo) {


        if(rootPath == null) {
            // 设置一个默认的根目录保存

        }

        // 判断是否已存在该文件
        String fileName = source.getOriginalFilename();

        Objects.requireNonNull(fileName, "filename is null");

        // TODO: 会造成数据库重复数据产生，解决方案，使用更新
        // 是否重复分为主要依据两个字段：type，filename
        // 1.先查询，是否存在

        // 1.1 如果存在，且 cover 为 true 直接覆盖

        // 1.2 如果存在，且 cover 为 false 直接失败

        boolean cover = fileQueryAo.getCover();
        String id = fileQueryAo.getId();
        String type = fileQueryAo.getBusinessType();

        Boolean exist = existFile(fileQueryAo);

        if(exist && (!cover)) {
            throw new FileExistException("文件名已存在： " + fileName);
        }


        // 判断根目录是否存在
        Path path = Paths.get(rootPath, id, type);
        if(!Files.isDirectory(path)) {
            try {
                Files.createDirectory(path);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {

            Files.copy(
                    source.getInputStream(),
                    path.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            // 1.获取端口号 2.获取前缀（http or https） 3.获取当前请求路由

            String uri = request.getRequestURI();
            String url =  request.getRequestURL().toString();

            url = url.replace(uri, "");
            // 拼接 url 地址
            String fileUrl = url + "/" + id +  "/" + type + "/" + fileName;

            SysFile sysFile = new SysFile()
                    .setFileName(fileName)
                    .setFileSize(source.getSize())
                    .setFilePath(fileUrl)
                    .setBusinessType(type)
                    .setSysId(id)
                    .setServiceId(fileQueryAo.getServiceId())
                    .setStatus("done");

            // TODO: 还需要判断数据库是否更新成功，否则文件创建成功，数据库更新失败，会导致
            //        内存泄露，文件永远无法被使用，删除

            boolean r;

            if(!exist) {
                r =  sysFileService.saveOrUpdate(sysFile);
            }else {

                LambdaUpdateWrapper<SysFile> updateWrapper = Wrappers.lambdaUpdate();
                updateWrapper.eq(SysFile::getDeleted, 0)
                                .eq(SysFile::getBusinessType, type)
                                        .eq(SysFile::getFileName, fileName);

                r = sysFileService.update(sysFile, updateWrapper);
            }

            if(!r) {
                // 删除创建的文件
                Files.delete(path);
                sysFile.setStatus("error");
            }

            return sysFile;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SysFile> uploadFiles(MultipartFile[] source, FileQueryAo queryAo) {
        // 1.将多个文件打包成压缩文件保存（使用的时候还需要单独解压，比较麻烦）
        // 2.多个调用单个上传接口，逐次上传（无法保证原子性），所以为了数据的尽量完整，必须最后处理文件逻辑

        List<SysFile> fileList = new ArrayList<>();

        for (MultipartFile multipartFile : source) {
            SysFile sysFile = uploadFile(multipartFile, queryAo);
            if(sysFile != null) {
                fileList.add(sysFile);
            }
        }

        return fileList;
    }

    @Override
    public Boolean existFile(FileQueryAo fileQueryAo) {
        // 通过用户 id 和 文件名称 查询文件是否存在
        LambdaQueryWrapper<SysFile> query = Wrappers.lambdaQuery();

        query.eq(SysFile::getSysId, fileQueryAo.getId())
                .eq(SysFile::getDeleted, 0)
                .eq(SysFile::getBusinessType, fileQueryAo.getBusinessType());

        List<SysFile> files = sysFileService.list(query);

        // 判断是否有重名文件
        return files
                .stream()
                .anyMatch(sysFile -> sysFile.getFileName().equals(fileQueryAo.getFileName()));

    }

    @Override
    public List<SysFile> getBatchFileBy(FileQueryAo fileQueryAo) {

        // 1. 直接条件查询，减少接口数量
        LambdaQueryWrapper<SysFile> query = Wrappers.lambdaQuery();

        query.eq(!StringUtils.isEmpty(fileQueryAo.getFileId()), SysFile::getSysId, fileQueryAo.getId())
                        .eq(SysFile::getDeleted, 0)
                                .eq(!StringUtils.isEmpty(fileQueryAo.getBusinessType()), SysFile::getFileType, fileQueryAo.getBusinessType())
                .eq(!StringUtils.isEmpty(fileQueryAo.getServiceId()), SysFile::getServiceId, fileQueryAo.getServiceId());

        // 2.范围查询条件
        query.in(fileQueryAo.getFileIds() != null, SysFile::getFileId, fileQueryAo.getFileIds());
        query.in(fileQueryAo.getServiceIds() != null, SysFile::getServiceId, fileQueryAo.getServiceIds());

        return sysFileService.list(query);
    }

    @Override
    public Resource downloadFile(FileQueryAo fileQueryAo) {

        String id = fileQueryAo.getId();
        String fileName = fileQueryAo.getFileName();

        Path path = Paths.get(rootPath, id);

        Path resolve = path.resolve(fileName);

        try {
            UrlResource resource = new UrlResource(resolve.toUri());
            if(resource.exists() && resource.isReadable()) {
                return resource;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
