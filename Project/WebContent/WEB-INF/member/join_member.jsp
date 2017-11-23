<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../top.jsp" %>
<script type="text/javascript">
	
		// ȸ������ ȭ���� �Է°����� �˻��Ѵ�.
		function checkValue()
		{
			var f = document.userInfo;
		
			if(!f.email.value){
				alert("�̸��ϸ� �Է��ϼ���.");
				return false;
			}
			
			if(f.email.value.length<4){
				alert("�̸����� 4���̻� �Է��ϼž� �մϴ�.");
				return false
			}
			
			if(f.emailDuplication.value != "emailCheck"){
				alert("�̸��� �ߺ�üũ�� ���ּ���.");
				return false;
			}
			
			if(!f.password.value){
				alert("��й�ȣ�� �Է��ϼ���.");
				return false;
			}
			
			// ��й�ȣ�� ��й�ȣ Ȯ�ο� �Էµ� ���� �������� Ȯ��
			if(f.password.value != form.passwordcheck.value ){
				alert("��й�ȣ�� �����ϰ� �Է��ϼ���.");
				return false;
			}	
			
			if(!f.nickName.value){
				alert("�г����� �Է��ϼ���.");
				return false;
			}
			
			if(!f.age.value){
				alert("���̸� �Է��ϼ���.");
				return false;
			}
			
			if(isNaN(f.age.value)){
				alert("���̴� ���ڸ� �Է°����մϴ�.");
				return false;
			}
		}
		
		// ��� ��ư Ŭ���� ùȭ������ �̵�
		function goFirstForm() {
			location.href="index.do";
		}	
		
		// ���̵� �ߺ�üũ ȭ��open
		function openEmailChk(){
			var a = document.userInfo.email.value;
			window.name = "parentForm";
			window.open("email_check.do?email="+a,"chkForm", "width=500, height=300, resizable = no, scrollbars = no");	
		}
		
		// ���̵� �Է�â�� �� �Է½� hidden�� idUncheck�� �����Ѵ�.
		// �̷��� �ϴ� ������ �ߺ�üũ �� �ٽ� ���̵� â�� ���ο� ���̵� �Է����� ��
		// �ٽ� �ߺ�üũ�� �ϵ��� �Ѵ�.
		function inputEmailChk(){
			document.userInfo.emailDuplication.value ="emailUncheck";
		}
		
</script>

<tr>
	<div align="center">		
		
		<!-- �Է��� ���� �����ϱ� ���� form �±׸� ����Ѵ� -->
		<!-- ��(�Ķ����) ������ POST ���, ������ �������� JoinPro.jsp -->
		<form method="post" action="join_member.do" 
				name="userInfo" onsubmit="return checkValue()">
			<table align="center"  height="60%">
				<tr>
					<td id="title">�̸���</td>
					<td>
						<input type="text" name="email" maxlength="50" onkeydown="inputEmailChk()">
						<input type="button" value="�ߺ�Ȯ��" onclick="openEmailChk()">	
						<input type="hidden" name="emailDuplication" value="emailUncheck" >
					</td>
				</tr>
						
				<tr>
					<td id="title">��й�ȣ</td>
					<td>
						<input type="password" name="pwd" maxlength="50">
					</td>
				</tr>
				
				<tr>
					<td id="title">��й�ȣ Ȯ��</td>
					<td>
						<input type="password" name="passwordcheck" maxlength="50" >
					</td>
				</tr>
					
				<tr>
					<td id="title">�г���</td>
					<td>
						<input type="text" name="nickname" maxlength="50">
					</td>
				</tr>
					
				<tr>
					<td id="title">����</td>
					<td>
						<input type="radio" name="gender" value="M" checked>��
						<input type="radio" name="gender" value="W" >��
					</td>
				</tr>
					
				<tr>
					<td id="title">����</td>
					<td>
						<input type="text" name="age" maxlength="2" size="3"/>
					</td>
				</tr>
			</table>
			<br>
			<input type="submit" value="����"/>  
			<input type="reset" value="�ٽ��ۼ�">
		</form>
</div>
</tr>

<%@ include file="../../bottom.jsp" %>