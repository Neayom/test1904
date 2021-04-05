package edu.dlnu.Vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import edu.dlnu.Vod.service.VodService;
import edu.dlnu.Vod.utils.ConstantVodUtils;
import edu.dlnu.Vod.utils.InitVodClient;
import edu.dlnu.commonutils.R;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService ;
    //1 上传视频到阿里云的方法
    @PostMapping("/uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file){
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId",videoId);
    }

    //2 删除阿里云中的视频
    @DeleteMapping("DeleteAlyVideo/{videoId}")
    public R DeleteAlyVideo(@PathVariable String videoId){
        try{
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //像request中设置id
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除失败异常");
        }
    }
    //3 删除多个阿里云视频的方法
    @DeleteMapping("delete-batch")
    public R Delete_Batch(@RequestParam("videoList") List<String> videoList){
        vodService.deleteBatch(videoList);
        return R.ok();
    }
}
