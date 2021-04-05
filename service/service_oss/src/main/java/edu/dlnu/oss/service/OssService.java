package edu.dlnu.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface OssService {
    String upLoadFileAvatar(MultipartFile file) throws FileNotFoundException;
}
