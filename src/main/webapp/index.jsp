<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<body>
<h2>Session测试</h2>
<p>${user.username}</p>
Nginx测试使用Spring-session框架，NOSQL数据库Redis集中管理Session <br/>
tomcat1 SessionID : <%= session.getId() %>  
</body>
</html>