<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/7
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>SpringMVC博客管理</title>
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="container">
    <h1>SpringMVC博客管理</h1>
    <hr/>
    <h3>所有博客<a href="/admin/blogs/add"  type="button" class="btn btn-primary btn-sm">添加</a></h3>
    <c:if test="${empty blogList}">
        <div class="alert alert-warning" role="alert" >
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>Blog表为空，请<a href="/admin/blogs/add" type="button" class="btn btn-primary btn-sm">添加</a>
        </div>
        </c:if>
    <c:if test="${!empty blogList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>ID</th>
                <th>标题</th>
                <th>作者</th>
                <th>发布日期</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${blogList}" var="blog">
                <tr>
                    <td>${blog.id}</td>
                    <td>${blog.title}</td>
                    <td>${blog.userByUserId.nickname}, ${blog.userByUserId.firstName} ${blog.userByUserId.lastName}</td>
                    <td><fmt:formatDate value="${blog.pubDate }" pattern="yyyy--MM--dd"/></td>
                    <td>
                        <a href="/admin/blogs/show/${blog.id}" type="button" class="btn btn-sm btn-success">详情</a>
                        <a href="/admin/blogs/update/${blog.id}" type="button" class="btn btn-sm btn-warning">修改</a>
                        <a href="/admin/blogs/delete/${blog.id}" type="button" class="btn btn-sm btn-danger">删除</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </c:if>

</div>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
