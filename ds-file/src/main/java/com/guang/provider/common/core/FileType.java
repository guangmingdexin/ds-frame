package com.guang.provider.common.core;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 根据业务类型划定 文件类型范围
 *
 * @author guangmingdexin
 * @date 2022/4/12
 */
@Getter
public enum FileType {

    /**
     * 头像上传
     */
    Aavatar("avatar", "头像", new HashSet<>(Arrays.asList(
            "jpg", "png", "jpeg"))),

    /**
     * 学生信息导入
     */
    Student("student", "学生信息", new HashSet<>(Arrays.asList( "xls", "xlsx"))),
    /**
     * 课题附件上传
     */
    Project("project", "课题",
            new HashSet<>(Arrays.asList(
                    "jpg", "png", "jpeg", "doc", "xls", "docx", "xlsx", "pdf")));



    /**
     * 业务类型
     */
    String type;

    /**
     * 类型描述
     */
    String desc;


    /**
     * 业务文件范围
     */
    Set<String> range;

    FileType(String type, String desc, Set range) {

        this.type = type;
        this.desc = desc;
        this.range = range;
    }

    /**
     *
     * 查询 type
     *
     * @param type
     * @return
     */
    public static FileType getFileTypeBy(String type) {

        for (FileType fileType : values()) {
            if(fileType.getType().equals(type)) {
                return fileType;
            }
        }

        return null;
    }
}
