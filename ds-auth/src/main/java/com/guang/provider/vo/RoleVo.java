package com.guang.provider.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * 角色对象
 *
 * @author guangmingdexin
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleVo {


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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("\"id\":").append(id)
                .append(", \"name\":").append(name)
                .append(", \"cn\":").append(cn)
                .append(", \"describe\":").append(describe)
                .append('}');
        return sb.toString();
    }
}
