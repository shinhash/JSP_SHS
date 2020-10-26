<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="${pageContext.request.contextPath}/css/favicon.ico">
	
	<title>BoardList</title>
	
	 <%@ include file="/pages/linkInfo/link_tag_Info.jsp" %>
	
	
	
	<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script><!-- Custom styles for this template --> --%>
	<link href="${cp }/pages/css/bootstrap.css" rel="stylesheet"><!-- Bootstrap core CSS -->
	<link href="${cp }/pages/css/dashboard.css" rel="stylesheet">
	<link href="${cp }/pages/css/blog.css?v=2" rel="stylesheet">
	
	
	<style type="text/css">
		#boardTable{
			width: 100%;
		}
		#boardTable th, td{
			border: 1px solid black;
			
		}
		#boardTable th{
			text-align: center;
		}
		.board_seq{
			text-align: center;
		}
	</style>
	
</head>

<body>

	
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<%@ include file="/pages/layout/header.jsp" %>
	</nav>
	
	
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/pages/layout/left.jsp" %>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				

				<br>
				<br>
				<br>
				
				
				<!-- 접속한 게시판 -->
				<table id="boardTable">
					<tr>
						<th>게시글 번호</th>
						<th>제목</th>
						<th>작성자 아이디</th>
						<th>작성일시</th>
					</tr>
					<c:forEach items="${boardPageList }" var="board">
					<tr>
						<td class="board_seq">${board.BOARD_SEQ }</td>
						<td>${board.BOARD_TITLE }</td>
						<td>${board.USERID }</td>
						<td><fmt:formatDate value="${board.BOARD_DATE }" pattern="YYYY-MM-dd"/></td>
					</tr>
					</c:forEach>
				</table>
				<br>
				<a class="btn btn-default pull-right" href="${cp }/boardRegist?boardKind=${boardKindTitle}" >새글 등록</a>
				
				
				
				<div class="text-center">
					<ul class="pagination">
					
						<c:forEach var="i" begin="1" end="${pageCnt}" step="1">
							<c:choose>
								<c:when test="${pageNum == i}">
									<li class="active"><span>${i}</span></li>
								</c:when>
								<c:otherwise>
									<li><a href="${cp}/boardListPage?pageNum=${i}">${i}</a></li>						
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
				<!------------------------>
				
				
				



			</div>
		</div>
	</div>
	
	
	
</body>
</html>
