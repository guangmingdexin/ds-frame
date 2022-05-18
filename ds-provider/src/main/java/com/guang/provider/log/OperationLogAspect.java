package com.guang.provider.log;

import com.guang.provider.ao.log.OperationLogUpdateAo;
import com.guang.provider.service.impl.OperationLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author guangmingdexin
 * @date 2022/4/25
 */
@Aspect
@Component
public class OperationLogAspect {


    @Autowired
    OperationLogService operationLogService;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.guang.provider.log.OperationLog)")
    public void operationLogPointCut() {}


    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "operationLogPointCut()", returning = "keys")
    public void saveOperationLog(JoinPoint joinPoint, Object keys) {


        OperationLogUpdateAo operationLog = new OperationLogUpdateAo();
        try {

            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperationLog opLog = method.getAnnotation(OperationLog.class);
            if (opLog != null) {

                operationLog.setLogName(opLog.operationModule().getModule())
                        .setLogDesc(opLog.operationDesc().getDesc())
                        .setLogType(opLog.operationType().getName());

            }

            operationLog.setLogTime(LocalDateTime.now());

            operationLogService.addOperationLog(operationLog);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
