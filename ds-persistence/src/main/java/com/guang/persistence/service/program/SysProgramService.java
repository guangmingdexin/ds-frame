package com.guang.persistence.service.program;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guang.persistence.domain.SysProgram;
import com.guang.persistence.mapper.program.SysProgramMapper;
import org.springframework.stereotype.Service;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@Service(value = "sysProgramService")
public class SysProgramService extends ServiceImpl<SysProgramMapper, SysProgram> {
}
