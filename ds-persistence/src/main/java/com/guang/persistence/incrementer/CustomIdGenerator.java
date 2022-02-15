package com.guang.persistence.incrementer;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义ID生成器
 * 仅作为示范
 *
 * @author nieqiuqiu 2019/11/30
 */
@Slf4j
public class CustomIdGenerator  {


//    @Override
//    public Number nextId(Object entity) {
//        return null;
//    }
//
//    @Override
//    public String nextUUID(Object entity) {
//        return IdWorker.get32UUID().substring(0, 8);
//    }
}
