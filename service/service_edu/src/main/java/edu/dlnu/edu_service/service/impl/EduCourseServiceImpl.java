package edu.dlnu.edu_service.service.impl;

import edu.dlnu.edu_service.entity.EduCourse;
import edu.dlnu.edu_service.entity.EduCourseDescription;
import edu.dlnu.edu_service.entity.vo.CourseInfoVo;
import edu.dlnu.edu_service.entity.vo.CoursePublishVo;
import edu.dlnu.edu_service.mapper.EduCourseMapper;
import edu.dlnu.edu_service.service.EduChapterService;
import edu.dlnu.edu_service.service.EduCourseDescriptionService;
import edu.dlnu.edu_service.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dlnu.edu_service.service.EduVideoService;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    //注入课程描述
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    //注入小节
    @Autowired
    private EduVideoService eduVideoService;
    //注入章节
    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1、向课程表里面添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert<=0){
            throw new GuliException(20001,"添加课程信息失败");
        }
        //获取添加之后的课程ID
        String cid = eduCourse.getId();
        //2、像课程简介表添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        //设置【课程】id就是【课程描述】id
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;

    }

    @Override
    public CourseInfoVo getCourseVo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(description.getDescription());
        return courseInfoVo;
    }
    //根据CourseInfoVo来更新课程表以及描述表
    @Override
    public String updateCourse(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if(i==0){
            throw new GuliException(20001,"修改课程表失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
        return eduCourse.getId();

    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo=null;
        try {
            publishCourseInfo = baseMapper.getPublishCourseInfo(id);

        }catch (Exception e) {
            e.printStackTrace();
        }
       return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);
        //根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);
        //根据课程id删除描述
        eduCourseDescriptionService.removeDescriptionByCourseId(courseId);
        //根据课程id删除课程
        int i = baseMapper.deleteById(courseId);
        if(i==0){
            throw new GuliException(20001,"删除失败");
        }
    }
}
