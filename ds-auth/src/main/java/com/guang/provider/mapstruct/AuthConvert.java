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
 * @date 2022-02-15 10:16
 */
@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    /**
     *
     * do 转换为 bo
     *
     * @param sysUser do
     * @return bo
     */
    UserBo doConvertBo(SysUser sysUser);


}
