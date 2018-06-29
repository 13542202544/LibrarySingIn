<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/9/30
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>评分</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0"/>
    <link rel="stylesheet" href="../css/base.m.css"/>
    <link rel="stylesheet" href="../css/geaded.m.css"/>
    <link rel="stylesheet" href="../css/mess.m.css"/>
    <style>
        .list .error {
            border: 2px solid red;
        }
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        $(function () {
            $("form").submit(function() {
                var b = true;
                $('.score').each(function () {
                    var v1 = $(this).next().val();
                    var v2 = $(this).val();
                    if (v2 > v1) {
                        b = false;
                    }
                })
                return b;
            })
            $(".score").change(function(){
                var v1 = $(this).next().val()/10;
                var v2 = $(this).val()/10;
                if ( v2 > v1 ) {
                    $(this).addClass("error");
                } else {
                    $(this).removeClass("error");
                }
            })

            /**
             * 开启窗口
             */
            $("#mes").click(function(){
                $("#messdiv").removeAttr("style");
                return false;
            })

            /**
             * 关闭窗口
             */
            $("#close").click(function(){
                $("#messdiv").attr("style","display: none");
            })
        })
    </script>
</head>
<body>
    <div class="div1">
        <h1>面试评分</h1>
        <h2>${interSI.number}号 ${interSI.employee.eName}<a href="#" id="mes">详细</a> 面试评分表</h2>
        <p>面试官:${user.eName}</p>
        <form action="/LibrarySingIn-8/Interview/graded" method="post">
            <ul class="list">
                <input type="hidden" name="empId" value="${interSI.employee.eId}">
                <input type="hidden" name="userId" value="${user.eId}">
                <c:forEach items="${gradItem}" var="g">
                    <li class="oh" id="grad">
                        <div class="fl">${g.itemName}(${g.maxScore}分):</div>
                        <input type="hidden" name="itemId" class="itemId" value="${g.id}">
                        <div class="fl">
                            <input type="text" name="score" class="score" onkeyup='this.value=this.value.replace(/\D/gi,"")'/>
                            <input type="hidden" class="maxSc" value="${g.maxScore}">
                        </div>
                    </li>
                </c:forEach>
                <li class="oh" id="grad">
                    <div class="fl">备 &nbsp; 注:</div>
                    <input type="hidden">
                    <div class="fl"><input type="text" name="note" class="note"/></div>
                </li>
                <li><button type="submit" class="button">提 &nbsp; 交</button></li>
            </ul>
        </form>
    </div>

    <div id="messdiv" style="display: none">
        <div id="mess">
            <div class="hurdle oh">
                <p id="close" class="fr">X</p>
            </div>
            <div class="content">
                <p>编号:${interSI.number}号</p>
                <p>姓名:${interSI.employee.eName}</p>
                <p>
                    性别:
                    <c:if test="${interSI.employee.eSex.toString() == '1'}">男</c:if>
                    <c:if test="${interSI.employee.eSex.toString() == '2'}">女</c:if>
                    <c:if test="${interSI.employee.eSex.toString() == '3'}">?</c:if>
                </p>
                <p>电话:${interSI.employee.ePhone}</p>
                <p>部门:${interSI.employee.eDepartment.dName}</p>
                <p>系别:${interSI.employee.eXiBie.xbName}</p>
                <p>班级:${interSI.employee.eClazz}</p>
            </div>
        </div>
    </div>
</body>
</html>
