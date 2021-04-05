package edu.dlnu.Vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import edu.dlnu.Vod.service.VodService;
import edu.dlnu.Vod.utils.ConstantVodUtils;
import edu.dlnu.Vod.utils.InitVodClient;
import edu.dlnu.commonutils.R;
import edu.dlnu.servicebase.ExceptionHandler.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {

            //inputStream 上传文件的输入流
            InputStream inputStream = file.getInputStream();
            //初始文件名称
            String fileName = file.getOriginalFilename();
            System.out.println("++++++"+fileName);
            //上传之后显示名称
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteBatch(List videoList) {
        try{
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //像request中设置id
            //1,2,3
            request.setVideoIds(StringUtils.join(videoList.toArray(),","));
            client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除失败异常");
        }
    }
}
