<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ include file="sidetop.jsp"%>  
    
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>�� �� �� ��</title>
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
	

		<form name="f" method="post"  
			action="ask_write.do" onsubmit="return checkMember()">
		
		<table border="1" width="600">
			<tr bgcolor="orange">
				<th colspan="2">�� �� �� ��</th>
			</tr> 
		
		
				
			<tr>
				<th bgcolor="pink" width="20%">�ۼ���</th>
				<td width="80%"><input type ="text" name="writer" rows="10" >
				</td>
			</tr> 
			
			<tr>
				<th bgcolor="pink" width="20%">�� ��</th>
				<td width="80%"><input type="text" name="subject" size="50"></td>
			</tr>
			 
			
			
			<tr>
				<th bgcolor="pink" width="20%">�� ��</th>
				<td width="80%">
					<textarea name="content" rows="10" cols="50"></textarea>
				</td>
			</tr>
			
			
			
		 <tr>
				<th>�̹���</th>
				<td><input type="file" name="img"></td>
			</tr>
		
			<tr>
				<th bgcolor="pink" width="20%">��й�ȣ</th>
				<td width="80%"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td bgcolor="pink" colspan="2" align="center">
					<input type="submit" value="�۾���">
					<input type="submit" value="��۴ޱ�">
					<input type="reset" value="�ٽ��ۼ�">
					<input type="button" value="��Ϻ���" 
												onclick="window.location='ask_list.do'">
				</td>
				
			</tr>
		</table>
		</form>
	</div>
</body>
</html>







