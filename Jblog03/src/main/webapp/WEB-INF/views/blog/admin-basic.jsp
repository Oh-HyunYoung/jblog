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
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.request.contextPath }/blog/admin-category/${id}">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath }/blog/admin-write/${id}">글작성</a></li>
				</ul>
				<form action="${pageContext.request.contextPath }/blog/upload/${id}" method="post"  enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${blogvo.title }"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img style="width:100px" id="profile" src="${pageContext.request.contextPath }${blogvo.profile }"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>