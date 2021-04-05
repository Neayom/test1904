package edu.dlnu.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.dlnu.commonutils.R;
import edu.dlnu.edu_service.entity.EduTeacher;
import edu.dlnu.edu_service.entity.vo.TeacherQuery;
import edu.dlnu.edu_service.service.EduTeacherService;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-01-30
 */
@Api(description = "讲师管理")
@RestController//Responsbody，用于响应数据，一般返回json,RequestBody使用json传递数据，把json数据封装到对应对象里面，需要改成post提交
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {
    //把service注入
    @Autowired

    private EduTeacherService eduTeacherService;
    //1、查询讲师表所有数据
    //rest风格
    @ApiOperation("查询所有讲师")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    //2、逻辑删除讲师的方法
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        return flag? R.ok():R.error();
    }

    //3、分页查询讲师的方法
    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current",value = "当前页",required = true) @PathVariable Long current,
                             @ApiParam(name = "limit",value = "显示数据",required = true) @PathVariable Long limit){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用方法实现分页
        //调用方法时候，底层封装，把分页所有数据封装到pageTeacher对象里面
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据List集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);

    }
    //4、分页条件查询讲师的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){ //
        //创建page对象
        Page<EduTeacher> page = new Page<>(current,limit);
        //构造条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //wrapper,多条件组合查询
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        Integer level = teacherQuery.getLevel();
        String name = teacherQuery.getName();
        //判断以上四个条件是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)){ //名称是模糊查询
            queryWrapper.like("name",name);//参数：1字段名称2传入的值
        }
        if(!StringUtils.isEmpty(level)){//级别是等于
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){//开始和结束是大于等于和小于等于
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified",end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页结果
        eduTeacherService.page(page,queryWrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }
    //5、添加讲师接口的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        System.out.println("添加讲师");
        boolean save = eduTeacherService.save(eduTeacher);
        return save?R.ok():R.error();
    }
    //6、根据讲师id查询信息(修改时数据回显)
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){ //@PathVariable"获取路径中的参数
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }
    //7、讲师修改功能
    @PostMapping("updateTeacher")
    public R UpdateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean res = eduTeacherService.updateById(eduTeacher);
        return res?R.ok():R.error();
    }

}

