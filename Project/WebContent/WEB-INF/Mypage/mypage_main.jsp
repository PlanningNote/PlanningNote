<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../top.jsp"%>
<html>
<head>
<title>마이페이지</title>
</head>
<body>
	<div align="center">
		<form name="f">
			<table align="center" height="60%">
			<img src="img/mp2.PNG">
				<tr>
					<td id="title">이름</td>
					<td><input type="text" maxlength="50"></td>
				</tr>

				<tr>
					<td id="title">비밀번호</td>
					<td><input type="password" name="pwd" maxlength="50">
					</td>
				</tr>

				<tr>
					<td id="title">비밀번호 확인</td>
					<td><input type="password" name="passwordcheck" maxlength="50">
					</td>
				</tr>

				<tr>
					<td id="title">닉네임</td>
					<td><input type="text" name="nickname" maxlength="50"
						onkeydown="inputNicknameChk()"> <input type="button"
						value="중복확인" onclick="openNicknameChk()"> <input
						type="hidden" name="nicknameDuplication" value="nicknameUncheck">
					</td>
				</tr>

				<tr>
					<td id="title">성별</td>
					<td><input type="radio" name="gender" value="남" checked>남
						<input type="radio" name="gender" value="여">여</td>
				</tr>

				<tr>
					<td id="title">나이</td>
					<td><input type="text" name="age" maxlength="2" size="3" /></td>

				</tr>

				<tr>
					<td id="title">내가작성한글보기</td>
					<td><input type="text" name="age" maxlength="2" size="3" /></td>

				</tr>

				</tr>

				<tr>
					<td id="title">좋아요누른페이지</td>
					<td><input type="text" name="age" maxlength="2" size="3" /></td>

				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<%@ include file="../../bottom.jsp"%>