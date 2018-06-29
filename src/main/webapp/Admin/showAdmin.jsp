<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mobk
  Date: 2016/5/3
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看所有管理员</title>
    <link rel="stylesheet" href="../css/base.css"/>
</head>
<div class="nav">
    <div class="div1 oh">
        <a href="#" class="logo fl"></a>
        <h1 class="fr"> 广东东软学院图书馆</h1>
    </div>
</div><body>
    <table class="table" id="table1" border="1" cellspacing="0">
        <tr>
            <th width="60">姓名</th>
            <th width="80">权限</th>
        </tr>
        <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.name}</td>
                <td>${u.role}</td>
                <td><a href="/LibrarySingIn-8/Admin/deleteAdmin?id=${u.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
