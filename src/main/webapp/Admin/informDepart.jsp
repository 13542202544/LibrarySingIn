<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/9/14
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新生报名</title>
    <link rel="stylesheet" href="../css/base.css"/>
    <link rel="stylesheet" href="../css/informDepart.css"/>
    <link rel="stylesheet" href="../css/mess.css"/>
    <style>
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
     <script type="text/javascript">
        $(function(){

            var depId = 0;

            $("a").click(function(){
                return false;
            })

            $("#confirm").click(function(){
                var b = false;
                if (depId != 0) b = true;
                if(b) {
                    var arg = {"depId" : depId, "message" : $("#message").val()};
                    $.post("/LibrarySingIn-8/Admin/informDep", arg, function(data){
                    })
                    alert("正在发送通知,请稍后刷新查看!");
                }
                $("#messdiv").attr("style","display: none");
                depId = 0;
                return false;
            });

            /**
             * 窗口中的信息
             */
            $("#message").bind('input propertychange', function() {
                if($("#message").val().length > 57){
                    $(this).val($(this).val().substring(0,58));
                }
                var v = $("#message").val();
                var n = 58 - v.length;
                $("#surplus").text(n);
                if (n < 10) {
                    $("#sum").attr("style","color:red");
                } else {
                    $("#sum").removeAttr("style");
                }
            })

            /**
             * 开启窗口
             */
            $(".button").click(function(){
                depId = $(this).attr("id");
                $("#messdiv").removeAttr("style");
            })

            /**
             * 关闭窗口
             */
            $("#close").click(function(){
                $("#messdiv").attr("style","display: none");
                depId = 0;
            })
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
    <ul class="first">
        <c:forEach items="${dep}" var="d">
            <li><a id="${d.dId}" class="button" href="#?dep=${d.dId}">${d.dName}</a></li>
        </c:forEach>
    </ul>
</div>

<div id="messdiv" style="display: none">
    <div id="mess">
        <div class="hurdle oh">
            <p id="close" class="fr">X</p>
        </div>
        <div class="content">
            <h3>通知短信内容:</h3>
            <textarea id="message"></textarea>
            <span id="sum" class="fr">58/<span id="surplus">58</span></span>
        </div>
        <div class="messbot">
            <button id="confirm" type="button">确定</button>
            <button id="cancel" type="button">取消</button>
        </div>
    </div>
</div>
</body>
</html>
