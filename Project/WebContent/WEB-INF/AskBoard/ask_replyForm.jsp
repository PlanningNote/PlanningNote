<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="../../top.jsp" %>
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
		if (f.pwd.value==""){
			alert("��й�ȣ�� �Է��� �ּ���!!")
			f.pwd.focus()
			return false
		}
		return true
	}
	</script>
<tr>
	<td>
		<div align="center">
			<h3>�� �� �� ~~~~~</h3>
			<form name="f" action="ask_reply.do" 
									method="post" onsubmit="return check()">
			<input type="hidden" name="no" value="${getAskBoard.no}"/> 
				<table border="1" width="600">
					<tr bgcolor="red">
					<tr bgcolor="grey">
						<td align="center" colspan="2">�� �� �� ��</td>
					</tr>
					<tr>
						<th bgcolor="grey" width="20%">�� ��</th>
						<td><input type="text" name="writer" class="box"></td>
				   </tr>
					<tr>
						<th bgcolor="grey" width="80%">����</th>
						<td><input type="text" name="subject" class="box" size="50" 
																value="[���]${getAskBoard.subject}"></td>
					</tr>
					<tr>
						<th bgcolor="grey" width="25%">�� ��</th>
						<td><textarea name="content"  rows="12" cols="65" size="50"  class="box">${getAskBoard.content}</textarea></td>
					</tr>
					<tr>
						<th bgcolor="grey" width="20%">�̹���</th>
						<td><input type="text" name="img" class="box" size="50" 
																value="${getAskBoard.img}"></td>
					</tr>
					<tr>
						<th bgcolor="grey" width="20%">��й�ȣ</th>
						<td><input type="password" name="pwd" class="box"></td>
					</tr>
					<tr bgcolor="grey">
						<td align="center" colspan="2">
							<input type="submit" value="��۴ޱ�">
							<input type="reset" value="���">
							<input type="button" value="��Ϻ���" 
													onclick="window.location='ask_list.do'">
						</td>
					</tr>
				</table>
			</form>	
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>












