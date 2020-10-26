<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<script src="${pageContext.request.contextPath}/pages/css/bootstrap.js"></script><!-- Custom styles for this template -->

<link href="${cp }/pages/css/bootstrap.css" rel="stylesheet"><!-- Bootstrap core CSS -->
<link href="${cp }/pages/css/dashboard.css" rel="stylesheet">
<link href="${cp }/pages/css/blog.css" rel="stylesheet">



	<div class="col-sm-3 col-md-2 sidebar">
		<ul class="nav nav-sidebar">
			<li class="active"><a href="${cp }/boardCreate">게시판 생성</a></li>
			
			<c:forEach items="${bkList }" var="bk">
				<li class="active"><a href="${cp }/boardListPage?boardKindTitle=${bk.BOARD_KIND_TITLE }">${bk.BOARD_KIND_TITLE }</a></li>
			</c:forEach>
			
		</ul>
	</div>