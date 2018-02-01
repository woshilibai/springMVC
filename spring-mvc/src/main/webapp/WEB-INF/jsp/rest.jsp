<%--
  Created by IntelliJ IDEA.
  User: pc-3
  Date: 2018/1/31
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>rest</title>
</head>
<body>
    <%--GET请求--%>
    <form action="user/1" method="get">
        <input type="submit" value="get请求">
    </form>

    <%--POST请求--%>
    <form action="user/2" method="post">
        <input type="submit" value="post请求">
    </form>

    <%--PUT请求,利用post请求，再在表单里额外传递一个hidden域，name必须为_method,value为put，springmvc的hiddenHttpMethodFilter将会转换该post请求为put请求--%>
    <form action="user/3" method="post">
        <input type="hidden" name="_method" value="put">
        <input type="submit" value="put请求">
    </form>

    <%--DELETE请求,利用post请求，再在表单里额外传递一个hidden域，name必须为_method,value为delete，springmvc的hiddenHttpMethodFilter将会转换该post请求为delete请求--%>
    <form action="user/4" method="post">
        <input type="hidden" name="_method" value="delete">
        <input type="submit" value="delete请求">
    </form>
</body>
</html>
