package com.guang.provider.vo;

import com.guang.provider.vo.entity.Meta;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
@Getter
@Setter
public class MenuVo {

    private String id;

    private String name;

    private String parentId;

    private Meta meta;

    private String component;

    private String redirect;

    private String path;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuVo menuVo = (MenuVo) o;
        return Objects.equals(id, menuVo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("\"id\":").append(id)
                .append(", \"name\":").append(name)
                .append(", \"parentId\":").append(parentId)
                .append(", \"meta\":").append(meta)
                .append(", \"component\":").append(component)
                .append(", \"redirect\":").append(redirect)
                .append(", \"path\":").append(path)
                .append('}');
        return sb.toString();
    }
}
