package com.guang.provider.service;

import com.guang.provider.ao.count.CountQueryAo;
import com.guang.provider.vo.TaskStaticCountVo;

/**
 *
 *
 * 数据统计接口
 *
 * @author guangmingdexin
 * @date 2022/4/27
 */
public interface IStaticService {


    /*--------------- 学生统计 ------------------ */

    /**
     *
     * 统计学生人数
     *
     * @param queryAo 查询对象
     * @return
     */
    int countStudent(CountQueryAo queryAo);


    /*--------------- 教师统计统计 ------------------ */



    /*--------------- 项目统计 ------------------ */




    /*--------------- 任务统计 ------------------ */

    /**
     *
     * 获取 任务的各种统计数据
     *
     * @param queryAo
     * @return
     */
    TaskStaticCountVo countStaticTask(CountQueryAo queryAo);

}
