<%--
  Created by IntelliJ IDEA.
  User: pc-3
  Date: 2018/1/30
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
    <script src="../static/js/jquery-1.7.2.js"></script>
    <script src="../static/js/jquery-ui.js"></script>
    <script src="../static/js/jquery.form.js"></script>

    <script type="text/javascript" lang="javascript">
        // 使用jQuery.form.js表单插件提交表单，可以异步上传文件
        $(function() {
            $("#doSave")
                .click(
                    function() {
                        $("#uploadForm")
                            .ajaxSubmit(
                                {
                                    type : 'post',
                                    url : "ajaxUploadFile",
                                    //data:  //用于添加附加数据，注意只要是写在表单里面的，都不需要加这个属性。在controller中可以根据@RequestParam String str获取到属性值。
                                    contentType : "application/x-www-form-urlencoded; charset=utf-8",
                                    dataType : "json",
                                    success: function(obj) {
                                        //接受到的data还只是一个字符串，需要转成json对象
                                        // var obj = JSON.parse(data); // 如果不指定dataType,即返回数据类型，则需要手动转换返回数据字符串到json类型
                                        if(obj.flag==1){
                                            alert(obj.msg);
                                        }else{
                                            alert(obj.msg);
                                        }
                                    },
                                    error: function (obj)//服务器响应失败处理函数
                                    {
                                        alert("出错");
                                    }
                                });
                    });
        });
    </script>
</head>
<body>
    <form id="uploadForm" action="uploadFile" method="post" enctype="multipart/form-data">
        <input name="image" type="file" value="选择文件">
        <input type="submit" value="上传"><br>
        <input type="button" id="doSave" value="异步上传">
    </form>

</body>
</html>
