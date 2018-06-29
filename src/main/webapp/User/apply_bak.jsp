<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/7/30
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0"/>
    <title>图书馆义务馆员报名</title>
    <link rel="stylesheet" href="../css/apply.css"/>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
        var name = false,
                phone = false,
                xibie = false,
                clazz = false,
                depar = false;
        $(function () {
            $("form").submit(function() {
                return false;
            })
            $("form").submit(function () {
                if ($('input[name="eSex"]:checked ').val() == 1 || $('input[name="eSex"]:checked ').val() == 2) {
                    if (name && phone && xibie && clazz && depar) {
                        $.post(this.action, $("form").serialize(), function (data) {
                            if ("true" == data) {
                                $("#box1").remove();
                                $("#box2").removeClass("hidden");
                            }else if("false" == data) {
                                alert("请重新填写好各项!");
                            }else if("repetition" == data) {
                                alert("你已经报名,请不要重复报名!");
                            }
                        });
                    } else {
                        if (!name) {
                            nameError($("#eName"));
                        }
                        if (!phone) {
                            phoneError($("#ePhone"))
                        }
                        if (!xibie) {
                            xibieError($("#eXiBie"))
                        }
                        if (!clazz) {
                            clazzError($("#eClazz"))
                        }
                        if (!depar) {
                            deparError($("#eDepar"))
                        }
                        alert("请填写好各项");
                    }
                } else {
                    alert("请选择性别");
                }
                return false;
            })

            $("#eName").change(function () {
                nameError();
            })

            $("#ePhone").change(function () {
                phoneError();
            })

            $("#eClazz").change(function () {
                clazzError();
            })

            $("#eXiBie").change(function () {
                xibieError();
            });

            $("#eDepar").change(function () {
                deparError();
            });

        })

        function nameError(){
            $("#name").remove();
            var regName = /^([\u4E00-\u9FA5])*$/; //是否全为中文
            if ($("#eName").val().length > 1 && $("#eName").val().length < 5 && regName.test($("#eName").val())) {
                name = true;
            } else {
                name = false;
                $("#eNameError").append($("<div id='name' class='error' >名字长度不能为2-4个,且只能为中文</div>"));
            }
        }

        function phoneError() {
            $("#phone").remove();
            if ($("#ePhone").val().length == 11) {
                phone = true;
            } else {
                phone = false;
                $("#ePhoneError").append($("<div id='phone' class='error' >前填写11位长度的手机号码</div>"));
            }
        }
        function clazzError() {
            $("#clazz").remove();
            if ($("#eClazz").val().length > 0) {
                clazz = true;
            } else {
                clazz = false;
                $("#eClazzError").append($("<div id='clazz' class='error' >请填写班级</div>"));
            }
        }
        function xibieError() {
            $("#xibie").remove();
            if ($("#eXiBie").val() > 0) {
                xibie = true;
            } else {
                xibie = false;
                $("#eXiBieError").append($("<div id='xibie' class='error'>请选择所属系别</div>"))
            }
        }
        function deparError() {
            $("#depar").remove();
            if ($("#eDepar").val() > 0) {
                depar = true;
            } else {
                depar = false;
                $("#eDeparError").append($("<div id='depar' class='error'>请选择报名的部门</div>"))
            }
        }
    </script>
</head>
<body>
<div id="box1" class="div1">
    <h1>图书馆义务馆员报名</h1>
    <form:form action="/LibrarySingIn-8/User/apply1" method="post" modelAttribute="employee">
        <ul class="classify tar">
            <li>姓名</li>
            <li>性别</li>
            <li>联系方式</li>
            <li>系别</li>
            <li>班级</li>
            <li>报名部门</li>
        </ul>
        <ul class="write tal">
            <li><form:input class="name" path="eName" onkeyup="this.value=this.value.replace(/[, ]/g,'')"/></li>
            <li>
                <div class="radio">
                    <div class="box"><form:radiobutton path="eSex" value="1"/><label class="firstRadio" for="eSex1">男</label></div>
                    <div class="box"><form:radiobutton path="eSex" value="2"/><label for="eSex2">女</label></div>
                </div>
            </li>
            <li><form:input class="phone" path="ePhone" onkeyup='this.value=this.value.replace(/\D/gi,"")'/></li>
            <li>
                <form:select path="eXiBie.xbId" id="eXiBie">
                    <form:option value="0" label="请选择"/>
                    <form:options items="${xibie}" itemLabel="xbName" itemValue="xbId"/>
                </form:select>
            </li>
            <li><form:input class="clazz" path="eClazz" onkeyup="this.value=this.value.replace(/[, ]/g,'')"/></li>
            <li>
                <form:select path="eDepartment.dId" id="eDepar">
                    <form:option value="0" label="请选择"/>
                    <form:options items="${department}" itemLabel="dName" itemValue="dId"/>
                </form:select>
            </li>
            <li><button class="chick" type="submit">报 &nbsp; &nbsp;名</button></li>
        </ul>
        <ul class="errorLi">
            <li id="eNameError"><form:errors path="eName"  cssClass="error"/></li>
            <li id="eSexError"><form:errors path="eSex" cssClass="error"/></li>
            <li id="ePhoneError"><form:errors path="ePhone" cssClass="error"/></li>
            <li id="eXiBieError"><form:errors path="eXiBie" cssClass="error"/></li>
            <li id="eClazzError"><form:errors path="eClazz" cssClass="error"/></li>
            <li id="eDeparError"><form:errors path="eDepartment" cssClass="error"/></li>
        </ul>
    </form:form>
</div>

<div id="box2" class="div1 hidden">
    <h1>图书馆义务馆员报名</h1>
    <h2>
        <p>报名成功</p>
        <span>请关注我们的官方公众号,留意面试通知</span><br>
        <span>咨询QQ群：1群:154386648 2群:299766538</span>
    </h2>
    <img src="../images/weixin.jpg">
</div>
</body>
</html>
