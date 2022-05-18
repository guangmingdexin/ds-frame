package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.program.ProjectDelAo;
import com.guang.provider.ao.program.ProjectQueryAo;
import com.guang.provider.ao.program.ProjectUpdateAo;
import com.guang.provider.vo.ProjectVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author guangmingdexin
 * @date 2022/4/19
 */
public interface ProjectRouterService {


    /**
     *
     * 查询
     *
     * @param queryAo ao
     * @return vo
     */
    @PostMapping(value = "/api/project/list")
    ResponseVO<IPage<ProjectVo>> getBatchProjectBy(@Validated @RequestBody ProjectQueryAo queryAo);


    @PostMapping(value = "/api/project/one")
    ResponseVO<ProjectVo> getOneProjectBy(@Validated @RequestBody ProjectQueryAo queryAo);


    @PostMapping(value = "/api/project/one/st")
    ResponseVO<ProjectVo> getOneProjectByStId(@Validated @RequestBody ProjectQueryAo queryAo);


    /**
     *
     * @param updateAo
     * @return
     */
    @PostMapping(value = "/api/project/save")
    ResponseVO<ProjectVo> addProject(@Validated @RequestBody ProjectUpdateAo updateAo);

    @PostMapping(value = "/api/project/edit")
    ResponseVO<Boolean> updateProject(@Validated @RequestBody ProjectUpdateAo updateAo);

    @PostMapping(value = "/api/project/del")
    ResponseVO<Boolean> delProject(@Validated @RequestBody ProjectDelAo delAo);
}
