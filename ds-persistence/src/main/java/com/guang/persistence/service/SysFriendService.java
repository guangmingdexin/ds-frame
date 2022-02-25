package com.guang.persistence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guang.persistence.domain.SysFriend;
import com.guang.persistence.domain.SysUser;
import com.guang.persistence.mapper.SysFriendMapper;
import com.guang.persistence.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guangyong.deng
 * @date 2022-02-24 15:28
 */
@Service("sysFriendService")
public class SysFriendService extends ServiceImpl<SysFriendMapper, SysFriend> {

    @Autowired
    SysFriendMapper sysFriendMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    public List<SysFriend> getFriends(String id) {

        return sysFriendMapper.getFriends(id);
    }
}
