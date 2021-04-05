package edu.dlnu.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.dlnu.commonutils.R;
import edu.dlnu.edu_service.client.VodClient;
import edu.dlnu.edu_service.entity.EduVideo;
import edu.dlnu.edu_service.service.EduVideoService;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-16
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private VodClient vodClient;
    //新增小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        boolean save = eduVideoService.save(eduVideo);
        return save?R.ok():R.error();
    }
    //删除小节
    //TODO 完善:删除小节时,同时把阿里云中的视频删除掉,按照微服务方式删除
    @DeleteMapping("deleteVideo/{VideoId}")
    public R deleteVideo(@PathVariable  String VideoId){
        //先删阿里云中的视频,再删除小节信息
        //根据video的id获取到video_source_id
        EduVideo eduVideo = eduVideoService.getById(VideoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)) {
            //删除阿里云中的视频
            R result = vodClient.DeleteAlyVideo(videoSourceId);
            if(result.getCode()==20001){
                throw new GuliException(20001,"删除视频失败,熔断器....");
            }
        }
        //删除小节信息
        boolean b = eduVideoService.removeById(VideoId);
        return b?R.ok():R.error();
    }
    //修改小节,根据id修改
    @PostMapping("UpdateVideo/{videoId}")
    public R UpdateVideo(@RequestBody EduVideo eduVideo,@PathVariable String videoId){
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",videoId);
        boolean update = eduVideoService.update(eduVideo, queryWrapper);
        return update?R.ok():R.error();
    }
}

