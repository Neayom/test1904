package edu.dlnu.oss.controller;

import edu.dlnu.commonutils.R;
import edu.dlnu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService ;
    //上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file) throws FileNotFoundException {
        //获取上传的文件 MultipartFile
            String url = ossService.upLoadFileAvatar(file);
        System.out.println(url);
        return R.ok().data("url",url);
    }
}
