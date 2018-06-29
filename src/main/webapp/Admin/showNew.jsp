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
    <link rel="stylesheet" href="../css/showNew.css"/>
    <link rel="stylesheet" href="../css/mess.css"/>
    <style>
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
                 $(function(){
            $("#close").click(function(){
                $("#messdiv").attr("style","display: none");
            })

                    $(".button").click(function(){
                        $("#messdiv").removeAttr("style");
                    })

            $("#checkAll").click(function(){
                $(".checkbox").prop("checked",($("#checkAll").is(':checked')));
            })

            $("#confirm").click(function(){
                var empId = "";
                var b = false;
                $('input[name="empId"]:checked').each(function(){
                    empId = empId + $(this).val() + ",";
                    b = true;
                });
                if(b) {
                    var arg = {"empId" : empId, "message" : $("#message").val()};
                    $.post("/LibrarySingIn-8/Admin/inform", arg, function(data){
                    })
                    alert("正在发送通知,请稍后刷新查看!");
                }
                $("#messdiv").attr("style","display: none");
                return false;
            });



            $("#${depid}").addClass("chick");

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
        <li><a id="0" href="/LibrarySingIn-8/Admin/findNew">全部</a></li>
        <c:forEach items="${dep}" var="d">
            <li><a id="${d.dId}" href="/LibrarySingIn-8/Admin/findNew?dep=${d.dId}">${d.dName}</a></li>
        </c:forEach>
    </ul>
    <ul></ul>
    <ul>
        <li>全选<input type="checkbox" id="checkAll"/></li>
        <li>姓名</li>
        <li>性别</li>
        <li>部门</li>
        <li>班级</li>
        <li>联系方式</li>
    </ul>
    <c:forEach items="${employeePager.result}" var="em">
        <ul>
            <li><input type="checkbox" class="checkbox" name="empId" value="${em.eId}"/></li>
            <li>${em.eName}</li>
            <li>
                <c:if test="${em.eSex.toString() == '1'}">男</c:if>
                <c:if test="${em.eSex.toString() == '2'}">女</c:if>
                <c:if test="${em.eSex.toString() == '3'}">?</c:if>
            </li>
            <li>${em.eDepartment.dName}</li>
            <li>${em.eClazz}</li>
            <li>${em.ePhone}</li>
        </ul>
    </c:forEach>
    <ul class="pager">
        <li>
            <c:if test="${depid > 0}">
                <a href="?pagination=${employeePager.pageNo - 1}&dep=${depid}">上一页</a>
            </c:if>
            <c:if test="${depid == 0}">
                <a href="?pagination=${employeePager.pageNo - 1}">上一页</a>
            </c:if>
            共 ${employeePager.rowCount} 条记录
            共 ${employeePager.pageNumber} 页
            当前第 ${employeePager.pageNo} 页
            每页显示 ${employeePager.pageSize} 条记录
            <c:if test="${depid > 0}">
                <a href="?pagination=${employeePager.pageNo + 1}&dep=${depid}">下一页</a>
            </c:if>
            <c:if test="${depid == 0}">
                <a href="?pagination=${employeePager.pageNo + 1}">下一页</a>
            </c:if>
        </li>
    </ul>
    <button type="button" class="button">通知面试</button>
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
