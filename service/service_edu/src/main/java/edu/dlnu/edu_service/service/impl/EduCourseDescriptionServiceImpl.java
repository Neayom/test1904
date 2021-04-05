package edu.dlnu.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.edu_service.entity.EduCourseDescription;
import edu.dlnu.edu_service.mapper.EduCourseDescriptionMapper;
import edu.dlnu.edu_service.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public void removeDescriptionByCourseId(String courseId) {
        baseMapper.deleteById(courseId);
    }
}
