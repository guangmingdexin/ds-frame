package com.guang.persistence.mapper.program;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.core.sql.SqlUtil;
import com.guang.persistence.domain.SysTask;
import com.guang.persistence.domain.SysTaskUserAssociation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/26
 */
@Mapper
public interface SysTaskMapper extends BaseMapper<SysTask> {


    /**
     *
     * 根据状态获取各个任务的总数
     *
     * @param taskId
     * @param status
     * @return
     */
    @Select(value = "select count(*) from ds_task_user where tk_id = #{taskId} and status = #{status}")
    int countTask(@Param("taskId") String taskId, @Param("status") String status);


    /**
     *
     * 批量插入
     *
     * @param dataList 数据源
     * @param clazz 类型
     * @return
     */
    @InsertProvider(type = SqlUtil.class, method = "insertBatchString")
    int addTaskUserAssociation(List<SysTaskUserAssociation> dataList, Class<SysTaskUserAssociation> clazz);


    /**
     *
     * 获取用户的提交状态
     *
     * @param params
     * @return
     */
    @Select(value = "select status from ds_task_user where tk_id = #{taskId} and  sys_id in ( ${params} )")
    List<String> getTaskUserStatus(@Param("params") String params, @Param("taskId") String taskId);



}
