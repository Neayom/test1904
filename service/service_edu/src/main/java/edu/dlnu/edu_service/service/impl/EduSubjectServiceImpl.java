package edu.dlnu.edu_service.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.edu_service.Listener.SubjectExcelListener;
import edu.dlnu.edu_service.demodata.ExcelListener;
import edu.dlnu.edu_service.demodata.stu;
import edu.dlnu.edu_service.entity.EduSubject;
import edu.dlnu.edu_service.entity.subject.OneSubject;
import edu.dlnu.edu_service.entity.subject.TwoSubject;
import edu.dlnu.edu_service.entity.subjectData;
import edu.dlnu.edu_service.mapper.EduSubjectMapper;
import edu.dlnu.edu_service.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-13
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        System.out.println("进去saveSubject方法中");
        try {
            InputStream in = file.getInputStream();
            System.out.println("得到文件"+file.getOriginalFilename());
            EasyExcel.read(in, subjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
            System.out.println("读取完成");
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(20002,"添加课程分类失败");

        }

    }

    @Override
    public List<OneSubject> getAllSubject() {
        //1、查询出所有的一级分类 parent_id=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> OneSubjects = baseMapper.selectList(wrapperOne);

        //2、查询所有的二级分类 parent_id!=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> TwoSubjects = baseMapper.selectList(wrapperTwo);

        //创建List集合,用于存储最终封装数据
        List<OneSubject> finallSubjectList = new ArrayList<>();
        //3、封装一级分类
        for(EduSubject eduSubject : OneSubjects){
            OneSubject oneSubject = new OneSubject();
           /* String id = eduSubject.getId();
            String title = eduSubject.getTitle();
            oneSubject.setId(id);
            oneSubject.setTitle(title);*/
           BeanUtils.copyProperties(eduSubject,oneSubject);
            finallSubjectList.add(oneSubject);

            //4、封装二级分类,需要再一级分类中进行封装
            List<TwoSubject> twoFinallSubjectList = new ArrayList<>();
            for(EduSubject eduSubject2:TwoSubjects){
                //如果二级的父id是一级的id,则建立关系
                if(eduSubject2.getParentId().equals(oneSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(eduSubject2, twoSubject);
                    twoFinallSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinallSubjectList);
        }


        return finallSubjectList;
    }
}
