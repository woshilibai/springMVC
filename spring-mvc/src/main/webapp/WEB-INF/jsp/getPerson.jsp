<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>getPerson</title>
    <script src="../static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" lang="javascript">
        $(function () {
            $("#btn").click(function () {
                var url = "getPerson";
                var params = {name:$("#name").val()};
                $.post(url,params,function(data){
                    alert(data);
                });
            });

            // ajax请求，返回json类型数据
            $("#btnjson").click(function () {
                var url = "getPerson2";
                var params = {};
                $.post(url,params,function(data){
                    alert(data);
                    // 将字符串转换为json类型数据
                    var jsonData = JSON.parse(data);
                    alert(jsonData.flag + "-" + jsonData.msg);
                });
            });
        });

    </script>
</head>
<body>
    name:<input id="name" type="text"><br>
    <input id="btn" type="button" value="getPerson">
    <input id="btnjson" type="button" value="jsonGetPerson">
</body>
</html>
