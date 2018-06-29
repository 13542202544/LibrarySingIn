<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/9/30
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加评分标准</title>
    <link rel="stylesheet" href="../css/base.css"/>
</head>
<body>
<div class="nav">
    <div class="div1 oh">
        <a href="#" class="logo fl"></a>
        <h1 class="fr"> 广东东软学院图书馆</h1>
    </div>
</div>  <div class="div1">
        <ul>
            <li class="oh">
                <div class="fl">项名</div>
                <div class="fl"></div>
            </li>
            <form action="/LibrarySingIn-8/Interview/addGradItem" method="post">
                <li class="oh">
                    <div class="fl"><input type="text" name="itemName" class="itemName" /></div>
                    <div class="fl"><input type="text" name="maxScore" class="maxScore" /></div>
                </li>
                <li><button type="submit">提交</button> </li>
            </form>
        </ul>
    </div>
</body>
</html>
