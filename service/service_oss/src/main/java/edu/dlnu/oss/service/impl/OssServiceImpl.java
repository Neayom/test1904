package edu.dlnu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import edu.dlnu.oss.service.OssService;
import edu.dlnu.oss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upLoadFileAvatar(MultipartFile file) throws FileNotFoundException {
        //上传文件到oss
        String endpoint = ConstantPropertiesUtils.END_POINT;
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String BUCKET_NAME = ConstantPropertiesUtils.BUCKET_NAME;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();

        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        originalFilename = uuid+originalFilename;
        //调用OSS方法实现上传
        //第一个参数 Bucket名称
        //第二个参数 上传到oss文件路径和文件名称
        //第三个参数,上传文件的输入流
        //获取文件名称
        ossClient.putObject(BUCKET_NAME, originalFilename, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
            //把上传之后文件的路径返回
        String url = "https://"+BUCKET_NAME+"."+endpoint+"/"+originalFilename;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
