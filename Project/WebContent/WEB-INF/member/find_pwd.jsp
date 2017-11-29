<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>비밀번호 찾기</title>
</head>
<body>
	<div align="center">
		<form name="f" action="findemail.do" method="post">
			<table >
			<b>비밀번호 찾기</b>
			<hr color="gray" width="300">
			<tr></tr>
				<tr>
					<td>이메일: <input type="text" maxlength="30" name="email">
					<input type="submit" value="확인"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>