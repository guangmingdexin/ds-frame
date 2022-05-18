package com.guang.provider.service.impl;

import com.guang.persistence.mapper.program.SysTaskMapper;
import com.guang.provider.ao.count.CountQueryAo;
import com.guang.provider.common.Status;
import com.guang.provider.service.IStaticService;
import com.guang.provider.vo.TaskStaticCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guangmingdexin
 * @date 2022/4/27
 */
@Service
public class StaticService implements IStaticService {

    @Autowired
    SysTaskMapper sysTaskMapper;

    @Override
    public int countStudent(CountQueryAo queryAo) {
        return 0;
    }

    @Override
    public TaskStaticCountVo countStaticTask(CountQueryAo queryAo) {

        // 提交数，完成数
        int success = sysTaskMapper.countTask(queryAo.getTaskId(), Status.Success.getStatus());
        int fail = sysTaskMapper.countTask(queryAo.getTaskId(), Status.Error.getStatus());

        return new TaskStaticCountVo(success, fail, success + fail);
    }
}
