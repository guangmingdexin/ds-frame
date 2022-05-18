package com.guang.provider.ao.program;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guang.provider.vo.StudentVo;
import com.guang.provider.vo.TeacherVo;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class GroupUpdateAo {

    private String groupId;

    private String groupName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime groupTime;

    private String groupLocation;

    /**
     * 上传 ID
     */
    private List<String> groupTeachers;

    private List<String> groupStudents;

    private String groupLeader;

    private String programId;
}
