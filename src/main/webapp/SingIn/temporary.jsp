<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/2/23
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>临时上班签到</title>
    <style type="text/css">
        .nostB .numberB .j {
            color: #FF0000;
            font-weight: bold;
        }
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#jobId").change(function () {
                $(":submit").attr("disabled",true);
                $(".jobB").remove();
                if (!checkLen(this.value,8)) {
                    $("#jobId").after("<b class='jobB'>请正确输入8位工号</b>");
                    return false;
                }
                if (checkLen($("#number").val(),11)  && !checkLen($("#nost").val(),0)) {
                    $(":submit").removeAttr("disabled");
                }
            });

            $("#number").change(function () {
                $(":submit").attr("disabled",true);
                $(".numnerB").remove();
                if (!checkLen(this.value,11)) {
                    $("#jobId").after("<b class='numnerB'>请正确输入8位工号</b>");
                    return false;
                }
                if (checkLen($("#jobId").val(),8) && !checkLen($("#nost").val(),0)) {
                    $(":submit").removeAttr("disabled");
                }
            });

            $("#nost").change(function () {
                $(":submit").attr("disabled",true);
                $(".nostB").remove();
                if (checkLen(this.value,0)) {
                    $("#nost").after("<b class='nostB'>请填写工作内容</b>");
                    return false;
                }
                if (checkLen($("#jobId").val(),8) && checkLen($("#number").val(),11)) {
                    $(":submit").removeAttr("disabled");
                }
            });

            $("form").submit(function () {
                $.post(this.action, {"con":$("#con").val(), "jobId":$("#jobId").val(), "number":$("#number").val()}, function (data) {
                    if ("true" == data) {
                        alert("签到成功!");
                        window.close();
                    }else {
                        alert(data);
                    }
                });
                return false;
            });
        });

        function checkLen (num, len) {
            if (num.trim().length == len) {
                return true;
            }else {
                return false;
            }
        }
    </script>
</head>
<body>
<div class="div1">
        <form action="/LibrarySingIn-8/SingIn/temporary" method="post">
            <input type="hidden" name="con" id="con" value="${con}">
            工号:<input type="text" name="jobId" id="jobId"><br>
            学号:<input type="text" name="number" id="number"><br>
            工作内容:<input type="text" name="nost" id="nost"><br>
            <button disabled type="submit">签到</button>
        </form>
    </div>
</body>
</html>
