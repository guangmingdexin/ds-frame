package com.guang.provider.vo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author guangmingdexin
 * @date 2022/5/4
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionEntitySet {

    private String action;

    private String describe;

    private boolean defaultCheck;

}
