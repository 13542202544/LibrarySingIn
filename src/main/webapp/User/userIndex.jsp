<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/7/15
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户主页</title>
    <style type="text/css">
        .div1 {
            width: 800px;
            margin:0 auto;
            text-align:center;
        }
    </style>
</head>
<body>
<div class="div1">
    <a href="/LibrarySingIn-8/User/vacation">请假</a>
    <table class="table" border="1" cellspacing="0">
        <c:forEach items="${works}" var="w">
            <tr>
                <c:forEach items="${w}" var="e">
                    <td>${e}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
