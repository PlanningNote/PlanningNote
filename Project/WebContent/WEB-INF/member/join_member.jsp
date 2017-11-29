<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../top.jsp" %>
<script type="text/javascript">
	
		// 회원가입 화면의 입력값들을 검사한다.
		function checkValue()
		{
			var f = document.userInfo;
		
			if(!f.email.value){
				alert("이메일를 입력하세요.");
				return false;
			}
			
			if(f.emailDuplication.value != "emailCheck"){
				alert("이메일 중복체크를 해주세요.");
				return false;
			}
			
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
		}
		
		// 취소 버튼 클릭시 첫화면으로 이동
		function goFirstForm() {
			location.href="index.do";
		}	
		
		// 아이디 중복체크 화면open
		function openEmailChk(){
			var a = document.userInfo.email.value;
			window.name = "parentForm";
			window.open("email_check.do?email="+a,"chkForm", "width=500, height=300, resizable = no, scrollbars = no");	
		}
		
		// 아이디 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
		// 이렇게 하는 이유는 중복체크 후 다시 아이디 창이 새로운 아이디를 입력했을 때
		// 다시 중복체크를 하도록 한다.
		function inputEmailChk(){
			document.userInfo.emailDuplication.value ="emailUncheck";
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
	</script>
	
</head>
<body>
<div align="center">
		
		<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
		<!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
		<form method="post" action="join_member.do" 
				name="userInfo" onsubmit="return checkValue()">
			<table align="center"  height="60%">
				<tr>
					<td id="title">이메일</td>
					<td>
						<input type="text" name="email" maxlength="50" onkeydown="inputEmailChk()">
						<input type="button" value="중복확인" onclick="openEmailChk()">	
						<input type="hidden" name="emailDuplication" value="emailUncheck" >
					</td>
				</tr>
						
				<tr>
					<td id="title">비밀번호</td>
					<td>
						<input type="password" name="pwd" maxlength="50">
					</td>
				</tr>
				
				<tr>
					<td id="title">비밀번호 확인</td>
					<td>
						<input type="password" name="passwordcheck" maxlength="50" >
					</td>
				</tr>
					
				<tr>
					<td id="title">닉네임</td>
					<td>
						<input type="text" name="nickname" maxlength="50" onkeydown="inputNicknameChk()">
						<input type="button" value="중복확인" onclick="openNicknameChk()">	
						<input type="hidden" name="nicknameDuplication" value="nicknameUncheck" >
					</td>
				</tr>
					
				<tr>
					<td id="title">성별</td>
					<td>
						<input type="radio" name="gender" value="남" checked>남
						<input type="radio" name="gender" value="여" >여
					</td>
				</tr>
					
				<tr>
					<td id="title">나이</td>
					<td>
						<input type="text" name="age" maxlength="2" size="3"/>
					</td>
				</tr>
			</table>
			<br>
			<input type="submit" value="가입"/>  
			<input type="reset" value="다시작성">
		</form>
</div>
</tr>

<%@ include file="../../bottom.jsp" %>