package edu.dlnu.edu_service.demodata;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class stu {
    //设置excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private String sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;


}
