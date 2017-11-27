<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../top.jsp" %>
<html>
<head>
	<title>로그인</title>
	<script type="text/javascript">
		function check(){
			var f = document.userInfo;
			if(!f.email.value){
				alert("이메일을 입력하세요.");
				return false;
			}
			if(!f.pwd.value){
				alert("비밀번호를 입력하세요.");
				return false;
			}
		}
		
		
	</script>
</head>
<body>
	<div align="center">
		<form name="userInfo" onsubmit="return check()" method="post" action="login_ok.do">
			<table align="center"  height="70%">
			
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
<%@ include file="../../bottom.jsp" %>