package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.sql.WrapPage;
import com.guang.persistence.domain.SysGroup;
import com.guang.persistence.domain.SysStudent;
import com.guang.persistence.service.program.SysGroupService;
import com.guang.persistence.service.user.SysStudentService;
import com.guang.provider.ao.program.GroupQueryAo;
import com.guang.provider.ao.program.GroupUpdateAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IGroupService;
import com.guang.provider.vo.GroupVo;
import com.guang.provider.vo.entity.GroupBasicInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author guangmingdexin
 * @date 2022/4/30
 */
@Service
public class GroupService implements IGroupService {

    @Autowired
    SysStudentService sysStudentService;

    @Autowired
    SysGroupService sysGroupService;

    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public IPage<GroupVo> getGroupBatchBy(GroupQueryAo queryAo) {
        // 常规的分页查询
        LambdaQueryWrapper<SysGroup> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysGroup::getProgramId, queryAo.getProgramId())
                .eq(SysGroup::getDeleted, 0);

        IPage<SysGroup> page = sysGroupService.page(new Page<>(queryAo.getPage(), queryAo.getSize()));
        IPage<GroupVo> convert = page.convert(this.convert::sysGroupConvertVo);
        return new WrapPage<>(convert);
    }

    @Override
    public GroupBasicInfoVo getGroupBasicInfoBy(GroupQueryAo queryAo) {

        LambdaQueryWrapper<SysStudent> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysStudent::getProgramId, queryAo.getProgramId())
                .eq(SysStudent::getDeleted, 0);
        IPage<SysStudent> page = sysStudentService.page(new Page<>(1, 1));

        if(page.getTotal() == 0) {
            return null;
        }

        SysStudent sysStudent = page.getRecords().get(0);
        GroupBasicInfoVo vo = new GroupBasicInfoVo();
        vo.setDept(sysStudent.getDept())
                .setMajor(sysStudent.getMajor())
                .setGrade(sysStudent.getGrade())
                .setTotal((int)page.getTotal())
                .setNotParticipate(0);


        return vo;
    }

    @Override
    public boolean addGroup(GroupUpdateAo updateAo) {

        return sysGroupService.save(convert.groupAoConvertDo(updateAo));

    }

    @Override
    public boolean updateGroup(GroupUpdateAo updateAo) {
        return sysGroupService.updateById(convert.groupAoConvertDo(updateAo));
    }

    @Override
    public boolean delGroup(GroupUpdateAo updateAo) {
        return sysGroupService.removeById(updateAo.getGroupId());
    }


}
