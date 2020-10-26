<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	
	<title>Board Craete</title>
	
	
	 <%@ include file="/pages/linkInfo/link_tag_Info.jsp" %>
	
	
	
	<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script><!-- Custom styles for this template --> --%>
	<link href="${cp }/pages/css/bootstrap.css" rel="stylesheet"><!-- Bootstrap core CSS -->
	<link href="${cp }/pages/css/dashboard.css" rel="stylesheet">
	<link href="${cp }/pages/css/blog.css?v=2" rel="stylesheet">

	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$("#addBoard").on("click", function(){

				var boardKindTitle = $("#addboardName").val()
				if(boardKindTitle == "" || boardKindTitle == " "){
					alert("게시판의 이름을 작성해주세요.")
				}else{
					$("#addForm").submit();
				}
				
				
			})

			$(".upBKSt").on("click", function(){

				var bkId = $(this).val()
				var use = $(this).parents(".updateDiv").find(".useSelect").val()
				
				$("#upBoardKindId").val(bkId)
				$("#boardUse").val(use)
				
				$("#UpdateBKForm").submit();
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
			
				<br>
				<br>
				<br>

				<form id="addForm" action="${cp }/boardCreate" method="post">
					게시판 이름 : <input type="text" id="addboardName" name="addboardName" /> 
					<select name="board_use">
						<option value="Y">사용</option>
						<option value="N">미사용</option>
					</select>
					<button type="button" id="addBoard">생성</button>
				</form>
				<br>
				
				
				
				<!-- 회원이 생성한 게시판 정보 -->
				<form action="${cp }/boardKindUpdate" method="post" id="UpdateBKForm">
					<c:forEach items="${memBkList }" var="memBk">
						<div class="updateDiv">
							게시판 이름 : <input type="text" name="updateBoardInfo" value="${memBk.BOARD_KIND_TITLE }" readonly="readonly"/> 
							<select class="useSelect" name="board_use">
								<option value="Y">사용</option>
								<option value="N">미사용</option>
							</select>
							<button type="button" class="upBKSt" name="upBKID" value="${memBk.BOARD_KIND_ID }">수정</button>
							<br>
						</div>
						
					</c:forEach>
					
					<input id="upBoardKindId" type="hidden" name="upBoardKindId" />
					<input id="boardUse" type="hidden" name="boardUse" />
				</form>
				<!------------------------>
				
				
				
				
				
				
			</div>
		</div>
	</div>
</body>
</html>