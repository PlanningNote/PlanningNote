<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
<%@ include file="../../top.jsp" %>
<tr>
	<td>
		<div align="center">
			<h3>��  ��  ��~~~~</h3>
			<form name="f" action="notice_update.do" 
											method="post" onsubmit="return check()">
			<input type="hidden" name="no" value="${getNoticeBoard.no}"/> 
				<table border="1" width="600">
					<tr bgcolor="red">
						<th colspan="2">�� ��  �� �� �� �Դϴ�</th>
						</tr> 
					<tr>
						<th bgcolor="pink" width="80%">����</th>
						<td><input type="text" name="subject" class="box" size="50" 
																value="${getNoticeBoard.subject}"></td>
					</tr>			
					<tr>
						<th bgcolor="pink" width="25%">�� ��</th>
						<td><textarea name="content"  rows="12" cols="65" size="50"  class="box">${getNoticeBoard.content}</textarea></td>
					</tr>
					<tr>
						<th bgcolor="pink" width="20%">�̹���</th>
						<td><input type="text" name="img" class="box" size="50" 
																value="${getNoticeBoard.img}"></td>
					</tr>
					<tr>
						<th bgcolor="pink" width="20%">��й�ȣ</th>
						<td><input type="password" name="passwd" class="box"></td>
					</tr>
					<tr bgcolor="pink">
						<td align="center" colspan="2">
							<input type="submit" value="�ۼ���">
							<input type="reset" value="���">
							<input type="button" value="��Ϻ���" 
													onclick="window.location='notice_list.do'">
						</td>
					</tr>
				</table>
			</form>	
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>











