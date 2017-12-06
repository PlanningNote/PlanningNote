<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>이메일 중복 체크</title>
	
	<script type="text/javascript">
	
		var httpRequest = null;
		
		// httpRequest 객체 생성
		function getXMLHttpRequest(){
			var httpRequest = null;
		
			if(window.ActiveXObject){
				try{
					httpRequest = new ActiveXObject("Msxml2.XMLHTTP");	
				} catch(e) {
					try{
						httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e2) { httpRequest = null; }
				}
			}
			else if(window.XMLHttpRequest){
				httpRequest = new window.XMLHttpRequest();
			}
			return httpRequest;	
		}
		
		
		
		// 아이디 중복체크
		function emailCheck(){
			var email = document.checkForm.email.value;
			if (!email) {
				alert("이메일를 입력하지 않았습니다.");
				return false;
			} 
			else
			{
				var param="email="+email
				httpRequest = getXMLHttpRequest();
				httpRequest.onreadystatechange = callback;
				httpRequest.open("POST", "MemberEmailCheckAction.do", true);	
				httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
				httpRequest.send(param);
			}
		}
		
		function callback(){
			if(httpRequest.readyState == 4){
				// 결과값을 가져온다.
				var resultText = httpRequest.responseText;
				if(resultText == 0){
					alert("사용할수없는 아이디입니다.");
					document.getElementById("cancelBtn").style.visibility='visible';
					document.getElementById("useBtn").style.visibility='hidden';
					document.getElementById("msg").innerHTML ="";
				} 
				else if(resultText == 1){ 
					document.getElementById("cancelBtn").style.visibility='hidden';
					document.getElementById("useBtn").style.visibility='visible';
					document.getElementById("msg").innerHTML = "사용 가능한 이메일입니다.";
				}
			}
		}
		
		// 사용하기 클릭 시 부모창으로 값 전달 
		function sendCheckValue(){
			// 중복체크 결과인 idCheck 값을 전달한다.
			opener.document.userInfo.emailDuplication.value ="emailCheck";
			// 회원가입 화면의 ID입력란에 값을 전달
			opener.document.userInfo.email.value = document.getElementById("email").value;
			
			if (opener != null) {
            	opener.chkForm = null;
            	self.close();
			}	
		}	
	</script>
	
</head>
<!--  <body onload="pValue()">-->
<div id="wrap">
	<br>
	<b><font size="4" color="gray"><img src="img/emailch.PNG" border="0"></font></b>
	<hr size="1" width="460">
	<br>
	<div id="chk" align="center">
		<form id="checkForm" name="checkForm">
			<input type="text" name="emailinput" id="email" value="${getEmail}">
			<input type="button" value="중복확인" onclick="emailCheck()">
		</form>
		<div id="msg"></div>
		<br>
		<input id="cancelBtn" type="button" value="취소" onclick="window.close()">
		<input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">
	</div>
</div>	
</body>
</html>