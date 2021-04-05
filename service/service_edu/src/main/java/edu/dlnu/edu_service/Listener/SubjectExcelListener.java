package edu.dlnu.edu_service.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.edu_service.entity.EduSubject;
import edu.dlnu.edu_service.entity.subjectData;
import edu.dlnu.edu_service.service.EduSubjectService;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<subjectData> {
    //1、因为SubjectExcelListener不能交给spring进行管理,需要自己new,所以不能注入其他对象
    //为了实现数据库操作
    public EduSubjectService subjectService;

    public SubjectExcelListener() {}
    //创建有参数构造，传递subjectService用于操作数据库
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @Override
    public void invoke(subjectData subjectData, AnalysisContext analysisContext) {
        System.out.println(subjectData);

        if(subjectData ==null){

            throw new GuliException(20001,"文件数据为空");
            //一行一行读,第一个值一级分类,第二个值二级分类
        }
        EduSubject eduSubjectOne = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if(eduSubjectOne==null){
            eduSubjectOne = new EduSubject();
            eduSubjectOne.setParentId("0");

            eduSubjectOne.setTitle(subjectData.getOneSubjectName());

            subjectService.save(eduSubjectOne);
        }
        //判断二级分类
        //首先取得其一级分类的id值
        String pid = eduSubjectOne.getId();
        EduSubject eduSubjectTwo = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(eduSubjectTwo==null){
             eduSubjectTwo = new EduSubject();
            eduSubjectTwo.setParentId(pid);

            eduSubjectTwo.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(eduSubjectTwo);
        }
    }
    //判断一级分类不能重复添加(数据库判别是否存在的方式:先查一遍表,如果查的结果为null,则不存在)
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = subjectService.getOne(wrapper);
        return one;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject two = subjectService.getOne(wrapper);
        return two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
