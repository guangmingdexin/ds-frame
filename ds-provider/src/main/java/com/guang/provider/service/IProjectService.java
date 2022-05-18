package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.provider.ao.file.FileQueryAo;
import com.guang.provider.ao.program.ProjectDelAo;
import com.guang.provider.ao.program.ProjectQueryAo;
import com.guang.provider.ao.program.ProjectUpdateAo;
import com.guang.provider.vo.FileVo;
import com.guang.provider.vo.ProjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
public interface IProjectService {


    /**
     *
     * 查询
     *
     * @param queryAo ao
     * @return vo
     */
    IPage<ProjectVo> getBatchProjectBy(ProjectQueryAo queryAo);


    /**
     *
     * 根据 id 查询课题信息
     *
     * @param queryAo
     * @return
     */
    ProjectVo getOneProjectBy(ProjectQueryAo queryAo);


    /**
     *
     * 根据学生 id 获取课题信息
     *
     * @param queryAo 查询对象
     * @return vo
     */
    ProjectVo getOneProjectByStId(ProjectQueryAo queryAo);


    /**
     *
     * 增加
     *
     * @param updateAo ao
     * @return vo
     */
    ProjectVo addProject(ProjectUpdateAo updateAo);


    /**
     *
     * 更新
     *
     * @param updateAo ao
     * @return boolean
     */
    boolean updateProject(ProjectUpdateAo updateAo);


    /**
     *
     * 删除
     *
     * @param delAo ao
     * @return boolean
     */
    boolean delProject(ProjectDelAo delAo);


}
