<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>문 의 글  삭 제 폼 ~~~~~~~~~~!!! </title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div align="center">
	<b>글 삭 제</b>
	<form name="f" action="ask_delete.do" method="post">
		<input type="hidden" name="no" value="${param.no}"/>
		<table border="1" width="300">
			<tr bgcolor="yellow">
				<th>비밀번호를 입력해 주세요</th>
			</tr>
			<tr>
				<td align="center">
					비밀번호 : <input type="password" name="pwd" class="box">
				</td>
			</tr>
			<tr bgcolor="yellow">
				<td align="center">
					<input type="submit" value="글삭제">
					<input type="button" value="글목록" 
								onclick="window.location='ask_list.do'">
				</td>								
			</tr>
		</table>
	</form>
</div>
</body>
</html>











