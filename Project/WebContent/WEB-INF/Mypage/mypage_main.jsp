<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../top.jsp"%>
<html>
<head>
<title>마이페이지</title>
<script type="text/javascript">
	
		// 회원가입 화면의 입력값들을 검사한다.
		function checkValue()
		{
			var f = document.userInfo;
		
			if(!f.password.value){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
			if(f.password.value != form.passwordcheck.value ){
				alert("비밀번호를 동일하게 입력하세요.");
				return false;
			}	
			
			if(!f.nickname.value){
				alert("닉네임을 입력하세요.");
				return false;
			}
			
			if(f.nicknameDuplication.value != "nicknameCheck"){
				alert("닉네임 중복체크를 해주세요.");
				return false;
			}
			
			if(!f.age.value){
				alert("나이를 입력하세요.");
				return false;
			}
			
			if(isNaN(f.age.value)){
				alert("나이는 숫자만 입력가능합니다.");
				return false;
			}
			
			// 닉네임 중복체크 화면open
			function openNicknameChk(){
				var a = document.userInfo.nickname.value;
				window.name = "parentForm";
				window.open("nickname_check.do?nickname="+a,"chkForm", "width=500, height=300, resizable = no, scrollbars = no");	
			}
			
			function inputNicknameChk(){
				document.userInfo.nicknameDuplication.value ="nicknameUncheck";
			}
		}
</script>
</head>
<body>
	<div align="center">
		<form name="userInfo">
			<table align="center" height="60%">
			<img src="img/mp2.PNG">
			<tr>
					<td id="title">닉네임</td>
					<td><input type="text" name="nickname" maxlength="50" onkeydown="inputNicknameChk()"> 
							<input type="button" value="중복확인" onclick="openNicknameChk()"> 
						    <input type="hidden" name="nicknameDuplication" value="nicknameUncheck">
					</td>
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
					<td><input type="text" name="age" maxlength="2" size="3" /><br>
					<input type="submit" value="가입"/>  
					<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<%@ include file="../../bottom.jsp"%>