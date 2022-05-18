package com.guang.provider.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.core.web.domain.ResponseVO;
import com.guang.provider.ao.program.GroupQueryAo;
import com.guang.provider.ao.program.GroupUpdateAo;
import com.guang.provider.router.GroupRouterService;
import com.guang.provider.service.impl.GroupService;
import com.guang.provider.vo.GroupVo;
import com.guang.provider.vo.entity.GroupBasicInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangmingdexin
 * @date 2022/4/30
 */
@RestController
@CrossOrigin("*")
public class GroupController implements GroupRouterService {

    @Autowired
    GroupService groupService;

    @Override
    public ResponseVO<IPage<GroupVo>> getGroupBatchBy(GroupQueryAo queryAo) {
        return ResponseVO.success(groupService.getGroupBatchBy(queryAo));
    }

    @Override
    public ResponseVO<GroupBasicInfoVo> getGroupBasicInfoBy(GroupQueryAo queryAo) {
        return ResponseVO.success(groupService.getGroupBasicInfoBy(queryAo));
    }

    @Override
    public ResponseVO<Boolean> addGroup(GroupUpdateAo updateAo) {
        return ResponseVO.success(groupService.addGroup(updateAo));
    }

    @Override
    public ResponseVO<Boolean> updateGroup(GroupUpdateAo updateAo) {
        return ResponseVO.success(groupService.updateGroup(updateAo));
    }

    @Override
    public ResponseVO<Boolean> delGroup(GroupUpdateAo updateAo) {
        return ResponseVO.success(groupService.delGroup(updateAo));
    }
}
