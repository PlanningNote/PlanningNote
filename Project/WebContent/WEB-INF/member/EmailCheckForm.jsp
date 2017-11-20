<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>�̸��� �ߺ� üũ</title>
	
	<script type="text/javascript">
	
		var httpRequest = null;
		
		// httpRequest ��ü ����
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
		
		
		
		// ���̵� �ߺ�üũ
		function emailCheck(){
			var email = document.checkForm.email.value;
			if (!email) {
				alert("�̸��ϸ� �Է����� �ʾҽ��ϴ�.");
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
				// ������� �����´�.
				var resultText = httpRequest.responseText;
				if(resultText == 0){
					alert("����Ҽ����� ���̵��Դϴ�.");
					document.getElementById("cancelBtn").style.visibility='visible';
					document.getElementById("useBtn").style.visibility='hidden';
					document.getElementById("msg").innerHTML ="";
				} 
				else if(resultText == 1){ 
					document.getElementById("cancelBtn").style.visibility='hidden';
					document.getElementById("useBtn").style.visibility='visible';
					document.getElementById("msg").innerHTML = "��� ������ �̸����Դϴ�.";
				}
			}
		}
		
		// ����ϱ� Ŭ�� �� �θ�â���� �� ���� 
		function sendCheckValue(){
			// �ߺ�üũ ����� idCheck ���� �����Ѵ�.
			opener.document.userInfo.emailDuplication.value ="emailCheck";
			// ȸ������ ȭ���� ID�Է¶��� ���� ����
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
	<b><font size="4" color="gray">�̸��� �ߺ�üũ</font></b>
	<hr size="1" width="460">
	<br>
	<div id="chk">
		<form id="checkForm" name="checkForm">
			<input type="text" name="emailinput" id="email" value="${getEmail}">
			<input type="button" value="�ߺ�Ȯ��" onclick="emailCheck()">
		</form>
		<div id="msg"></div>
		<br>
		<input id="cancelBtn" type="button" value="���" onclick="window.close()"><br>
		<input id="useBtn" type="button" value="����ϱ�" onclick="sendCheckValue()">
	</div>
</div>	
</body>
</html>