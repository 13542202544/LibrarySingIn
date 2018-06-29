<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/7/17
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>请假</title>
    <style type="text/css">
        .div1 {
            width: 800px;
            margin:0 auto;
            text-align:center;
        }
        .table {
            margin:0 auto;
            text-align:center;
        }
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
    <table class="table" border="0" cellspacing="0">
        <form action="/LibrarySingIn-8/User/vacation" method="post">
            <tr>
                <td colspan="2">
                    <select name="work">
                        <option value="0">选择请假时间</option>
                        <c:forEach items="${works}" var="w">
                            <option value="${w.id}">${w.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>请假原因:</td>
                <td><input type="text" name="cause" id="cause"/></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit">提交</button></td>
            </tr>
        </form>
    </table>
</body>
</html>
