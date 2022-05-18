package com.guang.provider.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:06
 */
@Getter
@Setter
@ToString
public class UserVo {

    private String id;

    private String name;

    private String username;

    private String avatar;

    List<RoleVo> roles;

    Set<PermissionVo> permissions;

}
