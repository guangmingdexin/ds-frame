package com.guang.provider.ao.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/5/18
 */
@Data
public class RoleUpdateAo {


    private String id;

    private String cn;


    private String name;


    private String status;


    private String describe;


    private LocalDateTime createTime;
}
