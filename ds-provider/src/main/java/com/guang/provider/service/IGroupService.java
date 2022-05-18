package com.guang.provider.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guang.provider.ao.program.GroupQueryAo;
import com.guang.provider.ao.program.GroupUpdateAo;
import com.guang.provider.vo.GroupVo;
import com.guang.provider.vo.entity.GroupBasicInfoVo;

/**
 * @author guangmingdexin
 * @date 2022/4/30
 */
public interface IGroupService {

    IPage<GroupVo> getGroupBatchBy(GroupQueryAo queryAo);

    GroupBasicInfoVo getGroupBasicInfoBy(GroupQueryAo queryAo);


    boolean addGroup(GroupUpdateAo updateAo);


    boolean updateGroup(GroupUpdateAo updateAo);


    boolean delGroup(GroupUpdateAo updateAo);

}
