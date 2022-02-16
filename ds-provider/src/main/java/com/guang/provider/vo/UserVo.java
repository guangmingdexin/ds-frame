package com.guang.provider.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mapstruct.Mapper;

/**
 * @author guangyong.deng
 * @date 2022-02-15 17:06
 */
@Getter
@Setter
@ToString
public class UserVo {

    private String userId;

    private String username;

    private int vip;

    private int score;
}
