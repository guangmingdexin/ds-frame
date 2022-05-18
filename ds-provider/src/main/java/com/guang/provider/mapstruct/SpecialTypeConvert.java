package com.guang.provider.mapstruct;

import com.common.core.web.bean.BeanUtil;
import com.common.core.web.domain.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guang.persistence.domain.SysStudent;
import com.guang.persistence.domain.SysTeacher;
import com.guang.persistence.service.user.SysStudentService;
import com.guang.persistence.service.user.SysTeacherService;
import com.guang.provider.ao.file.FileQueryAo;
import com.guang.provider.feign.remote.service.FileRemoteService;
import com.guang.provider.vo.StudentVo;
import com.guang.provider.vo.TeacherVo;
import com.guang.provider.vo.entity.Progress;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/12
 */
@Component
public class SpecialTypeConvert {


    private final ObjectMapper objectMapper = new ObjectMapper();


    @Named("progressToVo")
    public Progress strToProgress(String strProgress) {

        if(strProgress == null) {
            return null;
        }

        try {
            return objectMapper.readValue(strProgress, Progress.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Named(value = "progressToDo")
    public String progressToStr(Progress progress) {

        if(progress == null) {
            return "{}";
        }

        try {
            return objectMapper.writeValueAsString(progress);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "{}";
    }


    /**
     *
     * 将 文件 id 转换为 文件路径
     *
     * @param fileId 文件 id
     * @return
     */
    @Named("avatar")
    public String getFileUrl(String fileId) {

        FileRemoteService fileRemoteService = BeanUtil.getBean(FileRemoteService.class);

        return fileRemoteService.getFileUrl(new FileQueryAo().setFileId(fileId)).getResult();
    }


    /**
     *
     * 负责人信息
     *
     * @param leaderId 负责人 id
     * @return vo
     */
    @Named("leaderToVo")
    public TeacherVo leaderStrToVo(String leaderId) {

        SysTeacherService service = BeanUtil.getBean(SysTeacherService.class);

        if(leaderId.equals(Constant.INITIAL_PROCESS_LEADER_ID)) {
            return null;
        }
        SysTeacher vo = service.getById(leaderId);

        return new TeacherVo(
                vo.getProgramId(),
                vo.getSysId(),
                vo.getNo(),
                vo.getName(),
                vo.getDept(),
                vo.getJob(),
                vo.getStatus()
        );
    }


    /**
     *
     * 转换后的学生 vo 对象
     *
     * @param st 分组中学生列表对象 （学生 id , 默认以逗号分隔）
     * @return vo
     */
    @Named(value = "stToVo")
    public List<StudentVo> stListStrToVo(String st) {

        // 测试

        if(StringUtils.isEmpty(st)) {
            return null;
        }

        // 获取
        SysStudentService service = BeanUtil.getBean(SysStudentService.class);

        String[] ids = st.trim().split(",");
        List<StudentVo> vo = new ArrayList<>(ids.length);

        Arrays.stream(ids).forEach(id -> {

            SysStudent entity = service.getById(id);
            StudentVo v = new StudentVo();

            v.setSysId(id);
            v.setStName(entity.getName());

            vo.add(v);
        });


        return vo;
    }



    @Named(value = "tcToVo")
    public List<TeacherVo> tcListStrToVo(String tc) {

        if(StringUtils.isEmpty(tc)) {
            return null;
        }

        System.out.println("tc: " + tc);

        SysTeacherService service = BeanUtil.getBean(SysTeacherService.class);

        String[] ids = tc.trim().split(",");
        List<TeacherVo> voList = new ArrayList<>(ids.length);

        // TODO 应该使用批量查询
        Arrays.stream(ids).forEach(id -> {

            SysTeacher sysTeacher = service.getById(id);

            TeacherVo v = new TeacherVo();

            v.setSysId(id);
            v.setTcName(sysTeacher.getName());

            voList.add(v);
        });

        return voList;
    }


    @Named("arrToStr")
    public String arrToStr(List<String> ids) {

        if(ids == null || ids.isEmpty()) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        ids.forEach(id -> {
            builder.append(id).append(",");
        });

        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }



}
