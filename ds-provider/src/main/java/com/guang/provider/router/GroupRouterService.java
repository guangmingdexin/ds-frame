package com.guang.provider.router;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.program.GroupQueryAo;
import com.guang.provider.ao.program.GroupUpdateAo;
import com.guang.provider.vo.GroupVo;
import com.guang.provider.vo.entity.GroupBasicInfoVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author guangmingdexin
 * @date 2022/4/30
 */
public interface GroupRouterService {


    @PostMapping(value = "/api/group/list")
    ResponseVO<IPage<GroupVo>> getGroupBatchBy(@RequestBody GroupQueryAo queryAo);


    @PostMapping(value = "/api/group/basic-info")
    ResponseVO<GroupBasicInfoVo> getGroupBasicInfoBy(@RequestBody GroupQueryAo queryAo);


    @PostMapping(value = "/api/group/save")
    ResponseVO<Boolean> addGroup(@RequestBody GroupUpdateAo updateAo);

    @PostMapping(value = "/api/group/edit")
    ResponseVO<Boolean> updateGroup(@RequestBody GroupUpdateAo updateAo);

    @PostMapping(value = "/api/group/del")
    ResponseVO<Boolean> delGroup(GroupUpdateAo updateAo);
}
