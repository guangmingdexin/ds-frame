package com.guang.provider.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guangmingdexin
 * @date 2022/4/30
 */
@Getter
@Setter
@NoArgsConstructor
public class GroupVo {

    private String groupId;

    private String groupName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime groupTime;

    private String groupLocation;

    private List<TeacherVo> groupTeachers;

    private List<StudentVo> groupStudents;

    private TeacherVo groupLeader;

    private String programId;


}
