<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>�÷��󼼺���</title>
</head>
<body>
	<div align="center">
		<form name="f" method="post" action="content.do">
			<table border="1" WIDTH="1000" HEIGHT="750">
				<tr>
					<td COLSPAN="2">�̹���</td>
				</tr>
				<tr>
					<td COLSPAN="2">����</td>
					<input type="hidden" name="subject" border="1" yle="width: 100%; height: 25;" VALUE="${subject}">
				</tr>
				<tr>
					<td COLSPAN="2">����</td>
				</tr>
				<tr>
					<td WIDTH="50%">����</td>
					<td>����: ��</td>
				</tr>
				<tr>
					<td COLSPAN="2">����</td>
				</tr>
			</table>
			<a href="list.do">�ڷΰ���</a>
		</form>
	</div>
</body>
</html>