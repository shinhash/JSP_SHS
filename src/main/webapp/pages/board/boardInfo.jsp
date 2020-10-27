<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="${cp }/css/favicon.ico">
	
	<title>BoardList</title>
	
	 <%@ include file="/pages/linkInfo/link_tag_Info.jsp" %>
	
	
	
	<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script><!-- Custom styles for this template --> --%>
	<link href="${cp }/pages/css/bootstrap.css" rel="stylesheet"><!-- Bootstrap core CSS -->
	<link href="${cp }/pages/css/dashboard.css" rel="stylesheet">
	<link href="${cp }/pages/css/blog.css?v=2" rel="stylesheet">
	
	
	<script type="text/javascript">

		$(document).ready(function(){

			$("#updateBoardInfo").on("click", function(){

				var boardSeq = $("#boardSeq").val();
				document.location = "boardUpdate?boardSeq=" + boardSeq;
				
			})

// 			$("#deleteBoardInfo").on("click", function(){
				
// 			})
			

		})
	
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
				
				
				<form id="boardForm">
				
					<div id="baordInfoDiv">
						<input type="hidden" id="boardSeq" name="boardSeq" value="${boardVO.BOARD_SEQ }"/>
						
						<br>
						제목 : <input type="text" id="baord_title" name="baord_title" value="${boardVO.BOARD_TITLE }" readonly><br>
						내용 <br>
						<div>
							${boardVO.BOARD_CONTENT }
						</div><br>
						작성자 : <input type="text" id="baord_user" name="baord_user" value="${boardVO.USERID }" readonly><br>
						작성일자 : <fmt:formatDate value="${boardVO.BOARD_DATE }" pattern="YYYY-MM-dd"/>
						<br><br><br>
						<div id="btnDiv">
							<c:if test="${boardVO.USERID == MEMBER.userid}">
								<button type="button" class="btn btn-primary" id="updateBoardInfo">수정</button>
								<button type="button" class="btn btn-primary" id="deleteBoardInfo">삭제</button>						
							</c:if>
						</div>
					</div>
				
				</form>

				
				
				



			</div>
		</div>
	</div>
	
</body>
</html>