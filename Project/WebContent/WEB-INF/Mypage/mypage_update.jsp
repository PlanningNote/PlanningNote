<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../top.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	
		// 회원가입 화면의 입력값들을 검사한다.
		function checkValue()
		{
			var f = document.userInfo;
		
			if(!f.oldPwd.value){
				alert("현재 비밀번호를 입력하세요.");
				return false;
			}
			if(!f.newPwd.value){
				alert("새 비밀번호를 입력하세요.");
				return false;
			}
			if(!f.passwordcheck.value){
				alert("새 비밀번호 확인 입력하세요.");
				return false;
			}
				
			// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
			if(f.newPwd.value != f.passwordcheck.value ){
				alert("새 비밀번호를 동일하게 입력하세요.");
				return false;
			}	
		}
</script>
<tr>
	<td>
	<div align="center">
		<form method="post" action="mypage_updatePro.do" 
				name="userInfo" onsubmit="return checkValue()">
			<table align="center" height="40%">
			<img src="img/mp2.PNG">
			<h1>비밀번호 변경</h1>
			<tr>
					<td id="title">현재 비밀번호</td>
					<td><input type="password" name="oldPwd" maxlength="50">
					</td>
				</tr>
				<tr>
					<td id="title">새 비밀번호</td>
					<td><input type="password" name="newPwd" maxlength="50">
					</td>
				</tr>
				<tr>
					<td id="title">새 비밀번호 확인</td>
					<td><input type="password" name="passwordcheck" maxlength="50">
					</td>
				</tr>	
				
				<tr>
					<td></td>
					<td>
					<input type="submit" value="확인" >  
					<input type="reset" value="다시작성">	
					</td>
				</tr>
			</table>
		</form>
	</div>
</td>
</tr>
<%@ include file="../../bottom.jsp" %>