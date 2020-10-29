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
	
	<title>Board Info</title>
	
	 <%@ include file="/pages/linkInfo/link_tag_Info.jsp" %>
	
	
	
	<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script><!-- Custom styles for this template --> --%>
	<link href="${cp }/pages/css/bootstrap.css" rel="stylesheet"><!-- Bootstrap core CSS -->
	<link href="${cp }/pages/css/dashboard.css" rel="stylesheet">
	<link href="${cp }/pages/css/blog.css?v=2" rel="stylesheet">
	
	
	
	<style type="text/css">
		#repleInsertBtn{
			height: 50px;
		
		}
	</style>
	
	
	
	
	<script type="text/javascript">

		$(document).ready(function(){

			$("#updateBoardInfo").on("click", function(){
				var boardSeq = $("#boardSeq").val();
				document.location = "boardUpdate?boardSeq=" + boardSeq;
				
			})

			$("#deleteBoardInfo").on("click", function(){
				var boardSeq = $("#boardSeq").val();
				var boardKindId = $("#boardKindId").val();
				document.location = "boardDelete?boardSeq=" + boardSeq + "&boardKindId=" + boardKindId;
			})


			$("#repleInsertBtn").on("click", function(){
				$("#repleWriteForm").submit();
			})

			$(".delRepleBtn").on("click", function(){
				var repleId = $(this).parents(".showRepleDiv").find(".repleId").val()
				document.location = "repleDelete?repleId=" + repleId;
			})

			$("#commentBoardInfo").on("click", function(){
				var boardPseq = $("#boardSeq").val();
				var boardKindId = $("#boardKindId").val();
				document.location = "boardRegist?boardPseq=" + boardPseq + "&boardKindId=" + boardKindId;
			})

			$(".delFileBtn").on("click", function(){
				var fileId = $(this).parents(".dbFileDiv").find(".delFileId").val();
// 				alert(fileId);
				document.location = "fileDownload?fileId=" + fileId;
			})

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
						<input type="hidden" id="boardKindId" name="boardKindId" value="${boardVO.BOARD_KIND_ID }"/>
						
						<br>
						제목 : <input type="text" id="baord_title" name="baord_title" value="${boardVO.BOARD_TITLE }" readonly><br>
						글내용 <br>
						<div>
							${boardVO.BOARD_CONTENT }
						</div><br>
						작성자 : ${boardVO.USERID }<br>
						작성일자 : <fmt:formatDate value="${boardVO.BOARD_DATE }" pattern="YYYY-MM-dd"/>
						<br><br><br>
						
						
						첨부파일
						<div id="fileListDiv">
							<c:forEach items="${fileList }" var="file">
							
								<div class="dbFileDiv">
									<input type="hidden" class="delFileId" name="fileId" value="${file.FILE_SEQ }" />
									${file.REAL_FILE_NAME } <button type="button" class="btn btn-primary delFileBtn">Down</button>
								</div>
								<br>
								
							</c:forEach>	
						</div>
						
						
						<div id="btnDiv">
						
						<c:choose>
							<c:when test="${boardVO.USERID == MEMBER.userid}">
							
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
								<button type="button" class="btn btn-primary" id="updateBoardInfo">수정</button>
								<button type="button" class="btn btn-primary" id="deleteBoardInfo">삭제</button>						
								<button type="button" class="btn btn-primary" id="commentBoardInfo">답글</button>
								
							</c:when>
							
							<c:otherwise>
							
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
								<button type="button" class="btn btn-primary" id="commentBoardInfo">답글</button>
							
							</c:otherwise>
							
						</c:choose>
							
						</div>
					</div>
				
				</form>
				
				
				<br>
				
				댓글
				
				<div id="repleDiv">
					
					<c:forEach items="${repleList }" var="reple">
					
					<div class="showRepleDiv">
						<input type="hidden" class="repleId" name="repleId" value="${reple.REPLE_SEQ }" />
						<input type="text" value="${reple.REPLE_CONTENT }" readonly="readonly"/>
						[${reple.USERID } / <fmt:formatDate value="${reple.REPLE_DT }" pattern="YYYY-MM-dd"/>]
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${reple.USERID == MEMBER.userid}">
							<c:if test="${reple.REPLE_STATUS == 'Y' }">
								<button type="button" class="btn btn-primary delRepleBtn">삭제</button>											
							</c:if>
						</c:if>
					</div>
					
					</c:forEach>
				
				</div>
				<br>
				
				
				<form id="repleWriteForm" action="${cp }/repleRegist" method="post">
					<input type="hidden" name="boardSeq" value="${boardVO.BOARD_SEQ }" />
					<input type="hidden" name="boardKindId" value="${boardVO.BOARD_KIND_ID }"/>
					<input type="hidden" name="userId" value="${MEMBER.userid }" />
				
					<div id="writeRepleDiv">
						<textarea id="repleContent" name="repleContent" rows="3" cols="80" style="resize: none;"></textarea>
						<button type="button" id="repleInsertBtn" class="btn btn-primary">댓글저장</button>
					</div>
				
				</form>
				
				
				



			</div>
		</div>
	</div>
	
</body>
</html>