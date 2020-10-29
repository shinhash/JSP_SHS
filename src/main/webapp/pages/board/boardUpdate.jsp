<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">
	
	
	
	
	<title>Board Update</title>
	
	
	 <%@ include file="/pages/linkInfo/link_tag_Info.jsp" %>
	
	
	
	<link href="${cp }/pages/css/bootstrap.css" rel="stylesheet"><!-- Bootstrap core CSS -->
	<link href="${cp }/pages/css/dashboard.css" rel="stylesheet">
	<link href="${cp }/pages/css/blog.css" rel="stylesheet">
	
	
	
	
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
 		#btnDiv{ 
			float: right;
 		}
 		.spantag{
 			font-size: 1.5em;
 			font-weight: bold;
 		}
 		#boardTitle{
 			height: 30px;
 			width: 90%;
 		}
 		.attchFile{
 			width: 500px;
 			float: left;
 		}
 		
	</style>

	
	<script type="text/javascript">

		$(function(){

			tagId = 0;
			
			var summer = $("#summernote");
			
			$('#summernote').summernote({
				height: 300,                 // 에디터 높이
				minHeight: null,             // 최소 높이
				maxHeight: null,             // 최대 높이
				focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
				lang: "ko-KR",					// 한글 설정
				placeholder: '최대 2048자까지 쓸 수 있습니다', //placeholder 설정
			});

			
			$("#boardRegResetBtn").on("click", function(){
				reset();
			});

			var reset = function() { 
				summer.summernote('reset'); 
			};



			$("#boardUpdateBtn").on("click", function(){
				$("#updatesummernoteForm").submit();
			});



			$(document).on("click", ".delBtn", function(){
				tagId = $(this).attr("id");
				$("#div" + tagId).remove()
			});



			$("#attachAdd").on("click", function(){
				tagId += 1;
				tagInfo = "<div id='div"+tagId+"' class='attchFile'><input type='file' name='fileInput' style='float: left;'><button type='button' id='"+tagId+"' class='delBtn btn btn-primary'> X </button></div>";
				$("#addedFileDiv").append(tagInfo); // 태그 추가

			});


			$(document).on("click", ".delFileBtn", function(){
				
				var delFileId = $(this).parents("#dbFileDiv").find(".delFileId").val();
				alert(delFileId);
				deltagInfo = "<div><input type='hidden' name='delFileIdInfo' value='"+delFileId+"' /></div>";
				
				$("#attachFileDiv #deletedFileIdIdiv").append(deltagInfo)
				$(this).parents("#dbFileDiv").remove();
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
				<br>
				
				
				
				<form id="updatesummernoteForm" action="${cp }/boardUpdate" method="post" enctype="multipart/form-data">
				
					<input type="hidden" name="BOARD_SEQ" value="${boardVO.BOARD_SEQ }" />
					<input type="hidden" name="BOARD_KIND_ID" value="${boardVO.BOARD_KIND_ID }" />
					<span class="spantag">제목 : </span><input type="text" id="boardTitle" name="boardTitleName" value="${boardVO.BOARD_TITLE }"/>
					<br>
					<br>
					<textarea id="summernote" name="editordata">${boardVO.BOARD_CONTENT }</textarea>
					<br>
					
					<div id="attachFileDiv">
					
						<div id="addedFileDiv"></div>
						
						
						<div id="dbFileListDiv">
							<c:forEach items="${fileList }" var="file">
							
								<div id="dbFileDiv">
									<input type="hidden" class="delFileId" name="fileId" value="${file.FILE_SEQ }" />
									<input type="text" name="addedFile" value="${file.REAL_FILE_NAME }" />
									<button type="button" class="delFileBtn"> X </button>
								</div>
								
							</c:forEach>
						</div>
						
						
						
						<div id="deletedFileIdIdiv"></div>
						<br>
						<div>
							<button type="button" class="btn btn-primary" id="attachAdd">파일추가</button>					
						</div>
						
					</div>
					<br>
					<div id="btnDiv">
						<button type="button" class="btn btn-primary" id="boardUpdateBtn">수정완료</button>
						<button type="button" class="btn btn-primary" id="boardRegResetBtn">초기화</button>
					</div>
					
					
				</form>
				
				
			
			</div>
		</div>
	</div>
	
</body>
</html>