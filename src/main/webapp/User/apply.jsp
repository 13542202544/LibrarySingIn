<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2017/9/5
  Time: 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <title>Hello Amaze UI</title>

    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="icon" type="image/png" href="../scripts/amazeui/i/favicon.png">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="../scripts/amazeui/i/app-icon72x72@2x.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="../scripts/amazeui/i/app-icon72x72@2x.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
    <meta name="msapplication-TileColor" content="#0e90d2">

    <link rel="stylesheet" href="../scripts/amazeui/css/amazeui.min.css">
    <link rel="stylesheet" href="../scripts/amazeui/css/app.css">
    <!--[if (gte IE 9)|!(IE)]><!-->
    <script src="../scripts/amazeui/js/jquery.min.js"></script>
    <!--<![endif]-->
    <!--[if lte IE 8 ]>
    <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="../scripts/amazeui/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->
    <style>
        body {
            padding: 10px;
        }
        .am-form-horizontal .am-form-label,.am-form-horizontal .am-radio-inline {
            padding-top: 0;
        }
        [class*=am-u-] {
            min-height: 32px;

        }
        .error {
            color: #ff0000;
            font-size: 15px;
            line-height: 32px;
        }
        h1 {
            width: 300px;
            margin: 0 auto;
            background: url("../images/logo.png") no-repeat;
            -webkit-background-size: 60px 60px;
            background-size: 60px 60px;
            text-align: center;
            height: 60px;
            padding-top: 25px;
            padding-left: 60px;
            font-size: 26px;
        }
    </style>
    <script>
        $(function(){
            $('#applyForm').validator({
                onValid: function(validity) {
                    $(validity.field).closest("div").find(".am-alert").hide();
                },

                onInValid: function(validity) {
                    var $field = $(validity.field);
                    console.info($field);
                    var $alert = $field.closest("div").find(".am-alert");
//                    // 使用自定义的提示信息 或 插件内置的提示信息
                    var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

                    if (!$alert.length) {
                        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().appendTo($field.closest("div"));
                    }

                    $alert.html(msg).show();
                }
            });
        })
    </script>
</head>
<body>
<div id="box1" class="div1">
    <form:form id="applyForm" class="am-form am-form-horizontal" action="/LibrarySingIn-8/User/apply" method="post" modelAttribute="employee"
               data-am-validator="{}">

        <fieldset>
            <legend class="title am-text-center">
                <h1>图书馆义务馆员报名</h1>
            </legend>
            <div class="am-form-group">
                <label for="eName" class="am-u-md-4 am-u-sm-3 am-form-label am-text-right">姓名</label>
                <div class="am-u-md-5 am-u-sm-9">
                    <form:input class="name am-input-sm" path="eName"
                                required="1" data-validation-message="请输入姓名"
                                onkeyup="this.value=this.value.replace(/[, ]/g,'')"/>
                </div>
                <div id="eNameError" class="am-u-md-3 am-u-sm-0">
                    <form:errors path="eName" cssClass="error"/>
                </div>
            </div>


            <div class="am-form-group">
                <h3 class="am-u-md-4 am-u-sm-3 am-form-label am-text-right">性别</h3>
                <div class="am-u-md-5 am-u-sm-9">
                    <label class="am-radio-inline">
                        <form:radiobutton path="eSex" value="1" data-am-ucheck="1"/> 男
                    </label>
                    <label class="am-radio-inline">
                        <form:radiobutton path="eSex" value="2" required="1" data-validation-message="选择性别" data-am-ucheck="2"/> 女
                    </label>
                </div>
                <div id="eSexError" class="am-u-md-3 am-u-sm-0">
                    <form:errors path="eSex" cssClass="error"/>
                </div>
            </div>

            <div class="am-form-group">
                <label for="ePhone" class="am-u-md-4 am-u-sm-3 am-form-label am-text-right">联系方式</label>
                <div class="am-u-md-5 am-u-sm-9">
                    <form:input class="phone am-input-sm" path="ePhone" data-validation-message="请正确输入11位手机号码" required="1" minlength="11" maxlength="11"
                                onkeyup='this.value=this.value.replace(/\D/gi,"")'/>
                </div>
                <div id="ePhoneError" class="am-u-md-3 am-u-sm-0">
                    <form:errors path="ePhone" cssClass="error"/>

                </div>
            </div>

            <div class="am-form-group">
                <label for="eXiBie" class="am-u-md-4 am-u-sm-3 am-form-label am-text-right">系别</label>
                <div class="am-u-md-5 am-u-sm-9">
                    <form:select path="eXiBie.xbId" id="eXiBie" required="1" data-validation-message="请选择系别" data-am-selected="{btnWidth: '100%', btnSize: 'sm'}">
                        <form:option value="" label="请选择"/>
                        <form:options items="${xibie}" itemLabel="xbName" itemValue="xbId"/>
                    </form:select>
                </div>
                <div id="eXiBieError" class="am-u-md-3 am-u-sm-0">
                    <form:errors path="eXiBie" cssClass="error"/>
                </div>
            </div>

            <div class="am-form-group">
                <label for="eClazz" class="am-u-md-4 am-u-sm-3 am-form-label am-text-right">班级</label>
                <div class="am-u-md-5 am-u-sm-9">
                    <form:input class="clazz am-input-sm" path="eClazz" required="1" data-validation-message="请填写所属班级"
                                onkeyup="this.value=this.value.replace(/[, ]/g,'')"/>
                </div>
                <div id="eClazzError" class="am-u-md-3 am-u-sm-0">
                    <form:errors path="eClazz" cssClass="error"/>

                </div>
            </div>

            <div class="am-form-group">
                <label for="eDepar" class="am-u-md-4 am-u-sm-3 am-form-label am-text-right">报名部门</label>
                <div class="am-u-md-5 am-u-sm-9">
                    <form:select path="eDepartment.dId" id="eDepar" required="1" data-validation-message="请选择报名的部门"
                                 data-am-selected="{btnWidth: '100%', btnSize: 'sm'}">
                        <form:option value="" label="请选择"/>
                        <form:options items="${department}" itemLabel="dName" itemValue="dId"/>
                    </form:select>
                </div>
                <div id="eDeparError" class="am-u-md-3 am-u-sm-0">
                    <form:errors path="eDepartment" cssClass="error"/>
                </div>
            </div>

            <div class="am-form-group">
                <div class="am-u-md-4 am-u-sm-3"></div>
                <div class="am-u-md-5 am-u-sm-9">
                    <button type="submit" class="am-btn am-btn-primary am-btn-block">报名</button>
                </div>
                <div class="am-u-md-3 am-u-md-2"></div>
            </div>
        </fieldset>

    </form:form>
</div>

<script src="../scripts/amazeui/js/amazeui.min.js"></script>
</body>
</html>
