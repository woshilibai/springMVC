
package com.ifunyoung.exceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/**
 * 全局异常处理类
 */

//@ControllerAdvice // 表示该bean为全局异常处理类
public class SystemExceptionHandler {
    @ExceptionHandler // 异常处理方法
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        System.out.println("======================全局异常处理类======================");
        return mv;
    }
}

