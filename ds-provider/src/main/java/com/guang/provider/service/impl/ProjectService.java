package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.sql.WrapPage;
import com.guang.persistence.domain.SysProject;
import com.guang.persistence.service.program.SysProjectService;
import com.guang.provider.ao.file.FileQueryAo;
import com.guang.provider.ao.program.ProjectDelAo;
import com.guang.provider.ao.program.ProjectQueryAo;
import com.guang.provider.ao.program.ProjectUpdateAo;
import com.guang.provider.feign.remote.service.FileRemoteService;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IProjectService;
import com.guang.provider.vo.FileVo;
import com.guang.provider.vo.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
@Service(value = "projectService")
public class ProjectService implements IProjectService {

    @Autowired
    SysProjectService sysProjectService;

    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public IPage<ProjectVo> getBatchProjectBy(ProjectQueryAo queryAo) {

        LambdaQueryWrapper<SysProject> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(SysProject::getProgramId, queryAo.getProgramId())
                .eq(SysProject::getDeleted, 0);

        // 条件查询
        queryWrapper.eq(!StringUtils.isEmpty(queryAo.getProjectId()), SysProject::getProjectId, queryAo.getProjectId())
                .eq(!StringUtils.isEmpty(queryAo.getProjectName()), SysProject::getProjectName, queryAo.getProjectName())
                .eq(!StringUtils.isEmpty(queryAo.getProjectDeclarer()), SysProject::getProjectDeclarer, queryAo.getProjectDeclarer())
                .eq(!StringUtils.isEmpty(queryAo.getProjectDeclarerTime()), SysProject::getProjectDeclarerTime, queryAo.getProjectDeclarerTime())
                .eq(!StringUtils.isEmpty(queryAo.getProjectType()), SysProject::getProjectType, queryAo.getProjectType())
                        .eq(!StringUtils.isEmpty(queryAo.getStatus()), SysProject::getStatus, queryAo.getStatus());

//        queryWrapper.orderByAsc(SysProject::getProjectId);

        IPage<SysProject> page = sysProjectService.page(new Page<>(queryAo.getPage(), queryAo.getSize()), queryWrapper);
        IPage<ProjectVo> convert = page.convert(this.convert::sysProjectConvertVo);

        return new WrapPage<>(convert);
    }

    @Override
    public ProjectVo getOneProjectBy(ProjectQueryAo queryAo) {

        return convert.sysProjectConvertVo(sysProjectService.getById(queryAo.getProjectId()));
    }

    @Override
    public ProjectVo getOneProjectByStId(ProjectQueryAo queryAo) {

        LambdaQueryWrapper<SysProject> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(SysProject::getSysStudentId, queryAo.getSysStudentId())
                .eq(SysProject::getDeleted, 0);



        return convert.sysProjectConvertVo(sysProjectService.getOne(queryWrapper));
    }

    //    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProjectVo addProject(ProjectUpdateAo updateAo) {
        SysProject sysProject = convert.projectAoConvertDo(updateAo);

        sysProjectService.save(sysProject);

        return convert.sysProjectConvertVo(sysProject);
    }

    @Override
    public boolean updateProject(ProjectUpdateAo updateAo) {
        LambdaUpdateWrapper<SysProject> updateWrapper = Wrappers.lambdaUpdate();

        updateWrapper
                .eq(SysProject::getProjectId, updateAo.getProjectId())
                .eq(SysProject::getDeleted, 0);

        SysProject sysProject = convert.projectAoConvertDo(updateAo);

        return sysProjectService.update(sysProject, updateWrapper);
    }

    @Override
    public boolean delProject(ProjectDelAo delAo) {
        return false;
    }

}
