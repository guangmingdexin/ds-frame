package com.common.core.web.util;

import java.util.UUID;

/**
 * @author guangmingdexin
 * @date 2022/4/23
 */
public class Util {


    public static String generatorUUID() {

        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }
}
