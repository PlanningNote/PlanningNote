<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>�α���</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="login_ok.do">
			<table border="1" align="center">
			<b>�� �� ��</b>
				<tr>
					<td>�̸���: <input type="text" name="email"><br>
					��й�ȣ: <input type="password" name="pwd"><br>
					<input type="submit" value="�α���" ><br>
					<a href="find_pwd.do">��й�ȣ ã��</a> |
					<a href="join_member.do">ȸ������</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>