package com.ifunyoung.controller;

import com.ifunyoung.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Map;

/**
 * 演示@ModelAttribute常见用法
 */
@Controller
@SessionAttributes("sessionUser") // 表示controller内共享model的sessionUser
@RequestMapping("/modelAttr")
public class ModelAttributeController {

    /**
     * @ModelAttribute 注解方法
     * 该类方法会在controller里所有handler方法（带有@RequestMapping）执行之前，执行
     * 等价于model.put("方法返回类型首字母小写",方法返回值)
     */
    @ModelAttribute // 等价于model.put("string","hello")
    public String getPerson(){
        return "hello";
    }

    @ModelAttribute // 等价于model.put("person",方法返回的person实例)
    public Person getPerson1(){
        return new Person(34,"abc",new Date());
    }

    @ModelAttribute("myPerson") // 指定了key，等价于model.put("myPerson",方法返回的person实例)
    public Person getPerson3(){
        return new Person(12,"twl",new Date());
    }

    /**
     * @ModelAttribute 注解方法参数
     * 会从model里获取指定key的value，然后将引用付给该方法形参
     */
    @RequestMapping("/getPerson4")
    public String getPerson4(@ModelAttribute Person person){
        System.out.println("====================" + person);
        return "hello";
    }

    @RequestMapping("/getPerson5")
    public String getPerson5(@ModelAttribute("myPerson") Person person){
        System.out.println("====================" + person);
        return "hello";
    }

    /**
     * 测试@SessionAttribute
     */
    @RequestMapping("/putUser")
    public String putUser(Map<String, Object> model, SessionStatus sessionStatus){
        Person  p = new Person(22,"ifunyoung",new Date());
        model.put("sessionUser",p);
        // 清除model属性
//        sessionStatus.setComplete();
        return "hello";
    }

    @RequestMapping("/getUser")
    public String getUser(Map<String,Object> model){
        Person p = (Person)model.get("sessionUser");
        System.out.println("====================" + p);
        return "hello";
    }
}
