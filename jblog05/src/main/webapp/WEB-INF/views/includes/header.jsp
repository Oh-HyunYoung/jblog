<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
			<h1>${blogvo.title }</h1>
			<ul>
				<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a><li>
				</c:when>
				<c:otherwise>
					<li><a href="${pageContext.request.contextPath }/${id}/blog/main">메인화면</a></li>
					<li><a href="${pageContext.request.contextPath }/index">로그아웃</a></li>
					<c:if test="${authUser.id == id } ">
					<li><a href="${pageContext.request.contextPath }/${id}/blog/admin-basic">블로그 관리</a></li>
					</c:if>
				</c:otherwise>
			</c:choose>	
			</ul>
		</div>