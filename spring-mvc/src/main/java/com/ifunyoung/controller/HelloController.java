package com.ifunyoung.controller;

import com.alibaba.fastjson.JSONObject;
import com.ifunyoung.model.Person;
import com.ifunyoung.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller // 注入bean到spring容器中
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/sayHi")
    public String sayHi(){
        System.out.println("=======sayHi()==========");
        return "hello";
    }

    // 自动匹配请求参数
    @RequestMapping("/person")
    public String toPerson(String name, Integer age, Date birthDay){
        System.out.println(name + ":" + age + ":" + birthDay);
        return "hello";
    }

    // 自动封装请求参数到dto
    @RequestMapping("/personDto")
    public String toPerson(Person person){
        System.out.println(person.getName() + ":" + person.getAge() + ":" + person.getBirthDay());
        return "hello";
    }

    // date类型
    @RequestMapping("/date")
    public String date(Date date){
        System.out.println(date);
        return "hello";
    }

    // 通过map向前台页面传递数据
    @RequestMapping("/showPerson")
    public String showPerson(Map<String, Object> map){
        Person person = new Person();
        person.setName("twl");
        person.setAge(28);
        map.put("person",person);
        return "showPerson";
    }

    //跳到getPerson.jsp,配合演示ajax调用
    @RequestMapping("/toGetPerson")
    public String toGetPerson(){
        return "getPerson";
    }

    // 接受Ajax调用
    @RequestMapping("/getPerson")
    public void getPerson(String name, HttpServletResponse response){
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.write("hello," + name);
    }

    // 返回json类型数据,requestMapping中的属性produces的作用是解决中文乱码问题，但是仅对当前方法有效
    @RequestMapping(value = "/getPerson2", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody // 将内容或对象作为 HTTP 响应正文返回，使用@ResponseBody将会跳过视图处理部分，而是调用适合HttpMessageConverter，将返回值写入输出流。
    public String getPerson2(){
        JSONObject json = new JSONObject();
        json.put("flag",1);
        json.put("msg","你好");
        return json.toJSONString();
    }

    // 配合演示bean2json
    @RequestMapping("/toJson")
    public String toJson(){
        return "json";
    }

    /**
     * 返回json类型DTO，
     * 通过@ResponseBody 将bean转为json，list转为json，map转为json均有异常如下：
     * org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
     * 暂未解决。
      */
    @RequestMapping("/getPerson3")
    @ResponseBody
    public Person getPerson3(){
        Person p = new Person(12,"twl",new Date());
        return p;
    }

    // 使用redirect方式转发请求
    @RequestMapping("/redirect")
    public String redirect(){
        return "redirect:sayHi";
    }

    // 进入上传页面
    @RequestMapping("/toUpload")
    public String toUpload(){
        return "upload";
    }

    // 文件上传,方法请求入参类型必须为MultipartFile，名称和表单的name相同
    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile image, HttpSession session){

        // 上传文件名称
        String originalFilename = image.getOriginalFilename();
        // 当前系统日期时间
        String currDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 上传文件保存路径
        String uploadPath = session.getServletContext().getRealPath("/") + "upload/";
        // 新文件的文件名称格式为：yyyyMMddHHmmss + 原始文件的后缀名
        String newFileName = currDateStr + originalFilename.substring(originalFilename.lastIndexOf('.'));

        File saveFile = new File(uploadPath, newFileName);
        // 判断目录是否存在，不存在，则创建目录
        if (!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdir();
        }
        // 保存文件
        try {
            image.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "hello";
    }

    // ajax 异步上传文件
    @RequestMapping("/ajaxUploadFile")
    @ResponseBody
    public String ajaxUploadFile(MultipartFile image, HttpSession session){

        JSONObject json = new JSONObject();
        // 上传文件名称
        String originalFilename = image.getOriginalFilename();
        // 当前系统日期时间
        String currDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 上传文件保存路径
        String uploadPath = session.getServletContext().getRealPath("/") + "upload/";
        // 新文件的文件名称格式为：yyyyMMddHHmmss + 原始文件的后缀名
        String newFileName = currDateStr + originalFilename.substring(originalFilename.lastIndexOf('.'));

        File saveFile = new File(uploadPath, newFileName);
        // 判断目录是否存在，不存在，则创建目录
        if (!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdir();
        }
        // 保存文件
        try {
            image.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            json.put("flag",0);
            json.put("msg","上传失败");
        }
        json.put("flag",1);
        json.put("msg","上传成功");

        return json.toJSONString();
    }

    /*
        异常处理
        处理局部异常（controller内）
     */
//    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        System.out.println("==================局部异常处理类==================");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("exception",ex);
        return mv;
    }

    // 测试异常
    @RequestMapping("/error")
    public String error(){
        int i = 5/0;
        return "hello";
    }

    // 配合表单验证
    @RequestMapping("toValidate")
    public String toValidate(){
        return "validate";
    }
    /**
     * 表单验证
     * @Validated 表示需要按照实体验证规则进行数据验证
     * BindingResult对象可以获取所有验证异常信息
     * 这个表单验证配置，启动异常，可能是版本问题，暂未解决
     */
    @RequestMapping("/validateUser")
    public ModelAndView validateUser(@Valid User user, BindingResult br){
        ModelAndView mv = new ModelAndView();
        List<ObjectError> allErrors = br.getAllErrors();
        if (allErrors.size() > 0) {
            // 验证失败，进入验证页面，回显验证错误信息
            mv.setViewName("validate");
            FieldError idFieldError = br.getFieldError("id");
            FieldError nameFieldError = br.getFieldError("name");
            FieldError birthFieldError = br.getFieldError("birth");
            if (idFieldError != null){
                mv.addObject("idError",idFieldError.getDefaultMessage());
            }
            if (nameFieldError != null){
                mv.addObject("nameError",nameFieldError.getDefaultMessage());
            }
            if (birthFieldError != null){
                mv.addObject("birthError",birthFieldError.getDefaultMessage());
            }
        }
        // 验证通过
        mv.setViewName("hello");
        return mv;
    }

}
