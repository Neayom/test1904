package edu.dlnu.edu_service.client.impl;

import edu.dlnu.commonutils.R;
import edu.dlnu.edu_service.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientImpl implements VodClient {
    //出错之后才会执行
    //删一个视频,用在添加
    @Override
    @Deprecated //标注的元素已经过时
    public R DeleteAlyVideo(String videoId) {
        return R.error().message("删除视频出错了");
    }
    //删多个视频
    @Override
    public R Delete_Batch(List<String> videoList) {
        return R.error().message("删除视频出错了");
    }
}
