package edu.dlnu.edu_service.service;

import edu.dlnu.edu_service.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.dlnu.edu_service.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-13
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    //void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);
    //课程分类列表(树形)
    List<OneSubject> getAllSubject();
}
