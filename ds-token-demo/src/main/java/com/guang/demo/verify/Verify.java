package com.guang.demo.verify;

/**
 *
 * 毕设通用接口 - 审核模块
 *
 * @author guangyong.deng
 * @date 2021-11-29 15:59
 */
public interface Verify<T, R> {

    /**
     * 待审核对象，经过处理后，返回结果
     *
     * @param t
     * @return
     */
    T verify(R t);
}
