package edu.dlnu.edu_service.mapper;

import edu.dlnu.edu_service.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.dlnu.edu_service.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);
}
