<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: mobk
  Date: 2016/3/29
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加管理员</title>
    <link rel="stylesheet" href="../css/base.css"/>
    <style type="text/css">
        .nostB {
            color: #FF0000;
            font-weight: bold;
        }
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
                $(function(){
        })
//            $(".button").click(function(){
//                alert("!");
//                $(".nostB").remove();
//                if ($("#pw").val() != $("#cpw").val()) {
//                    $("#cpw").after("<b class='nostB'>密码不一致</b>");
//                    return false;
//                }
//            })

                function button(){
            alert("!");
            $(".nostB").remove();
            if ($("#pw").val() != $("#cpw").val()) {
                    $("#cpw").after("<b class='nostB'>密码不一致</b>");
                    return false;
                }
        }
    </script>
</head>
<div class="nav">
    <div class="div1 oh">
        <a href="#" class="logo fl"></a>
        <h1 class="fr"> 广东东软学院图书馆</h1>
    </div>
</div><body>
    <form:form action="/LibrarySingIn-8/Admin/addAdmin" modelAttribute="user" method="post">
        账号:<form:input path="name"/><br>
        密码:<form:password path="password" id="pw"/><br>
        确认密码:<input type="password" id="cpw"><br>
        <button type="submit" onclick="button()">提交</button>
    </form:form>
</body>
</html>
