package edu.dlnu.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.edu_service.entity.EduChapter;
import edu.dlnu.edu_service.entity.EduVideo;
import edu.dlnu.edu_service.entity.chapter.VideoVo;
import edu.dlnu.edu_service.entity.chapter.chapterVo;
import edu.dlnu.edu_service.mapper.EduChapterMapper;
import edu.dlnu.edu_service.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.dlnu.edu_service.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<chapterVo> getChapterVideoByCourseId(String courseId) {

        //查询章节
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id",courseId);
        List<EduChapter> EduChapterlist = baseMapper.selectList(queryWrapper);
        //查询小节
        QueryWrapper queryVideoWrapper = new QueryWrapper();
        queryVideoWrapper.eq("course_id",courseId);
        List<EduVideo> EduVideolist = eduVideoService.list(queryVideoWrapper);

        //创建list集合,用于最终封装数据
        List<chapterVo> finalList =  new ArrayList<>();
        //遍历章节
        for(EduChapter eduChapter :EduChapterlist){
            chapterVo chapterVo = new chapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);
            //查询小节,需要在章节中分类
            List<VideoVo> resVideoList = new ArrayList<>();
            //遍历小节
            for(EduVideo videoVoSource:EduVideolist){
                //判断小节里的Chapterid与章节id是否对应
                if(videoVoSource.getChapterId().equals(chapterVo.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(videoVoSource,videoVo);
                    resVideoList.add(videoVo);
                }
            }
            //把封装之后小节list集合,放到章节对象里面
            chapterVo.setChildren(resVideoList);
        }
        return finalList;




    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }

}
