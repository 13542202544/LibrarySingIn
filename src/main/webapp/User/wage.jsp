<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/7/15
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到详细</title>
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
        function text() {
            alert("test");
            return "1";
        }
        function countTime(start,end) {
            var startTime = start.getTime();
            alert(startTime);
            var endTime = end.getTime();
            var time = endTime - startTime;
            alert(time);
            return time;
        }
    </script>
</head>
<body>
<div class="div1">
    <table class="table" border="1" cellspacing="0">
        <tr>
            <th>工作内容</th>
            <th>签到时间</th>
            <th>结束时间</th>
            <th>工作时长(分钟)</th>
        </tr>
        <c:forEach items="${singInList}" var="s">
            <tr>
                <td>&nbsp;${s.siWorkContent.wcCon}&nbsp;</td>
                <td>&nbsp;${s.siStartTime}&nbsp;</td>
                <td>&nbsp;${s.siEndTime}&nbsp;</td>
                <td>&nbsp;${s.siNotes}&nbsp;</td>
            </tr>
        </c:forEach>
        <tr>
            <th>当月时长</th>
            <td>&nbsp;${totalTime}&nbsp;</td>
            <th>当月工资</th>
            <td>&nbsp;${totalWages}元&nbsp;</td>
        </tr>
    </table>
</div>
</body>
</html>
