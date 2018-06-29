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
    <title>面试者编号</title>
    <link rel="stylesheet" href="../css/base.css"/>
    <style>
        form {
            margin-top: 100px;
        }
        form p {
            font-size: 40px;
            font-family: "Microsoft YaHei UI";
            font-weight: 900;
            color: #0d09ff;
            margin-bottom: 10px;
        }
        form input {
            font-size: 30px;
            font-family: "Microsoft YaHei UI";
            font-weight: 900;
            margin-bottom: 10px;
            width: 300px;
            color: #FFFFFF;
            background-color: #0D79F2;
            text-align: center;
            padding: 8px 10px;
            border: 1px solid #0d09ff;
            border-radius: 5px;
        }
        form button {
            width: 250px;
            height: 50px;
            font-size: 30px;
            font-family: "Microsoft YaHei UI";
            font-weight: 900;
            color: #0d09ff;
            border: 1px solid #0d09ff;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="nav">
        <div class="div1 oh">
            <a href="#" class="logo fl"></a>
            <h1 class="fr">广东东软学院图书馆</h1>
        </div>
    </div>
    <div class="div1">
        <form action="/LibrarySingIn-8/Interview/getIntEmp" method="post">
            <p>面 &nbsp 试 &nbsp 者 &nbsp 编 &nbsp 号</p>
            <input type="text" name="number" class="text" /><br>
            <button type="submit" class="submit">确 &nbsp 定</button><br>
        </form>
    </div>
</body>
</html>
