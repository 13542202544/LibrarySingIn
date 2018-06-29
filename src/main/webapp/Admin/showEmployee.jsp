<%--
  Created by IntelliJ IDEA.
  User: mobk
  Date: 2016/3/27
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工列表</title>
    <link rel="stylesheet" href="../css/base.css"/>
    <link rel="stylesheet" href="../css/showEmployee.css"/>
<%--
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
--%>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        $(function(){

            var linkU = $("#cutU").attr("href");
            var hrefU = window.location.href;
            linkU = linkU + hrefU.substr(hrefU.indexOf('&')+1, hrefU.length);
            $("#cutU").attr("href", linkU);

            var linkD = $("#cutD").attr("href");
            var hrefD = window.location.href;
            linkD = linkD + hrefD.substr(hrefD.indexOf('&')+1, hrefD.length);
            $("#cutD").attr("href", linkD);

            $("#checkAll").click(function(){
                $(".checkbox").prop("checked",($("#checkAll").is(':checked')));
            })

            $(".button").click(function(){
                var empId = "";
                var b = false;
                $('input[name="empId"]:checked').each(function(){
                    empId = empId + $(this).val() + ",";
                    b = true;
                });
                if(b) {
                    var arg = {"statusId" : this.value , "empId" : empId};
                    $.post("/LibrarySingIn-8/Admin/updateStatus", arg, function(data){
                        if(data){
                            window.location.reload();
                        } else {
                            alert("失败,请重试!");
                        }
                    })
                }
                return false;
            });

        })
    </script>
</head>
<body>
    <div class="div1">
    <ul></ul>
    <ul>
        <div class="center oh">
            <li>全选<input type="checkbox" id="checkAll"/></li>
            <li>姓名</li>
            <li>性别</li>
            <li>部门</li>
            <li>系部</li>
            <li>班级</li>
            <li>联系方式</li>
            <li>操作</li>
        </div>
    </ul>
    <c:forEach items="${employeePager.result}" var="em">
        <ul>
            <div class="center oh">
                <li><input type="checkbox" class="checkbox" name="empId" value="${em.eId}"/></li>
                <li>${em.eName}</li>
                <li>
                    <c:if test="${em.eSex.toString() == '1'}">男</c:if>
                    <c:if test="${em.eSex.toString() == '2'}">女</c:if>
                    <c:if test="${em.eSex.toString() == '3'}">?</c:if>
                </li>
                <li>${em.eDepartment.dName}</li>
                <li>${em.eXiBie.xbName}</li>
                <li>${em.eClazz}</li>
                <li>${em.ePhone}</li>
                <li><a href="/LibrarySingIn-8/Admin/fileEmployee?id=${em.eId}" target="_blank">编辑</a></li>
            </div>
        </ul>
    </c:forEach>
    <ul class="pager">
        <div class="center oh">
            <li>
                <a id="cutU" href="?pagination=${employeePager.pageNo - 1}&">上一页</a>
                共 ${employeePager.rowCount} 条记录
                共 ${employeePager.pageNumber} 页
                当前第 ${employeePager.pageNo} 页
                每页显示 ${employeePager.pageSize} 条记录
                <a id="cutD" href="?pagination=${employeePager.pageNo + 1}&">下一页</a>
            </li>
        </div>
    </ul>

    </div>
</body>
</html>
