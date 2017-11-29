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
		
		// 비밀번호 찾기 화면open
		function openFindPwd(){
			window.name = "parentForm";
			window.open("find_pwd.do","chkForm", "width=400, height=200, left=600, top=350, resizable = no, scrollbars = no");	
		}
		
	</script>
</head>
<body>
	<div align="center">
	
		<form name="userInfo" onsubmit="return check()" method="post" action="login_ok.do">
			<table align="center"  height="30%">
				<tr>
				<img src="img/joinImg.PNG" border="0">
					<td>이메일: <input type="text" name="email"><br>
					비밀번호: <input type="password" name="pwd"><br>
					<input type="submit" value="로그인" ><br>
					<img src="img/findpw.png" onclick="openFindPwd()" border="0"> 
					<a href="join_member.do"><img src="img/joinmember.png" border="0"></a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<%@ include file="../../bottom.jsp" %>