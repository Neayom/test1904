package edu.dlnu.edu_service.client;

import edu.dlnu.commonutils.R;
import edu.dlnu.edu_service.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod",fallback = VodClientImpl.class) //调用端服务名称
@Component
public interface VodClient {
    //定义调用的方法路径
    //删除阿里云中的视频
    @DeleteMapping("/eduvod/video/DeleteAlyVideo/{videoId}")
    public R DeleteAlyVideo(@PathVariable("videoId") String videoId);

    //删除多个视频的方法
    //3 删除多个阿里云视频的方法
    @DeleteMapping("/eduvod/video/delete-batch")
    public R Delete_Batch(@RequestParam("videoList") List<String> videoList);

}
