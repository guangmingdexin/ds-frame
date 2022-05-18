package com.guang.provider.vo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
@Getter
@Setter
@Accessors(chain = true)
public class Meta {

    private String title;

    private Boolean show;

    private Boolean hideChildren;

    private Boolean hideHeader;

    private Boolean hiddenHeaderContent;

    private String target;

    private String icon;

    private Boolean keepAlive;


}
