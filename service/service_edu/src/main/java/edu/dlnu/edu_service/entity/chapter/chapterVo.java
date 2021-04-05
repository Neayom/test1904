package edu.dlnu.edu_service.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class chapterVo {
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
