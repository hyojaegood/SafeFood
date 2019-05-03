<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% request.setCharacterEncoding("utf-8");  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>login</title>
</head>
<body>
<!-- <form action="./main.do?action=login" method="post">
<table>
<tr><td>아이디</td><td><input type="text" name='id' /></td></tr>
<tr><td>패스워드</td><td><input type="password" name='pwd' /></td></tr>
<tr><td colspan="2"><input type="submit" value='로그인' /></td></tr>
</table>
</form> -->
<%
response.sendRedirect("./main.do?action=index");
%>

</body>
</html>