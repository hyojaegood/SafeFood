<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<c:if test="${not empty sessionScope.id}">
	<ul>
      <li><a href="./main.do?action=list">글목록</a></li>
      <li><a href="./main.do?action=logout">로그아웃</a></li>
    </ul>
  </c:if>
    <c:if test="${ empty sessionScope.id}">
    <ul>
      <li><a href="./main.do?action=list">글목록</a></li>
      <li>로그인하세요.</li>
    </ul>
	
  </c:if>