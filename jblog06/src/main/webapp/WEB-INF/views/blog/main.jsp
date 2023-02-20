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
			<h1>${blogvo.title }</h1>
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a><li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
					<c:if test="${authUser.id == id}">
              			 <li><a href="${pageContext.request.contextPath}/${id}/blog/admin-basic">블로그 관리</a></li>
               		</c:if>
				</c:otherwise>
			</c:choose>	
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title}</h4>
					<p>
						${post.contents}
					<p>
				</div>
				<ul class="blog-list">
				<c:set var="count" value="${fn:length(postList) }" />
				<c:forEach items="${postList }" var ="pvo" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${id}/blog/main/${pvo.category_no}/${pvo.no}">${pvo.title }</a> <span>${pvo.reg_date }</span></li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogvo.profile }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<c:set var="count" value="${fn:length(list) }" />
			<c:forEach items="${list }" var ="vo" varStatus="status">
			<ul><li><a href="${pageContext.request.contextPath}/${id}/blog/main/${vo.no}">${vo.name }</a></li></ul>
			</c:forEach>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>