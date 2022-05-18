package com.guang.persistence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guang.persistence.domain.SysFile;
import com.guang.persistence.mapper.SysFileMapper;
import org.springframework.stereotype.Service;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
@Service("sysFileService")
public class SysFileService extends ServiceImpl<SysFileMapper, SysFile> {
}
