<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>���� ������</title>
	<script type="text/javascript">
	function checkMember(){
		
		if (f.subject.value==""){
			alert("������ �Է��� �ּ���!!")
			f.subject.focus()
			return false
		}
		if (f.content.value==""){
			alert("������ �Է��� �ּ���!!")
			f.content.focus()
			return false
		}
		if (f.passwd.value==""){
			alert("��й�ȣ�� �Է��� �ּ���!!")
			f.passwd.focus()
			return false
		}
		return true
	}
	</script>
</head>
<body>
<div align="center">
	<h3>�ۼ���</h3>
	<form name="f" action="notice_updatePro.do" 
									method="post" onsubmit="return check()">
	<input type="hidden" name="no" value="${getNoticeBoard.num}"/>
	<table border="1" width="600">
<tr bgcolor="pink">
				<th colspan="2">�� �� �� �� �� �Դϴ�</th>
			</tr> 
		<tr>
			<th bgcolor="pink" width="80%">����</th>
			<td><input type="text" name="subject" class="box" size="50" 
													value="${getNoticeBoard.subject}"></td>
		</tr>
		
			<tr>
			<th bgcolor="pink" width="80%">�ۼ���</th>
			<td><input type="text" name="writer" class="box" size="50" 
													value="${getNoticeBoard.writer}"></td>
		</tr> 
				<tr>
			<th bgcolor="pink" width="20%">����</th>
			<td><input type="text" name="content" class="box" 
													value="${getNoticeBoard.content}"></td>
		</tr>

		<tr>
			<th bgcolor="pink" width="20%">�� ��</th>
			<td><textarea name="content" rows="12" cols="55" class="box">${getBoard.content}</textarea></td>
		</tr>
				<tr>
			<th bgcolor="pink" width="20%">�̹���</th>
			<td><input type="file" name="img" class="box" size="50" 
													value="${getNoticeBoard.img}"></td>
		</tr>
		<tr>
			<th bgcolor="pink" width="20%">��й�ȣ</th>
			<td><input type="password" name="passwd" class="box"></td>
		</tr>
		<tr bgcolor="pink">
			<td align="center" colspan="2">
				<input type="submit" value="�ۼ���">
				<input type="reset" value="�ٽ��ۼ�">
				<input type="button" value="��Ϻ���" 
										onclick="window.location='Notice.do'">
			</td>
		</tr>
	</table>
	</form>	
</div>
</body>
</html>













