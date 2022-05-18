package com.guang.provider.mapstruct;

import com.guang.persistence.domain.*;
import com.guang.provider.ao.log.OperationLogUpdateAo;
import com.guang.provider.ao.process.ProcessProjectUpdateAo;
import com.guang.provider.ao.program.GroupUpdateAo;
import com.guang.provider.ao.program.ProgramUpdateAo;
import com.guang.provider.ao.program.ProjectUpdateAo;
import com.guang.provider.ao.program.TaskUpdateAo;
import com.guang.provider.ao.user.StudentUpdateAo;
import com.guang.provider.ao.user.TeacherUpdateAo;
import com.guang.provider.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/9
 */
@Mapper(uses = SpecialTypeConvert.class)
public interface CommonConvert {


    CommonConvert INSTANCE = Mappers.getMapper(CommonConvert.class);



    /* ---------------------------------- Program 实体转换 --------------------------*/


    /**
     *
     * do -> vo
     *
     * @param sysProgram
     * @return
     */
    @Mappings({
            @Mapping(source = "programTitle", target = "title"),
            @Mapping(source = "programDesc", target = "description"),
            @Mapping(source = "programOwner", target = "owner"),
//            @Mapping(source = "create_time", target = "startAt"),
            @Mapping(source = "programSchedule", target = "progress", qualifiedByName = "progressToVo"),
            @Mapping(source = "avatar", target = "avatar", qualifiedByName = "avatar")
    })
    ProgramVo sysProgramConvertVo(SysProgram sysProgram);


    /**
     *
     * ao -> do
     *
     * @param updateAo ao
     * @return do
     */
    @Mappings({
            @Mapping(target = "programTitle", source = "title"),
            @Mapping(target = "programDesc", source = "description"),
            @Mapping(target = "programOwner", source = "owner"),
            @Mapping(target = "programSchedule", source = "progress", qualifiedByName = "progressToDo"),
            @Mapping(target = "sysId", source = "id"),
            @Mapping(target = "endAt", source = "endAt")
    })
    SysProgram programAoConvertDo(ProgramUpdateAo updateAo);


    /**
     *
     * do -> voList
     *
     * @param sysPrograms
     * @return
     */
    List<ProgramVo> sysProgramConvertVos(List<SysProgram> sysPrograms);



    /* -----------------------------Student Convert-------------------------*/


    /**
     *
     * do -> vo
     *
     * @param sysStudent do
     * @return vo
     */
    @Mappings({
            @Mapping(source = "no", target = "stNo"),
            @Mapping(source = "name", target = "stName"),
            @Mapping(source = "dept", target = "stDept"),
            @Mapping(source = "major", target = "stMajor")
    })
    StudentVo sysStudentCovertVo(SysStudent sysStudent);


    /**
     *
     * do -> vo
     *
     * @param sysStudents do
     * @return voList
     */
    List<StudentVo> sysStudentConvertVos(List<SysStudent> sysStudents);


    /**
     *
     * ao -> do
     *
     * @param updateAo  ao
     * @return do
     */
    SysStudent studentAoConvertDo(StudentUpdateAo updateAo);


    List<SysStudent> studentAosCovertDos(List<StudentUpdateAo> updateAos);



    /* --------------------------------- Teacher ----------------- */

    /**
     *
     * do -> vo
     *
     * @param sysTeacher do
     * @return vo
     */
    @Mappings({
            @Mapping(source = "no", target = "tcNo"),
            @Mapping(source = "name", target = "tcName"),
            @Mapping(source = "dept", target = "tcDept"),
            @Mapping(source = "job", target = "tcJob"),
            @Mapping(source = "status", target = "status")
    })
    TeacherVo sysTeacherCovertVo(SysTeacher sysTeacher);


    /**
     *
     * ao -> do
     *
     * @param updateAo ao
     * @return do
     */
    SysTeacher teacherAoConvertDo(TeacherUpdateAo updateAo);



    /* ------------------- Project ------------------------- */

    /**
     *
     * do -> vo
     *
     * @param sysProject do
     * @return vo
     */
        @Mappings({
            @Mapping(target = "pjId", source = "projectId"),
            @Mapping(target = "pjName", source = "projectName"),
            @Mapping(target = "pjDesc", source = "projectDesc"),
            @Mapping(target = "pjType", source = "projectType"),
            @Mapping(target = "pjLevel", source = "projectLevel"),
            @Mapping(target = "pjDeclarer", source = "projectDeclarer"),
            @Mapping(target = "pjDeclarerTime", source = "projectDeclarerTime"),
            @Mapping(target = "pjNote", source = "projectNote"),
            @Mapping(target = "status", source = "status")
    })
    ProjectVo sysProjectConvertVo(SysProject sysProject);


    /**
     *
     * ao -> do
     *
     * @param updateAo  ao
     * @return do
     */
    SysProject projectAoConvertDo(ProjectUpdateAo updateAo);



    /*----------------------- ProcessProject --------------------*/



    /**
     *
     * do -> vo
     *
     * @param sysProcessProject
     * @return vo
     */
    @Mappings({
            @Mapping(source = "leader", target = "leader", qualifiedByName = "leaderToVo")
    })
    ProcessProjectVo sysProcessProjectConvertVo(SysProcessProject sysProcessProject);


    /**
     *
     * ao -> do
     *
     * @param updateAo
     * @return do
     */
    SysProcessProject processProjectAoConvertDo(ProcessProjectUpdateAo updateAo);


    /*------------------------ OperationLog -------------------------- */

    /**
     *
     * do -> vo
     *
     * @param sysOperationLog do
     * @return vo
     */
    OperationLogVo sysOperationLogConvertVo(SysOperationLog sysOperationLog);


    /**
     * @param updateAo
     * @return
     */
    SysOperationLog operationLogAoConvertDo(OperationLogUpdateAo updateAo);



    /* ----------------- Task --------------------- */

    TaskVo sysTaskConvertVo(SysTask sysTask);



    SysTask taskAoConvertDo(TaskUpdateAo updateAo);


    /* ----------------- Group ----------------------*/

    /**
     * @param sysGroup
     * @return
     */
    @Mappings({
            @Mapping(source = "groupStudents", target = "groupStudents", qualifiedByName = "stToVo"),
            @Mapping(source = "groupTeachers", target = "groupTeachers", qualifiedByName = "tcToVo"),
            @Mapping(source = "groupLeader", target = "groupLeader", qualifiedByName = "leaderToVo")
    })
    GroupVo sysGroupConvertVo(SysGroup sysGroup);


    /**
     * @param updateAo
     * @return
     */
    @Mappings({
            @Mapping(source = "groupTeachers", target = "groupTeachers", qualifiedByName = "arrToStr"),
            @Mapping(source = "groupStudents", target = "groupStudents", qualifiedByName = "arrToStr")
    })
    SysGroup groupAoConvertDo(GroupUpdateAo updateAo);

}
