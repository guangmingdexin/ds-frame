package com.guang.persistence.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guang.persistence.domain.SysStudent;
import com.common.core.sql.SqlUtil;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/16
 */
@Mapper
public interface SysStudentMapper extends BaseMapper<SysStudent> {


    /**
     *
     * 批量插入，如果指定字段相同，则更新
     *
     * @return
     */
    @InsertProvider(type = SqlUtil.class, method = "insertBatchString")
    boolean insertBatchOrUpdate(List<?> dataList, Class<?> clazz);


    /**
     *
     * 获取用户的成绩根据请求人的 id
     *
     * @param sysId id
     * @param page 页数
     * @param size 每页数量
     * @return
     */
    @Select("select " +
                "* " +
            "from " +
                "sys_st " +
            "where " +
                "sys_id " +
            "in " +
            "(" +
                "select " +
                    "sys_st_id " +
                "from " +
                    "ds_project " +
                "where " +
                    "sys_id = #{sysId} " +
                "and " +
                    "pr_id = #{programId}" +
            ") limit #{page}, #{size}"
    )
    @Results({
            @Result(id = true, column = "sys_id", property = "sysId"),
            @Result(column = "sys_st_no", property = "no"),
            @Result(column = "sys_st_name", property = "name"),
            @Result(column = "sys_st_dept", property = "dept"),
            @Result(column = "sys_st_major", property = "major"),
            @Result(column = "sys_st_grade", property = "grade"),
            @Result(column = "sys_st_email", property = "mail"),
            @Result(column = "sys_st_tel", property = "tel"),
            @Result(column = "sys_st_verify", property = "verify"),
            @Result(column = "status", property = "status"),
            @Result(column = "deleted", property = "deleted"),
            @Result(column = "pr_id", property = "programId"),
            @Result(column = "guide_score", property = "guideScore"),
            @Result(column = "guide_review", property = "guideReview"),
            @Result(column = "view_score", property = "viewScore"),
            @Result(column = "view_review", property = "viewReview"),
            @Result(column = "reply_score", property = "replyScore"),
            @Result(column = "reply_review", property = "replyReview"),
            @Result(column = "final_score", property = "finalScore"),
    })
    List<SysStudent> getScore(@Param(value = "sysId") String sysId,
                              @Param("programId") String programId,
                              @Param("page") Integer page,
                              @Param("size") Integer size);



}
