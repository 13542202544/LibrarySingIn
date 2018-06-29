<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/2/21
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>替班</title>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#beNumber").change(function(){
                $(":submit").attr("disabled");
                $(".beNuLi").remove();
                var beNu = this.value.trim();
                this.value = beNu;
                if (!number(beNu)) {
                    $("#beNumber").after("<b class='beNuLi'>请正确输入11位学号</b>");
                }
                check();
            })

            $("#jobId").change(function () {
                $(":submit").attr("disabled");
                $(".jobLi").remove();
                var job = this.value.trim();
                this.value = job;
                if (!jobId(job)) {
                    $("#jobId").after("<b class='jobLi'>请正确输入8位工号</b>");
                }
                check();
            })

            $("#number").change(function(){
                $(":submit").attr("disabled");
                $(".numLi").remove();
                var num = this.value.trim();
                this.value = num;
                if (!number(num)) {
                    $("#number").after("<b class='numLi'>请正确输入11位学号</b>");
                }
                check();
            })

            $("form").submit(function () {
                var v = {"beJobId":$("#beJobId").val(), "con":$("#con").val(), "beNumber":$("#beNumber").val(), "jobId":$("#jobId").val(), "number":$("#number").val()}
                var url = this.action;
                $.post(this.action, v, function(data){
                    if (data == "true") {
                        alert("成功");
                        window.close();
                    }else if(data == "beFalse") {
                        alert("失败,确认对方学号是否正确!")
                    }else if (data == "false") {
                        alert("失败,请重新确认工号学号是否正确!")
                    }
                });
                return false;
            });

        });

        function check() {
            $(":submit").attr("disabled",true);
            if (number($("#beNumber").val()) && jobId($("#jobId").val()) && number($("#number").val())) {
                $(":submit").removeAttr("disabled");
            }
        }

        function number(num) {
            if (num.trim().length == 11) {
                return true;
            } else {
                return false;
            }
        }

        function jobId(id) {
            if (id.trim().length == 8) {
                return true;
            } else {
                return false;
            }
        }
    </script>
</head>
<body>
    <div>
        <form action="/LibrarySingIn-8/SingIn/replaceCheck">
            <input type="hidden" name="beJobId" id="beJobId" value="${id}">
            <input type="hidden" name="con" id="con" value="${con}">
            对方学号:<input type="text" name="beNumber" id="beNumber"><br>
            你的工号:<input type="text" name="jobId" id="jobId"><br>
            你的学号:<input type="text" name="number" id="number"><br>
            <input disabled type="submit" value="提交"><br>
        </form>
    </div>
</body>
</html>
