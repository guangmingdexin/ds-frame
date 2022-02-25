package com.guang.provider.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author guangyong.deng
 * @date 2022-02-25 10:49
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Friend {

    private String id;

    private UserVo friend;
}
