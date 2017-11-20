<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>로그인</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="login_ok.do">
			<table border="1" align="center">
			<b>로 그 인</b>
				<tr>
					<td>이메일: <input type="text" name="email"><br>
					비밀번호: <input type="password" name="pwd"><br>
					<input type="submit" value="로그인" ><br>
					<a href="find_pwd.do">비밀번호 찾기</a> |
					<a href="join_member.do">회원가입</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>