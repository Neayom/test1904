package edu.dlnu.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.edu_service.client.VodClient;
import edu.dlnu.edu_service.entity.EduVideo;
import edu.dlnu.edu_service.mapper.EduVideoMapper;
import edu.dlnu.edu_service.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //TODO 删除小节,删除对应视频
    @Autowired
    private VodClient vodClient;
    @Override
    public void removeVideoByCourseId(String courseId) {
        //把课程里所有视频id查出来
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        //得到所有小节对象
        List<EduVideo> eduVideosList = baseMapper.selectList(wrapperVideo);
        List<String> videoList = new ArrayList<>();
        for(EduVideo eduVideo:eduVideosList){
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                videoList.add(videoSourceId);
            }
        }
        if(videoList.size()>0) {
            //多个视频的删除
            vodClient.Delete_Batch(videoList);
        }
        //删除小节
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }
}
