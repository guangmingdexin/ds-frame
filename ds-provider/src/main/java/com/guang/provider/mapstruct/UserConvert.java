package com.guang.provider.mapstruct;

import com.guang.persistence.domain.SysUser;
import com.guang.provider.bo.UserBo;
import com.guang.provider.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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

}
