package edu.dlnu.edu_service.demodata;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class testEasyExcel {
   /* public static void main(String[] args) {
        //excel的写操作
        //设置写入的文件路径
        String filename= "F:\\write.xlsx";
        //调用EasyExcel里的方法实现写操作
        //write:第一个参数文件路径名称,第二个参数实体类class
        //sheet
        EasyExcel.write(filename,stu.class).sheet("学生列表").doWrite(getLucy());
    }*/
   public static void main(String[] args) {
       //excel的写操作
       //设置写入的文件路径
       String filename= "F:\\write.xlsx";
       EasyExcel.read(filename,stu.class,new ExcelListener()).sheet().doRead();

   }
    /*private static List<stu> getLucy(){
        List<stu> list = new ArrayList<>();
        for(int i=0;i<10;++i) {
            list.add(new stu(i,"lucy"+i));
        }
        return list;
    }*/
}
