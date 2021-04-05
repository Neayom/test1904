package edu.dlnu.edu_service.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用于课程查询封装信息
 */
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery implements Serializable {



    @ApiModelProperty(value = "课程名称")
    @JsonProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "课程状态")
    @JsonProperty(value = "status")
    private String status;


}