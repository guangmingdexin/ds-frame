package com.guang.provider.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author guangmingdexin
 * @date 2022/5/4
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RolePermissionVo {


    /**
     * 角色 id
     */
    String id;

    /**
     * 角色名称
     */
    String name;

    String cn;

    String describe;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime createTime;


    String status;



    Set<PermissionVo> permissions;
}
