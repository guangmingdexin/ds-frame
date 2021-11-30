package com.guang.demo.mapper.base;

/**
 *
 * 基本数据查询接口
 *
 * @author guangyong.deng
 * @date 2021-11-29 16:02
 */
public interface BaseInfomation<T, R> {

    R findById(T t);



}
