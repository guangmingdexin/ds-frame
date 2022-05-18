package com.guang.provider.log;


import com.guang.provider.common.Module;
import com.guang.provider.common.OperationType;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.*;

/**
 *
 * 自定义操作日志记录
 *
 * @author asus
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface OperationLog {

    // 操作模块
    Module operationModule() default Module.NONE;

    // 操作类型
    OperationType operationType() default OperationType.NONE;

    // 操作说明
    OperationType operationDesc() default OperationType.NONE;

}
