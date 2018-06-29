<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: LinTi
  Date: 2016/9/19
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <title>查看员工</title>

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

    <link rel="stylesheet" href="../scripts/layui/css/layui.css" media="all">
</head>
<body>
<div class="am-g am-g-fixed">
    <%--   <div class="nav">
           <div class="div1 oh">
               <a href="#" class="logo fl"></a>
               <h1 class="fr">广东东软学院图书馆</h1>
           </div>
       </div>--%>
    <div class="am-g">
        <form id="screen" class="am-form am-form-horizontal" action="#">
            <div class="am-u-md-1 am-text-right">部门</div>
            <div class="am-u-md-2">
                <select name="dep" multiple data-am-selected="{btnWidth:'100%',btnSize: 'sm'}">
                    <c:forEach items="${dep}" var="d">
                        <option value="${d.dId}">${d.dName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="am-u-md-1 am-text-right">系别</div>
            <div class="am-u-md-2">
                <select name="xiBie" multiple data-am-selected="{btnWidth:'100%',btnSize: 'sm'}">
                    <c:forEach items="${xibie}" var="x">
                        <option value="${x.xbId}">${x.xbName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="am-u-md-1 am-text-right">性别</div>
            <div class="am-u-md-2">
                <select name="sex" multiple data-am-selected="{btnWidth:'100%',btnSize: 'sm'}">
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
            <div class="am-u-md-1 am-text-right">状态</div>
            <div class="am-u-md-2">
                <select name="status" multiple data-am-selected="{btnWidth:'100%',btnSize: 'sm'}">
                    <c:forEach items="${status}" var="s">
                        <option value="${s.sId}">${s.sStatus}</option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </div>
    <div class="am-g">
        <table id="empTable">
           <%-- <tr>
                <th>全选<input type="checkbox" id="checkAll"/></th>
                <th>姓名</th>
                <th>性别</th>
                <th>部门</th>
                <th>班级</th>
                <th>联系方式</th>
            </tr>--%>
        </table>
    </div>
    <%--<iframe class="div_view" id="iframe" width="100%" height="100%" scrolling="no" src="/LibrarySingIn-8/Admin/fileEmployee?pagination=0&"--%>
    <%--onload="this.height=this.contentWindow.document.documentElement.scrollHeight"></iframe>--%>
</div>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="../scripts/amazeui/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="../scripts/amazeui/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="../scripts/amazeui/js/amazeui.min.js"></script>

<script src="../scripts/layui/layui.js"></script>
<script type="text/javascript">
    $(function(){
        $("#screen").submit(function(){
            return false;
        });
        $("#dow").click(function(){
            window.open("/LibrarySingIn-8/Admin/outEmployee?" +  $("form").serialize());
        })
        layui.use('table', function(){
            var table = layui.table;

            //执行渲染
            var empTable = table.render({
                elem: '#empTable', //指定原始表格元素选择器（推荐id选择器）
                height: 315, //容器高度
                url: '/LibrarySingIn-8/Admin/fileEmployee',
                //where: {token: 'sasasas', id: 123}, //如果无需传递额外参数，可不加该参数
//                method: 'post', //如果无需自定义HTTP类型，可不加该参数
                request: {
                    pageName: 'pagination', //页码的参数名称，默认：page
                    limitName: 'count' //每页数据量的参数名，默认：limit
                }, //如果无需自定义请求参数，可不加该参数
                response: {
//                    statusName: 'code', //数据状态的字段名称，默认：code
//                    statusCode: 200, //成功的状态码，默认：0
//                    msgName: 'hint', //状态信息的字段名称，默认：msg
//                    countName: 'rowCount', //数据总数的字段名称，默认：count
//                    dataName: 'result' //数据列表的字段名称，默认：data
                }, //如果无需自定义数据响应名称，可不加该参数
                page: true, //开启分页
                limit: 10,  //每页默认10条
                cols:  [[ //标题栏
                    {checkbox: true},
                    {field: 'eId', title: 'ID', width: 120},
                    {field: 'eName', title: '姓名', width: 120},
                    {field: 'eSex',title: '性别', width: 120},
                    {field: '',title: '部门', width: 120},
                    {field: 'eClazz',title: '班级', width: 120},
                    {field: 'ePhone',title: '联系方式', width: 120}
                ]]
            });

            $("#screen").submit(function(){
                console.info($("#screen").serialize());
                empTable.reload({
                    where: $("#screen").serialize()
                });
            })
        });

    });
</script>
</body>
</html>
