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
	
	
	
	
	
	<!-- include libraries(jQuery, bootstrap) -->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	<!-- include summernote css/js -->
	<script src="${cp }/pages/js/jquery-3.5.1.min.js"></script>
	
	<link href="${cp }/pages/css/summernote/summernote-lite.css" rel="stylesheet">
	<script src="${cp }/pages/js/summernote/summernote-lite.js"></script>
	<script src="${cp }/pages/js/summernote/lang/summernote-ko-KR.js"></script>
	
	
	
	<style type="text/css">
		#repleInsertBtn{
			height: 65px;
			vertical-align: top;
		}
	</style>
	
	
	
	
	<script type="text/javascript">

		$(document).ready(function(){


			$('#summernote').summernote({
				height: null,                 // 에디터 높이
				minHeight: null,             // 최소 높이
				maxHeight: null,             // 최대 높이
				focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
				lang: "ko-KR",					// 한글 설정
				placeholder: '최대 2048자까지 쓸 수 있습니다', //placeholder 설정
				toolbar: [],
				disableResizeEditor: false
			});
			
			$("#summernote").summernote('disable');
			$('.note-statusbar').hide();




			
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
				var textarea = $("#repleContent").val();
				if(textarea != ""){
					$("#repleWriteForm").submit();
				}else{
					alert("댓글을 작성해주세요.");
				}
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
							<textarea id="summernote" name="editordata">${boardVO.BOARD_CONTENT }</textarea> 
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
							
								&nbsp;&nbsp;
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
							
								&nbsp;&nbsp;
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