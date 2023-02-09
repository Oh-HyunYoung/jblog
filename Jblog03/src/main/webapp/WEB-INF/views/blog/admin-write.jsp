<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Spring 이야기</h1>
			<ul>
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a><li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/blog/main/${id}">메인화면</a></li>
					<li><a href="${pageContext.request.contextPath }/index">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath }/blog/admin-basic/${id}">블로그 관리</a></li>
				</c:otherwise>
			</c:choose>	
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/blog/admin-basic/${id}">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath }/blog/admin-category/${id}">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
			<c:set var="count" value="${fn:length(list) }" />
			
				<form action="${pageContext.request.contextPath }/blog/admin-write/${id}" method="post" enctype="multipart/form-data">
			      	<table class="admin-cat-write">
			      	
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="category_no">
				      				<c:forEach items="${list }" var ="vo" varStatus="status">
				      				<option value="${vo.no } ">${vo.name}</option>
				      				 </c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="contents"></textarea></td>
			      		</tr>
			      		<tr>
			
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      		
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>