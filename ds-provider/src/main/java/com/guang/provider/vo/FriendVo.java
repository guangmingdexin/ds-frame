package com.guang.provider.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author guangyong.deng
 * @date 2022-02-24 15:09
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FriendVo {

    /**
     * id
     */
    private String id;

    private UserVo user;


    /**
     * 好友
     */
    private List<Friend> friends;

}
