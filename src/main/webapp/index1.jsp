<%--
  Created by IntelliJ IDEA.
  User: mobk
  Date: 2016/4/10
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到</title>
    <style type="text/css">
        .div1 {
            width: 800px;
            margin:0 auto;
            text-align:center;
        }
        .button {
            width: 100px;
        }
        .table {
            margin:0 auto;
            text-align:center;
        }
    </style>
    <script type="text/javascript" src="/scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        $(function(){

        })

        function select(){
            var v = {"phone":$("#phone").val()}
            $.post("/TestCon/select", v, function(json){
                $(".table1_td").parent().remove();
                for (var i = 0; i < json.length; i++){
                    alert(json[i].sing);
                    if(json[i].sing == '0'){
                        $("<tr></tr>").append("<td class='table1_td'>" + json[i].teamName + "</td>")
                                .append("<td>" + json[i].captain + "</td>")
                                .append("<td>" + json[i].people + "</td>")
                                .append("<td>" + json[i].member + "</td>")
                                .append("<td>" + json[i].phone + "</td>")
                                .append("<td id='tdid'><button id='" + json[i].id + "' name='TestCon/singIn' class='singIn'>签到</button></td>")
                                .appendTo("#table1");
                    } else {
                        $("<tr></tr>").append("<td class='table1_td'>" + json[i].teamName + "</td>")
                                .append("<td>" + json[i].captain + "</td>")
                                .append("<td>" + json[i].people + "</td>")
                                .append("<td>" + json[i].member + "</td>")
                                .append("<td>" + json[i].phone + "</td>")
                                .append("<td id='tdid'>" + json[i].sing + "</td>")
                                .appendTo("#table1");
                    }
                }
                $(".singIn").click(function(){
                    singIn(this);
                });
            },"json")
        }

        function singIn(nost){
            var id = nost.id;
            var arg = {"id": id};
            //设置ajax请求为同步执行
            $.ajaxSettings.async = false;
            $.post(nost.name, arg, function(data){
                $("<p>" + data + "</p>").appendTo("#tdid");
                $(".singIn").remove();
                alert("签到成功,编号为" + data + "组");
            });
            //设置ajax请求为异步
            $.ajaxSettings.async = true;
        }
    </script>
</head>
<body>
    <div class="div1">
        电话号码:<input type="text" name="phone" id="phone"/>
        <button type="button" onclick="select()">查找</button>
        <br><br><br>
        <table class="table" id="table1" border="1" cellspacing="0">
            <tr>
                <th>队名</th>
                <th>队长</th>
                <th>人数</th>
                <th>队员</th>
                <th>联系方式</th>
                <th>签到</th>
            </tr>
        </table>
    </div>
</body>
</html>
