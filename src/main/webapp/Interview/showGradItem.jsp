<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/9/30
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评分项目</title>
    <link rel="stylesheet" href="../css/base.css"/>
    <style>
        a {
            background-color: #ddd;
            border: 1px solid #aaa;
        }
        a:hover {
            background-color: #eee;
        }
        a:active {
            background-color: #888;
        }
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".del").click(function(){
                return false;
            });

            $(".del").click(function(){
                alert(this.href);
                $.post(this.href,null,function(data){
                    if (data) {
                        window.location.reload();
                    }
                })
            });
        })
    </script>
</head>
<body>
    <div class="nav">
        <div class="div1 oh">
            <a href="#" class="logo fl"></a>
            <h1 class="fr"> 广东东软学院图书馆</h1>
        </div>
    </div>
    <div class="div1">
        <h3>评分列</h3>
        <table class="center">
            <tr>
                <td>项名</td>
                <td>分数</td>
                <td></td>
            </tr>
            <c:forEach items="${gradeds}" var="g">
                <tr>
                    <td>${g.itemName}</td>
                    <td>${g.maxScore}</td>
                    <td><a class="del" href="/LibrarySingIn-8/Interview/delGradItem?id=${g.id}">删除</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3"><a href="/LibrarySingIn-8/Interview/addGradItem">添加</a></td>
            </tr>
        </table>
    </div>
</body>
</html>
