package edu.dlnu.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.dlnu.commonutils.R;
import edu.dlnu.edu_service.entity.EduCourse;
import edu.dlnu.edu_service.entity.vo.CourseInfoVo;
import edu.dlnu.edu_service.entity.vo.CoursePublishVo;
import edu.dlnu.edu_service.entity.vo.CourseQuery;
import edu.dlnu.edu_service.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    //分页查询所有课程信息
    @PostMapping("listAllCourse/{current}/{limit}")
    @ApiOperation(value = "遍历课程")
    public R listAllCourse(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> page = new Page(current,limit);

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();

        if(!StringUtils.isEmpty(title)) {
            queryWrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        System.out.println("++++++++");
        eduCourseService.page(page, queryWrapper);
        System.out.println("++++++++++++++++++++++++++");
        long total = page.getTotal();
        System.out.println(total);
        List<EduCourse> records = page.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    //添加课程基本信息
    @PostMapping("addCourseInfo")
    @ApiOperation(value = "新增课程")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String s = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",s);
    }

    //根据id，查询课程信息进行数据回显,用于"上一步"进行修改
    @GetMapping("getCourseInfo/{courseId}")
    @ApiOperation(value = "课程信息回显")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo =  eduCourseService.getCourseVo(courseId);
        System.out.println("调用了getCourseInfo");
        return R.ok().data("course",courseInfoVo);
    }
    //课程更新
    @PostMapping("UpdateCourse")
    @ApiOperation(value = "课程信息更新")
    public R UpdateCourse(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.updateCourse(courseInfoVo);
        return R.ok().data("courseId",courseId);
    }
    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    @ApiOperation(value = "根据课程id查询课程")
    public R getPublishCourseInfo(@PathVariable String id){

        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourseInfo(id);
        System.out.println(coursePublishVo);
        return R.ok().data("coursePublishVo",coursePublishVo);
    }
    //课程最终发布
    //修改课程状态
    @PutMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }
    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return R.ok();
    }
}

