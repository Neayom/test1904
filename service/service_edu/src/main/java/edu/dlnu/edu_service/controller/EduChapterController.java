package edu.dlnu.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.commonutils.R;
import edu.dlnu.edu_service.entity.EduChapter;
import edu.dlnu.edu_service.entity.EduVideo;
import edu.dlnu.edu_service.entity.chapter.chapterVo;
import edu.dlnu.edu_service.service.EduChapterService;
import edu.dlnu.edu_service.service.EduVideoService;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduVideoService eduVideoService;

    //课程大纲列表,根据课程Id 进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<chapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }
    //章节的添加
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        System.out.println("正在添加章节");
        boolean save = eduChapterService.save(eduChapter);
        return save?R.ok():R.error();
    }
    //根据id查询
    @GetMapping("getChapterInfo/{courseId}")
    public R getChapterInfo(@PathVariable String courseId){
        EduChapter eduChapter = eduChapterService.getById(courseId);
        return R.ok().data("eduChapter",eduChapter);
    }
    //章节的修改
    @PostMapping("upDateChapter")
    public R upDateChapter(@RequestBody EduChapter eduChapter){

        boolean b = eduChapterService.updateById(eduChapter);
        return b?R.ok():R.error();
    }
    //章节的删除,当章节下面有小节,则不允许删除
    @DeleteMapping("deleteChapter/{Chapterid}")
    public R deleteChapter(@PathVariable  String Chapterid){
        //如果此chapterid下还有小节,则不删除
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",Chapterid);
        int count = eduVideoService.count(queryWrapper);
        if(count>0){
           throw new GuliException(20001,"存在小节,请先删除小节");
        }else {
            boolean b = eduChapterService.removeById(Chapterid);
            return b? R.ok():R.error();
        }
    }

}

