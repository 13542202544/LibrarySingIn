<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Generator" content="EditPlus®">
    <meta name="Author" content="">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <title>签到系统</title>
    <link rel="stylesheet" href="css/base.css"/>
    <script type="text/javascript" src="scripts/jquery-1.12.0.js"></script>
    <style type="text/css">
        .div1 {
            width: 800px;
            margin:0 auto;
            text-align:center;
        }
        .head {
            background-color: #55a7ff;
            border-bottom: 3px solid #0d09ff;
        }
        .button {
            width: 100px;
        }
        .table {
            margin:0 auto;
            text-align:center;
        }
        .div1 a {
            display: block;
            line-height: 40px;
            padding: 0 6px;
            margin: 0 10px;
            background-color: #4a80ff;
        }
    </style>
    <script type="text/javascript">

        /**
         * jQuery 主函数
         */
        $(function(){
            refresh();
            refreshIn();
        })

        /**
         * 刷新需要上班的人
         */
        function refresh(){
            $.post("/LibrarySingIn-8/System/findDutyEmp", null, function(json){
                $(".table1_td").parent().remove();
                for (var i = 0; i < json.length; i++){
                    $("<tr></tr>").append("<td class='table1_td'>" + json[i].wEmployee.eName + "</td>")
                            .append("<td>" + json[i].wEmployee.eId + "</td>")
                            .append("<td>" + json[i].wWorkContent.wcCon + "</td>")
                            .append("<td><button id='" + json[i].wEmployee.eId + "' value='" + json[i].wWorkContent.wcId + "' name='SingIn/startTime' class='singIn'>签到</button></td>")
                            .append("<td><button onclick='replace(" + json[i].wEmployee.eId + "," + json[i].wWorkContent.wcId + ")'>替班</button></td>")
                            .append("<td><button onclick=\"phone(\'" + json[i].wEmployee.ePhone + "\')\">联系</button></td>")
                            .appendTo("#table1");
                }
                $(".singIn").click(function(){
                    singIn(this);
                });
            },"json");
            //休眠后调用方法
            setTimeout(refresh, 30000);
        }

        /**
         * 刷新正在上班的人
         */
        function refreshIn(){
            $.post("/LibrarySingIn-8/System/findEndDutyEmp", null, function(json){
                $(".table2_td").parent().remove();
                for (var i = 0; i < json.length; i++){
                    $("<tr></tr>").append("<td class='table2_td'>" + json[i].siEmployee.eName + "</td>")
                            .append("<td>" + json[i].siEmployee.eId + "</td>")
                            .append("<td>" + json[i].siWorkContent.wcCon + "</td>")
                            .append("<td>" + json[i].siStartTime + "</td>")
                            .append("<td><button id='" + json[i].siId + "' value='" + json[i].siWorkContent.wcId + "' name='SingIn/endTime' class='singOut'>下班</button></td>")
                            .append("<td><button onclick=\"phone(\'" + json[i].siEmployee.ePhone + "\')\">联系</button></td>")
                            .appendTo("#table2");
                }
                $(".singOut").click(function(){
                    singIn(this);
                });
            },"json");
            //休眠后调用方法
            setTimeout(refreshIn, 30000);
        }

        /**
         * 提示电话
         * @param phone
         */
        function phone(phone) {
            alert("请拨打:" + phone + "提醒他!!!");
            return false;
        }

        /**
         * 签到
         * @param nost
         */
        function singIn(nost){
            var id = nost.id;
            var con = nost.value;
            var arg = {"userID": id, "con": con};
            //设置ajax请求为同步执行
            $.ajaxSettings.async = false;
            $.post(nost.name, arg, function(data){
                if(data == true){
                    alert("签到成功!!");
                    $("#" + id).parent().parent().remove();
                }else{
                    alert(data);
                }
            });
            refresh();
            refreshIn();
            //设置ajax请求为异步
            $.ajaxSettings.async = true;
        }

        /**
         *代替上班
         */
        function replace(id,con) {
            window.open("/LibrarySingIn-8/SingIn/replace?id=" + id + "&con=" + con);
        }

        /**
         * 临时上班
         */
        function temporary() {
            window.open("/LibrarySingIn-8/SingIn/temporary?con=5");
        }

    </script>
</head>
<body>
    <div class="div1 oh head">
        <a class="fl db" href="/LibrarySingIn-8/Admin">后台</a>
        <a class="fl db" href="/LibrarySingIn-8/User/apply">新生报名</a>
        <a class="fr db" href="/LibrarySingIn-8/j_spring_security_logout">注销</a>
    </div>
    <div class="div1">
        <h1>目前时间需要上班的人</h1><br>
        <button type="button" onClick='refresh()' class="button" >刷新</button><br><br>
        <table class="table" id="table1" border="1" cellspacing="0">
            <tr>
                <th width="60">姓名</th>
                <th width="80">工号</th>
                <th width="80">工作</th>
                <th width="40">签到</th>
                <th width="80">替他上班</th>
                <th width="80">找不到人</th>
            </tr>
        </table>
        <br>
        <button onclick="temporary()">临时上班</button>
    </div>
    <div class="div1">
        <h1>目前时间需要上班的人</h1><br>
        <button type="button" onClick='refreshIn()' class="button" >刷新</button><br><br>
        <table class="table" id="table2" border="1" cellspacing="0">
            <tr>
                <th width="60">姓名</th>
                <th width="80">工号</th>
                <th width="80">工作</th>
                <th width="80">上班时间</th>
                <th width="40">下班</th>
                <th width="80">找不到人</th>
            </tr>
        </table>
    </div>
</body>
</html>