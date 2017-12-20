<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>선택</title>
</head>
<script>
	function reset(){
		self.close();
	}
	function board(){
		window.opener.location  = "searchPlanA.do?mode="+"작성자"+"&searching="+"${nickname}";
		window.alert("${nickname}"+'님의 게시물보기로 이동합니다.');
		self.close();	
	}
	function report(){
		window.opener.location = "memReportForm.do?nickname="+"${nickname}";
		window.alert('회원신고 페이지로 이동합니다.');
		self.close();
	}
</script>
<body>
	<div align="center">
		=== ${nickname}님 ===<br><br>
		<input type="button" value="회원게시물보기" onclick="board()"><br><br>
		<input type="button" value="회원신고" onclick="report()"><br><br>
		<input type="button" value="취소"  onclick="reset()"><br><br>
	</div>
</body>
</html>