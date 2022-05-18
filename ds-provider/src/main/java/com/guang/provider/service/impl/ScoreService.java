package com.guang.provider.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.core.sql.WrapPage;
import com.guang.persistence.domain.SysStudent;
import com.guang.persistence.mapper.user.SysStudentMapper;
import com.guang.provider.ao.program.GroupQueryAo;
import com.guang.provider.ao.program.ScoreQueryAo;
import com.guang.provider.ao.user.StudentDelAo;
import com.guang.provider.mapstruct.CommonConvert;
import com.guang.provider.service.IScoreService;
import com.guang.provider.vo.GroupVo;
import com.guang.provider.vo.StudentVo;
import com.guang.provider.vo.TeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guangmingdexin
 * @date 2022/5/2
 */
@Service
public class ScoreService implements IScoreService {

    @Autowired
    SysStudentMapper sysStudentMapper;

    @Autowired
    GroupService groupService;

    @Autowired
    StudentService studentService;

    final CommonConvert convert = CommonConvert.INSTANCE;

    @Override
    public IPage<StudentVo> getBatchScoreBy(ScoreQueryAo queryAo) {

        // 1.根据不同类型获取不同的学生成绩
        IPage<SysStudent> page = new Page<>();

        List<SysStudent> records = sysStudentMapper.getScore(
                queryAo.getSysId(), queryAo.getProgramId(), queryAo.getPage() - 1, queryAo.getSize());

        page.setCurrent(queryAo.getPage());
        page.setSize(queryAo.getSize());
        page.setRecords(records);

        IPage<StudentVo> convert = page.convert(this.convert::sysStudentCovertVo);

        return new WrapPage<>(convert);
    }

    @Override
    public IPage<StudentVo> getBatchReplyScoreBy(ScoreQueryAo queryAo) {

        GroupQueryAo groupQueryAo = new GroupQueryAo();
        groupQueryAo.setProgramId(queryAo.getProgramId());

        IPage<GroupVo> page = groupService.getGroupBatchBy(groupQueryAo);

        List<GroupVo> source = page.getRecords();

        // 提取出所有答辩教师 id，以及所有学生 id，
        List<StudentVo> voList = source.stream()
                .filter(groupVo -> groupVo.getGroupTeachers() != null && groupVo.getGroupStudents() != null)
                .filter(groupVo -> groupVo.getGroupTeachers().stream().map(TeacherVo::getSysId).collect(Collectors.toList()).contains(queryAo.getSysId()))
                .map(GroupVo::getGroupStudents).findAny().orElse(null);

        if(voList != null) {

            List<String> ids = voList.stream().map(StudentVo::getSysId).collect(Collectors.toList());
            voList = studentService.getBatchStudentNotLimitBy(new StudentDelAo(ids));

        }

        // 还需要获取 vo 的详细信息
        IPage<StudentVo> result = new Page<>(page.getCurrent(), page.getSize());
        result.setRecords(voList);

        return voList == null ? new WrapPage<>() : new WrapPage<>(result);

    }

    @Override
    public IPage<StudentVo> getBatchViewScoreBy(ScoreQueryAo queryAo) {

        GroupQueryAo groupQueryAo = new GroupQueryAo();
        groupQueryAo.setProgramId(queryAo.getProgramId());

        IPage<GroupVo> page = groupService.getGroupBatchBy(groupQueryAo);

        List<GroupVo> source = page.getRecords();

        // 提取出所有答辩教师 id，以及所有学生 id，
        List<StudentVo> voList = source.stream()
                .filter(groupVo -> groupVo.getGroupTeachers() != null && groupVo.getGroupStudents() != null)
                .filter(groupVo -> groupVo.getGroupTeachers().stream().map(TeacherVo::getSysId).collect(Collectors.toList()).contains(queryAo.getSysId()))
                .filter(groupVo -> groupVo.getGroupLeader().getSysId().equals(queryAo.getSysId()))
                .map(GroupVo::getGroupStudents).findAny().orElse(null);

        if(voList != null) {

            List<String> ids = voList.stream().map(StudentVo::getSysId).collect(Collectors.toList());
            voList = studentService.getBatchStudentNotLimitBy(new StudentDelAo(ids));

        }

        // 还需要获取 vo 的详细信息
        IPage<StudentVo> result = new Page<>(page.getCurrent(), page.getSize());
        result.setRecords(voList);

        return voList == null ? new WrapPage<>() : new WrapPage<>(result);

    }

}
