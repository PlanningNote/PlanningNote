<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../top.jsp" %>
<html>
<head>
	<title>�α���</title>
	<script type="text/javascript">
		function check(){
			var f = document.userInfo;
			if(!f.email.value){
				alert("�̸����� �Է��ϼ���.");
				return false;
			}
			if(!f.pwd.value){
				alert("��й�ȣ�� �Է��ϼ���.");
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
					<td>�̸���: <input type="text" name="email"><br>
					��й�ȣ: <input type="password" name="pwd"><br>
					<input type="submit" value="�α���" ><br>
					<a href="find_pwd.do">��й�ȣ ã��</a> |
					<a href="join_member.do">ȸ������</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<%@ include file="../../bottom.jsp" %>