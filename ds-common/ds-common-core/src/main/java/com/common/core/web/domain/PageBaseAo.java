package com.common.core.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author guangmingdexin
 * @date 2022/4/11
 */
@Getter
@Setter
@Accessors(chain = true)
public class PageBaseAo {

    int total;

    int size = 10;

    int page = 1;
}
