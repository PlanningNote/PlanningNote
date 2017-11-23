<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>삭제확인</title>
</head>
<body>
	<div align="center">
		<form name="f" action="admin_deleteMember.do" method="post">
			비밀번호 입력 : <input type="password" name="pwd"><br>
			<input type="submit" value="확인">
			<input type="reset" value="취소">
			<input type="hidden" name="no" value="${no}">
		</form>
	</div>
</body>
</html>