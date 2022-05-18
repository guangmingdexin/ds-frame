package com.guang.persistence.service.program;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guang.persistence.domain.SysProject;
import com.guang.persistence.mapper.program.SysProjectMapper;
import org.springframework.stereotype.Service;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
@Service(value = "sysProjectService")
public class SysProjectService extends ServiceImpl<SysProjectMapper, SysProject> {
}
