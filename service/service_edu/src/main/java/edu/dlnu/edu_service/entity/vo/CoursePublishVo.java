package edu.dlnu.edu_service.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Objects;

@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo {

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursePublishVo that = (CoursePublishVo) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(cover, that.cover) &&
                Objects.equals(lessonNum, that.lessonNum) &&
                Objects.equals(subjectLevelOne, that.subjectLevelOne) &&
                Objects.equals(subjectLevelTwo, that.subjectLevelTwo) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cover, lessonNum, subjectLevelOne, subjectLevelTwo, teacherName, price);
    }
}
