package edu.dlnu.edu_service.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.commonutils.R;
import edu.dlnu.edu_service.entity.EduCourse;
import edu.dlnu.edu_service.entity.EduTeacher;
import edu.dlnu.edu_service.service.EduCourseService;
import edu.dlnu.edu_service.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class indexFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;
    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public R index(){
        //查询前8个热门课程
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 8");
        List<EduCourse> EduCourselist = eduCourseService.list(queryWrapper);

        //查询前4个名师
        QueryWrapper<EduTeacher> queryWrapperteacher = new QueryWrapper<>();
        queryWrapperteacher.orderByDesc("id");
        queryWrapperteacher.last("limit 4");
        List<EduTeacher> EduTeacherlist = eduTeacherService.list(queryWrapperteacher);
        return R.ok().data("course",EduCourselist).data("teacher",EduTeacherlist);
    }

}
