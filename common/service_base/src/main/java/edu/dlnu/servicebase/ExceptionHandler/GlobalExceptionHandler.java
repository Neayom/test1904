package edu.dlnu.servicebase.ExceptionHandler;

import edu.dlnu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler { //不管什么异常，都能执行，避免500
    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public R Error(){
        return R.error().message("进入全局异常处理器处理");
    }


    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public R Error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("ArithmeticException处理");
    }

    //自定义异常处理
    @ExceptionHandler(GuliException.class)
    @ResponseBody//为了返回数据
    public R GuliExceptionError(GuliException e){
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
