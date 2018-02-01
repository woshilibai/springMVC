<%--
  Created by IntelliJ IDEA.
  User: pc-3
  Date: 2018/1/31
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>validate</title>
</head>
<body>
    <form action="validateUser" method="get">
        id:     <input type="text" name="id">${idError}<br>
        name:   <input type="text" name="name">${nameError}<br>
        birth:  <input type="text" name="birth">${birthError}<br>
        <input type="submit" value="提交验证">
    </form>
</body>
</html>
