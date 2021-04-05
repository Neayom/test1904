package edu.dlnu.edu_service.service;

import edu.dlnu.edu_service.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.dlnu.edu_service.entity.chapter.chapterVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
public interface EduChapterService extends IService<EduChapter> {
    //根据课程id进行小节查询
    List<chapterVo> getChapterVideoByCourseId(String courseId);

    void removeChapterByCourseId(String courseId);
}
