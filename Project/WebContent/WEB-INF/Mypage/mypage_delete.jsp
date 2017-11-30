<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../top.jsp"%>
<html>
<head>
	<title>회원탈퇴</title>
<script type="text/javascript">
	function begin(){
		document.f.pwd.focus();
	}

	function checkPwd(){
		if(!f.pwd.value){
			alert("비밀번호를 입력해주세요.");
			document.f.pwd.focus();
			return false;
		}
	}
</script>
</head>
<body onload="begin()">
	<div align="center">
		<form name="f" onSubmit="return checkPwd()" method="post" action="delete.do">
			<table>
				<h1>회원탈퇴</h1>
				<hr color="gray" width="300">
				<tr>
					<td>비밀번호: <input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td><input type="submit" value="확인">
					<input type="button" value="취소" onclick="javascript:window.location='mypage_main.jsp'"></td>					
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<%@ include file="../../bottom.jsp"%>