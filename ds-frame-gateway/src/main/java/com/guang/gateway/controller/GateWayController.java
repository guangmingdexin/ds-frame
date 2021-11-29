package com.guang.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guangyong.deng
 * @date 2021-11-26 9:34
 */
@RestController
public class GateWayController {

//    @Autowired
//    DsTokenConfig config;

    // logback
    private final static Logger logger = LoggerFactory.getLogger(GateWayController.class);

    @GetMapping("/say")
    public String hello() {
        logger.debug("debug");
        logger.info("info");
        return "";
    }
}
