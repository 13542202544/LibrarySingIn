<%--
  Created by IntelliJ IDEA.
  User: mobk
  Date: 2016/3/22
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
    this is security Admin
    <a href="/LibrarySingIn-8/Admin/testDownload">下载测试</a>
    <sec:authorize access="hasRole('ROLE_SUPER')">
        <br>this is security Super
    </sec:authorize>
</body>
</html>
