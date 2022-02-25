package com.guang.persistence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * 关系表
 *
 * @author guangyong.deng
 * @date 2022-02-25 9:16
 */
@Getter
@Setter
@NoArgsConstructor
public class SysFriUser extends BaseVO {

    /**
     * 关系 id
     */
    private String id;

    /**
     * 用户自身 id
     */
    private String sysIdX;

    /**
     * 好友 id
     */
    private String sysIdY;
}
