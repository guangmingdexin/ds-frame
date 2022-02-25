package com.guang.provider.mapstruct;

import com.guang.persistence.domain.SysFriend;
import com.guang.persistence.domain.SysUser;
import com.guang.provider.bo.UserBo;
import com.guang.provider.vo.Friend;
import com.guang.provider.vo.FriendVo;
import com.guang.provider.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:22
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);



    UserBo doConvertBo(SysUser sysUser);

    /**
     * bo 转换为 vo
     *
     * @param userBo bo
     * @return vo
     */
    @Mappings({
            @Mapping(source = "sysId", target = "userId"),
            @Mapping(source = "sysName", target = "username"),
            @Mapping(source = "sysVip", target = "vip"),
            @Mapping(source = "sysScore", target = "score")
    })
    UserVo boConvertVo(UserBo userBo);


    @Mappings({
            @Mapping(source = "sysId", target = "userId"),
            @Mapping(source = "sysName", target = "username"),
            @Mapping(source = "sysVip", target = "vip"),
            @Mapping(source = "sysScore", target = "score")
    })
    UserVo dtoConvertVo(SysUser sysUser);


    /**
     *
     * 将查询到的
     *
     * @param userId 用户 id
     * @param sysFriend 好友集合
     * @return vo
     */
    default FriendVo dtoConvertVo(String userId, List<SysFriend> sysFriend) {

        if(sysFriend == null) {
            return null;
        }

        FriendVo friendVo = new FriendVo();
        friendVo.setId(userId);


        List<Friend> friends = sysFriend.stream().map(this::dtoConvertFriend).collect(Collectors.toList());
        friendVo.setFriends(friends);

        return friendVo;
    }


    Friend dtoConvertFriend(SysFriend sysFriend);
}
