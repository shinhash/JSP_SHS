<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	
	<!-- include summernote css/js -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<link href="/JSP_SHS/pages/css/summernote/summernote-lite.css" rel="stylesheet" />
	<script src="/JSP_SHS/pages/js/summernote/summernote-lite.js"></script>
	<script src="/JSP_SHS/pages/js/summernote/lang/summernote-ko-KR.js"></script>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#summernote").summernote({
				height: 300,                 // 에디터 높이
				minHeight: null,             // 최소 높이
				maxHeight: null,             // 최대 높이
				focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
				lang: "ko-KR",					// 한글 설정
				placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정

			});
		});
	</script>
	

</head>
<body>

	<div>
		<form method="post">
		  <textarea id="summernote" name="editordata"></textarea>
		</form>
	
	</div>
	

</body>
</html>