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
    <title>图书馆义务馆员报名</title>
    <style type="text/css">
        .div1 {
            width: 800px;
            margin:0 auto;
            text-align:center;
        }
        .tar{
            text-align: right;
        }
        .tal{
            text-align: left;
        }
        tr {
            margin: 10px 0;
        }
        .table {
            margin:0 auto;
            text-align:center;
        }
        .error {
            color: #ff0000;
            font-size: 15px;
        }
        .name{
            border: 1px solid red;
            background-color: #71fff0;
            border-radius: 5px;
        }
    </style>
    <script type="text/javascript" src="../scripts/jquery-1.12.0.js"></script>
    <script type="text/javascript">
    $(function(){
        var name = false, phone = false, xibie = false, clazz = false, depar = false;
        $("form").submit(function () {
            if($('input[name="eSex"]:checked ').val() == 1 || $('input[name="eSex"]:checked ').val() == 2) {
                if (name && phone && xibie && clazz && depar) {
                    return true;
                } else {
                    alert("请填写好各项");
                }
            } else {
                alert("请选择性别");
            }
            return false;
        })

        $("#eName").change(function(){
            $("#name").remove();
            var regName = /^([\u4E00-\u9FA5])*$/; //是否全为中文
            if (this.value.length > 1 && this.value.length < 5 && regName.test(this.value)) {
                name = true;
            } else {
                name = false;
                $("#eName").after($("<li id='name' class='error' >名字长度不能为2-4个,且只能为中文</li>"));
            }
        })

        $("#ePhone").change(function(){
            $("#phone").remove();
            if (this.value.length == 11) {
                phone = true;
            } else {
                phone = false;
                $("#ePhone").after($("<li id='phone' class='error' >前填写11位长度的手机号码</li>"));
            }
        })

        $("#eClazz").change(function(){
            $("#clazz").remove();
            if (this.value.length != 0) {
                clazz = true;
            } else {
                clazz = false;
                $("#eClazz").after($("<li id='clazz' class='error' >请填写班级</li>"));
            }
        })

        $("#eXiBie").change(function(){
            $("#xibie").remove();
            if (this.value > 0) {
                xibie = true;
            } else {
                xibie = false;
                $("#eXiBie").after($("<li id='xibie' class='error'>请选择所属系别</li>"))
            }
        })

        $("#eDepar").change(function(){
            $("#depar").remove();
            if (this.value > 0) {
                depar = true;
            } else {
                depar = false;
                $("#eDepar").after($("<li id='depar' class='error'>请选择报名的部门</li>"))
            }
        })

    })

</script>
</head>
<body>
<div class="div1">
    <form:form action="/LibrarySingIn-8/User/apply" method="post" modelAttribute="employee">
        <table class="table" border="0" cellspacing="0">
            <tr>
                <td class="tar">姓名</td>
                <td class="tal"><form:input class="name" path="eName" onkeyup="this.value=this.value.replace(/[, ]/g,'')"/><form:errors path="eName" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="tar">性别</td>
                <td class="tal">
                    <form:radiobutton path="eSex" value="1"/>男
                    <form:radiobutton path="eSex" value="2"/>女
                    <form:errors path="eSex" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td class="tar">联系方式</td>
                <td class="tal"><form:input path="ePhone" onkeyup='this.value=this.value.replace(/\D/gi,"")'/><form:errors path="ePhone" cssClass="error"/></td>
            </tr>
            <tr>
                <td class="tar">系别</td>
                <td class="tal">
                    <form:select path="eXiBie.xbId" id="eXiBie">
                        <form:option value="0" label="请选择"/>
                        <form:options items="${xibie}" itemLabel="xbName" itemValue="xbId"/>
                    </form:select>
                    <form:errors path="eXiBie" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td class="tar">班级</td>
                <td class="tal"><form:input path="eClazz" onkeyup="this.value=this.value.replace(/[, ]/g,'')"/><form:errors path="eClazz" cssClass="error" /></td>
            </tr>
            <tr>
                <td class="tar">报名部门</td>
                <td class="tal">
                    <form:select path="eDepartment.dId" id="eDepar">
                        <form:option value="0" label="请选择"/>
                        <form:options items="${department}" itemLabel="dName" itemValue="dId"/>
                    </form:select>
                    <form:errors path="eDepartment" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td class="" colspan="2"><button type="submit">报名</button></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
