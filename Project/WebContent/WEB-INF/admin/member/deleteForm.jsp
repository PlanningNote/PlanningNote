<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>����Ȯ��</title>
</head>
<body>
	<div align="center">
		<form name="f" action="admin_deleteMember.do" method="post">
			��й�ȣ �Է� : <input type="password" name="pwd"><br>
			<input type="submit" value="Ȯ��">
			<input type="reset" value="���">
			<input type="hidden" name="no" value="${no}">
		</form>
	</div>
</body>
</html>