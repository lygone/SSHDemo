<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/7
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="container">
<h1>SpringMVC用户详情</h1>
<hr/>
<table class="table table-bordered table-striped">
<tr>
    <th>ID</th>
    <th>${user.id}</th>
</tr>
    <tr>
    <th>Nickname</th>
    <th>${user.nickname}</th>
    </tr>
    <tr>
        <th>Firstname</th>
    <th>${user.firstName}</th>
    </tr>
    <tr>
        <th>Last Name</th>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <th>Password</th>
        <td>${user.password}</td>
    </tr>
</table>
</div>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
