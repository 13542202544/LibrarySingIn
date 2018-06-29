<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/9/30
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到</title>
    <link rel="stylesheet" href="../css/base.css"/>
    <link rel="stylesheet" href="../css/SI.css"/>
    <link rel="stylesheet" href="../css/mess.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        var empId = 0;
        $(function(){
            $(".phone").submit(function(){return false;})
            $(".phone").submit(function(){
                var phone = $.trim($("#phone").val());
                if (phone.length != 11){
                    alert("请正确输入11位手机号码!");
                    $("#phone").val("");
                    return false;
                }
                $.post("/LibrarySingIn-8/Interview/getEmp",{"phone" : phone},function(json){
                    $(".table1_td").parent().remove();
                    for (var i = 0; i < json.length; i++){
                        var sex;
                        if (json[i].eSex == 1) sex = "男"; else if(json[i].eSex == 2) sex = "女"; else sex = "?";
                        var none;
                        if (json[i].interviewSI == null) none = "<button id='" + json[i].eId + "' onclick=\"SI(\'" + json[i].eId + "\')\">签到</button>";
                        else none = "<span class='red'>" + json[i].interviewSI.number + "</span>";
                        $("<tr></tr>").append("<td class='table1_td'>" + json[i].eId + "</td>")
                                .append("<td>" + json[i].eName + "</td>")
                                .append("<td>" + json[i].ePhone + "</td>")
                                .append("<td>" + sex + "</td>")
                                .append("<td>" + json[i].eClazz + "</td>")
                                .append("<td>" + json[i].eDepartment.dName + "</td>")
                                .append("<td>" + none + "</td>")
                                .appendTo("#table");
                    }
                    $("#phone").val("");
                },"json")
            })


            $("#eEmail").change(function(){
                var v = $(this).val();
                if(!v.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
                    $(this).next(".error").text("格式不正确！请重新输入");
                } else {
                    $(this).next(".error").text(" ");
                }
            })


            /**
             * 关闭窗口
             */
            $("#close").click(function(){
                $("#messdiv").attr("style","display: none");
                empId = 0;
            })

        })

        function chickEmial(note) {
            var v = $(this).val();
            if(!v.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
                $(this).next(".error").text("格式不正确！请重新输入");
            } else {
                $(this).next(".error").text(" ");
            }
        }

        function SI(eId) {
            empId = eId;
            $("#messdiv").removeAttr("style");
            return false;
        }

        function SI1() {
            if (empId != 0) {
                var eEmail = $("#eEmail").val();
                if(!eEmail.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
                    $("#eEmail").next(".error").text("格式不正确！请重新输入");
                } else {
                    $("#eEmail").next(".error").text(" ");
                    var g = {"eName":"eName","eSex":'1',"eClazz":"eClazz","ePhone":"13542202544","eDepartment.dId":"1","eXiBie.xbId":"1","eId": empId,"eEmail": eEmail}
                    $.post("/LibrarySingIn-8/Interview/SI1", g, function (data) {
                        if (data != -1) {
                            $("#" + empId).replaceWith("<span class='red'>" + data + "</span>");
                            $("#messdiv").attr("style","display: none");
                            $("#eEmail").val("");
                        } else {
                            alert(data);
                        }
                    }, "text")
                }
            }
        }

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
        <form action="#" class="phone">
            手机号码:<input type="text" name="phone" id="phone" /><button type="submit" id="submit">确定</button>
        </form>
        <table id="table">
            <tr>
                <td>ID</td>
                <td>名字</td>
                <td>手机号码</td>
                <td>性别</td>
                <td>班级</td>
                <td>部门</td>
                <td>签到</td>
            </tr>
        </table>
    </div>


    <div id="messdiv" style="display: none">
        <div id="mess">
            <div class="hurdle oh">
                <p id="close" class="fr">X</p>
            </div>
            <div class="content">
                <h3>电子邮箱:</h3>
                <input type="text" name="eEmail" id="eEmail">
                <p class="error"> </p>
                <span id="sum" class="fr">58/<span id="surplus">58</span></span>
            </div>
            <div class="messbot">
                <button id="confirm" type="button" onclick="SI1()">确定</button>
                <button id="cancel" type="button">取消</button>
            </div>
        </div>
    </div>
</body>
</html>
