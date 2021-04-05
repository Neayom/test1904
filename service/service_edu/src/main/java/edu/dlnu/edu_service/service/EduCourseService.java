package edu.dlnu.edu_service.service;

import edu.dlnu.edu_service.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.dlnu.edu_service.entity.vo.CourseInfoVo;
import edu.dlnu.edu_service.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
public interface EduCourseService extends IService<EduCourse> {
    ////添加课程基本信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);
    //得到课程基本信息
    CourseInfoVo getCourseVo(String courseId);
    //更新
    String updateCourse(CourseInfoVo courseInfoVo);
    //发布后的课程信息
    CoursePublishVo getPublishCourseInfo(String id);
    //删除课程
    void removeCourse(String courseId);
}
