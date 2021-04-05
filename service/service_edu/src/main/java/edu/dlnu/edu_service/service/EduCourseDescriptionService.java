package edu.dlnu.edu_service.service;

import edu.dlnu.edu_service.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void removeDescriptionByCourseId(String courseId);
}
