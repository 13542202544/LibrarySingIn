<%--
  Created by IntelliJ IDEA.
  User: mobk
  Date: 2016/3/27
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <link rel="stylesheet" href="../css/base.css"/>
    <style type="text/css">
        .div1 {
            width: 800px;
            margin:0 auto;
            text-align:center;
        }
        a {
            margin: 20px 0;
            display: block;
            text-decoration: none;
            color: skyblue;
        }
        a:link a:visited {
        }
    </style>
</head>
<body>
<div class="nav">
    <div class="div1 oh">
        <a href="#" class="logo fl"></a>
        <h1 class="fr"> 广东东软学院图书馆</h1>
    </div>
</div><div class="div1">
        <a href="/LibrarySingIn-8/Admin/outSingIn">下载签到表</a>
        <a href="/LibrarySingIn-8/Admin/showEmp">查看员工</a>
        <a href="/LibrarySingIn-8/Admin/addAdmin">添加管理员</a>
        <a href="/LibrarySingIn-8/Admin/showAdmin">查看所有管理员</a>
        <a href="/LibrarySingIn-8/Admin/findNew">查看报名新生</a>
        <a href="/LibrarySingIn-8/Admin/informDepart">通知面试</a>
        <a href="/LibrarySingIn-8/Interview">面试主页</a>
    </div>
</body>
</html>
