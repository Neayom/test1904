package edu.dlnu.edu_service.controller;


import edu.dlnu.commonutils.R;
import edu.dlnu.edu_service.entity.subject.OneSubject;
import edu.dlnu.edu_service.service.EduSubjectService;
import org.apache.catalina.LifecycleState;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-13
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //1、添加课程分类
    //获取上传过来的文件,把文件内容读取出来,并存储到数据库中,因此需要带上service为参数
    @PostMapping("addSubject")
    public R addStudent(MultipartFile file){
        //上传过来excel文件
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }
    //2、课程分类列表(树型)
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        System.out.println("-----");
        List<OneSubject> list = eduSubjectService.getAllSubject();
        return R.ok().data("list",list);
    }
}

