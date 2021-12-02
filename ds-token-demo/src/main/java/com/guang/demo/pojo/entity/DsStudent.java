package com.guang.demo.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author guangyong.deng
 * @date 2021-12-01 9:29
 */
public class DsStudent {

    private String stuId;

    private String stuNumber;

    private String stuName;

    private String stuDept;

    private Boolean stuDelete;

    public DsStudent() {
    }

    public String getStuId() {
        return stuId;
    }

    public DsStudent setStuId(String stuId) {
        this.stuId = stuId;
        return this;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public DsStudent setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
        return this;
    }

    public String getStuName() {
        return stuName;
    }

    public DsStudent setStuName(String stuName) {
        this.stuName = stuName;
        return this;
    }

    public String getStuDept() {
        return stuDept;
    }

    public DsStudent setStuDept(String stuDept) {
        this.stuDept = stuDept;
        return this;
    }

    public Boolean getStuDelete() {
        return stuDelete;
    }

    public DsStudent setStuDelete(Boolean stuDelete) {
        this.stuDelete = stuDelete;
        return this;
    }

    @Override
    public String toString() {
        return "DsStudent{" +
                "stuId='" + stuId + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuDept='" + stuDept + '\'' +
                ", stuDelete=" + stuDelete +
                '}';
    }
}
