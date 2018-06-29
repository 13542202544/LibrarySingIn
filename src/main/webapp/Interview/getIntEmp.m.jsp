<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/9/30
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0"/>
    <title>面试者编号</title>
    <link rel="stylesheet" href="../css/getIntEmp.m.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".button").click(function(){
                var v = $(this).children().attr("value");
                var v2 = $(".number").val();
                $(".number").val(v2 + v);
            })

            $(".submit").click(function(){
                if ($(".number").val().length == 6){
                    $("#form1").submit();
                } else {
                    $(".number").val("");
                }
            })
        })
    </script>
</head>
<body>
<div class="div1">
    <h1>获取面试者</h1>
    <form id="form1" action="/LibrarySingIn-8/Interview/getIntEmp" method="post">
        <%--<input type="hidden" name="number" class="number" value="">--%>
        <input type="text" name="number" class="text" /><br>
        <button type="reset" class="reset">C</button>
        <button type="submit" class="submit">确 &nbsp; 定</button>
    </form>
 <%--   <ul>
        <li>
            <div class="button"><span value="1">1</span></div>
        </li>
        <li>
            <div class="button"><span value="2">2</span></div>
        </li>
        <li>
            <div class="button"><span value="3">3</span></div>
        </li>
        <li>
            <div class="button"><span value="4">4</span></div>
        </li>
        <li>
            <div class="button"><span value="5">5</span></div>
        </li>
        <li>
            <div class="button"><span value="6">6</span></div>
        </li>
        <li>
            <div class="button"><span value="7">7</span></div>
        </li>
        <li>
            <div class="button"><span value="8">8</span></div>
        </li>
        <li>
            <div class="button"><span value="9">9</span></div>
        </li>
        <li>
            <div class="button"><span value="0">0</span></div>
        </li>
        <li>
            <div class="submit"><span value="submit">提交</span></div>
        </li>
    </ul>--%>
</div>
</body>
</html>
