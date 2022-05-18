package com.guang.provider.ao.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/17
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDelAo {

    /**
     * 批量删除 id
     */
    @NotNull(message = "id 不能为空")
    private List<String> sysIds;
}
