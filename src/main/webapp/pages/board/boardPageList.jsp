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
		
	</style>
	
	
	<script type="text/javascript">

		$(document).ready(function(){
			$("#boardTBody tr").on("click", function(){

				var boardST = $(this).find(".boardST").val();
				if(boardST == "Y"){
					var boardid = $(this).data("boardid")
					document.location = "boardInfo?boardId=" + boardid;
				}else if(boardST == "N"){
					alert("삭제된 게시글 입니다.")
				}
			});
		});

	</script>
	
	
	
	
	
	
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
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${boardKindTitle }</h2>
						
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>게시글 번호</th>
									<th>제목</th>
									<th>작성자 아이디</th>
									<th>작성일시</th>
								</tr>
								
								<tbody id="boardTBody">
									<c:forEach items="${boardPageList }" var="board">
									<tr data-boardid="${board.BOARD_SEQ}">
										<td class="board_seq">${board.BOARD_RN }</td>
										<td class="board_title">${board.BOARD_TITLE }</td>
										<td class="board_userid">${board.USERID }</td>
										<td class="board_date"><fmt:formatDate value="${board.BOARD_DATE }" pattern="YYYY-MM-dd"/></td>
										<input type="hidden" class="boardST" value="${board.BOARD_STATUS }" />
									</tr>
									</c:forEach>
								
								</tbody>
				
				
							</table>
						</div>
				
						<br>
						<a class="btn btn-primary pull-right" href="${cp }/boardRegist?boardKindId=${boardKindId }" >새글 등록</a>
		
						
						<!-- 페이지 번호 -->
						<div class="text-center">
							<ul class="pagination">
							
							
							
								<c:choose>
									<c:when test="${pageNum >= 2 }">
										<li><a href="${cp}/boardListPage?boardKindId=${boardKindId }&pageNum=${1}"> 〈〈 </a></li>								
									</c:when>
									<c:otherwise>
										<li class="active"><span> 〈〈 </span></li>
									</c:otherwise>
								</c:choose>
							
							
								<c:choose>
									<c:when test="${pageNum >= 2 }">
										<li><a href="${cp}/boardListPage?boardKindId=${boardKindId }&pageNum=${pageNum-1}"> 〈 </a></li>								
									</c:when>
									<c:otherwise>
										<li class="active"><span> 〈 </span></li>
									</c:otherwise>
								</c:choose>
							
								<c:forEach var="i" begin="1" end="${pageCnt }" step="1">
									<c:choose>
										<c:when test="${pageNum == i}">
											<li class="active"><span>${i}</span></li>
										</c:when>
										<c:otherwise>
											<li><a href="${cp}/boardListPage?boardKindId=${boardKindId }&pageNum=${i}">${i}</a></li>						
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								
								<c:choose>
									<c:when test="${pageNum < pageCnt }">
										<li><a href="${cp}/boardListPage?boardKindId=${boardKindId }&pageNum=${pageNum+1 }"> 〉 </a></li>								
									</c:when>
									<c:otherwise>
										<li class="active"><span> 〉 </span></li>
									</c:otherwise>
								</c:choose>
								
								
								<c:choose>
									<c:when test="${pageNum < pageCnt }">
										<li><a href="${cp}/boardListPage?boardKindId=${boardKindId }&pageNum=${pageCnt }"> 〉〉 </a></li>								
									</c:when>
									<c:otherwise>
										<li class="active"><span> 〉〉 </span></li>
									</c:otherwise>
								</c:choose>
								
								
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			
			
			
		</div>
	</div>
	
</body>
</html>
