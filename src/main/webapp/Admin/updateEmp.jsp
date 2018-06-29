<%--
  Created by IntelliJ IDEA.
  User: mobk
  Date: 2016/3/28
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看详细信息</title>
    <link rel="stylesheet" href="../css/base.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
                $(function(){
            <c:if test="${type == 'add'}">
            update();
            </c:if>

        })

                function cancel() {
            $("#showDiv").show();
            $("#updateDiv").hide();
        }

                function update(){
            $("#showDiv").hide();
            $("#updateDiv").show();
        }
    </script>
</head>
<body>
<div class="nav">
    <div class="div1 oh">
        <a href="#" class="logo fl"></a>
        <h1 class="fr"> 广东东软学院图书馆</h1>
    </div>
</div>  <div id="showDiv">
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td>工号</td>
                <td>${employee.eId}</td>
            </tr>
            <tr>
                <td>姓名</td>
                <td>${employee.eName}</td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <c:if test="${employee.eSex.toString() == '1'}">男</c:if>
                    <c:if test="${employee.eSex.toString() == '2'}">女</c:if>
                    <c:if test="${employee.eSex.toString() == '3'}">?</c:if>
                </td>
            </tr>
            <tr>
                <td>部门</td>
                <td>${employee.eDepartment.dName}</td>
            </tr>
            <tr>
                <td>学号</td>
                <td>${employee.eNumber}</td>
            </tr>
            <tr>
                <td>级别</td>
                <td>${employee.eId.substring(0,4)}级</td>
            </tr>
            <tr>
                <td>系别</td>
                <td>${employee.eXiBie.xbName}</td>
            </tr>
            <tr>
                <td>班级</td>
                <td>${employee.eClazz}</td>
            </tr>
            <tr>
                <td>联系方式</td>
                <td>${employee.ePhone}</td>
            </tr>
            <tr>
                <td>状态</td>
                <td>${employee.eStatus.sStatus}</td>
            </tr>
        </table>
        <button type="button" onclick="update()">更改</button>
    </div>
    <div id="updateDiv" style="display: none">
        <form:form  action="/LibrarySingIn-8/Admin/saveOrUpdateEmp" method="post" modelAttribute="employee">
            <table border="1" cellspacing="0" cellpadding="0">
                <c:if test="${type == 'show'}">
                    <tr>
                        <td>工号</td>
                        <td>${employee.eId}</td>
                        <input name="eId" type="hidden" value="${employee.eId}"/>
                    </tr>
                </c:if>
                <tr>
                    <td>姓名</td>
                    <td><form:input path="eName"/></td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td><form:select path="eSex">
                        <form:option value="1">男</form:option>
                        <form:option value="2">女</form:option>
                    </form:select></td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td><form:select path="eDepartment.dId" items="${departments}" itemValue="dId" itemLabel="dName"/> </td>
                </tr>
                <tr>
                    <td>学号</td>
                    <td><form:input path="eNumber"/></td>
                </tr>
                <c:if test="${type == 'show'}">
                    <tr>
                        <td>级别</td>
                        <td>${employee.eId.substring(0,4)}级</td>
                    </tr>
                </c:if>
                <tr>
                    <td>系别</td>
                    <td><form:select path="eXiBie.xbId" items="${xiBies}" itemValue="xbId" itemLabel="xbName"/> </td>
                </tr>
                <tr>
                    <td>班级</td>
                    <td><form:input path="eClazz"/></td>
                </tr>
                <tr>
                    <td>联系方式</td>
                    <td><form:input path="ePhone"/></td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td><form:select path="eStatus.sId" items="${status}" itemLabel="sStatus" itemValue="sId"/></td>
                </tr>
            </table>
            <button type="submit">提交</button>
            <c:if test="${type == 'show'}">
                <button type="button" onclick="cancel()">取消</button>
            </c:if>
        </form:form>
    </div>
</body>
</html>
