<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>ȸ������</title>
	<script type="text/javascript">
		function check(){
			if(f.email.value==""){
				alert("�̸����� �Է��� �ּ���");
				f.email.focus()
				return false
			}
			if(f.verify_no.value==""){
				alert("������ȣ�� �Է��� �ּ���");
				f.verify_no.focus()
				return false
			}
			if(f.pwd.value==""){
				alert("��й�ȣ�� �Է��� �ּ���");
				f.pwd.focus()
				return false
			}
			if(f.nickname.value==""){
				alert("�г����� �Է��� �ּ���");
				f.nickname.focus()
				return false
			}
			if(f.gender.value==""){
				alert("������ �Է��� �ּ���");
				f.gender.focus()
				return false
			}
			if(f.age.value==""){
				alert("������ �Է��� �ּ���");
				f.age.focus()
				return false
			}
			return true
		}
	</script>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="join_member.me" onsubmit="return check()">
			<table border="1" align="center">
				<b>ȸ �� �� ��</b>
				<tr>
					<td>�̸��� : <input type="text" name="email">
							<input type="button" name="verify" value="�����ϱ�"></td>
				</tr>
				<tr>
					<td>������ȣ : <input type="text" name="verify_no">
							<input type="button" name="verify_ok" value="Ȯ��"></td>
				</tr>
				<tr>
					<td>��й�ȣ : <input type="password" name="pwd"></td>
					<td>��й�ȣȮ�� : <input type="password" name="pwd1"></td>
				</tr>
				<tr>
					<td>�г��� : <input type="text" name="nickname"></td>
				</tr>
				<tr>
					<td>���� : <input type="radio" name="gender" value="m">��
								  <input type="radio" name="gender" value="w">��
					</td>
				</tr>
				<tr>
					<td>���� : <input type="text" name="age" size="3" maxlength="2"></td>
				</tr>
				<tr>
					<td><input type="button" name="join" value="ȸ������"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>