package edu.dlnu.edu_service.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
//Excel VO
@Data
public class subjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;


}
