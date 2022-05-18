package com.guang.provider.feign;

import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.file.FileQueryAo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author guangmingdexin
 * @date 2022/4/13
 */
@FeignClient(value = "file-service")
@Service(value = "fileService")
public interface FileService {


    /**
     *
     * feign 调用
     *
     * @param fileQueryAo
     * @return
     */
    @PostMapping("/api/file/path")
    ResponseVO<String> getFileUrl(FileQueryAo fileQueryAo);
}
