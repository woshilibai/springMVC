package com.ifunyoung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * springmvc + restful
 */

@Controller
@RequestMapping("/rest")
public class RestController {

    /*
        进入rest请求测试页面
     */
    @RequestMapping("/toRest")
    public String toRest(){
        return "rest";
    }

    /*
        get     查询
        method属性限制该方法只接受get请求
        @PathVariable 获取url占位符的数据填充到方法入参中
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String get(@PathVariable("id") Integer id){
        System.out.println("GET==========" + id);
        return "hello";
    }

    /*
        post    新建
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public String post(@PathVariable("id") Integer id){
        System.out.println("POST==========" + id);
        return "hello";
    }

    /*
        put     更新
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String put(@PathVariable("id") Integer id){
        System.out.println("PUT==========" + id);
        return "hello";
    }

    /*
        delete   删除
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        System.out.println("DELETE==========" + id);
        return "hello";
    }
}
