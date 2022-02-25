package com.guang.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guang.persistence.domain.SysFriUser;
import com.guang.persistence.domain.SysFriend;
import com.guang.persistence.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author guangyong.deng
 * @date 2022-02-24 15:14
 */
@Mapper
@Service
public interface SysFriendMapper extends BaseMapper<SysFriend> {

    /**
     *
     * 获取好友
     *
     * @param id 用户 id
     * @return 好友
     */
    @Results(id = "friendMap", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "sys_id_y", property = "friend", javaType = SysUser.class,
                    one = @One(select = "com.guang.persistence.mapper.SysUserMapper.selectById"))

    })
    @Select("select id, sys_id_y from sys_friend where sys_id_x = #{id} ")
    List<SysFriend> getFriends(String id);


}
