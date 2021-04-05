package edu.dlnu.edu_service.demodata;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<stu> {

    //一行一行的去读取excel内容

    @Override
    public void invoke(stu stu, AnalysisContext analysisContext) {
        System.out.println("***"+stu);
    }

    @Override
    //读取表头内容
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }
    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
