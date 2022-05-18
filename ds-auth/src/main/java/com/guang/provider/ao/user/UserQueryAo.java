package com.guang.provider.ao.user;

import com.common.core.web.domain.PageBaseAo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author guangyong.deng
 * @date 2022-02-16 16:29
 */
@Getter
@NoArgsConstructor
public class UserQueryAo extends PageBaseAo {

    /**
     * 用户 id
     */
    @NotNull(message = "用户 id 不能为空")
    private String id;


}
