<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ include file="../../top.jsp" %>
<tr>
	<td>
		<div align="center">
			<b>�۳��� ����</b>
			<table border="1" width="600">
				<tr>
					<th bgcolor="yellow" width="15%">�۹�ȣ</th>
					<td align="center" width="35%">${getNoticeBoard.no}</td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="15%">������</th>
					<td align="center" width="85%" colspan="3">
						${getNoticeBoard.subject}</td>
				</tr>
				<tr>					
					<th bgcolor="yellow" width="20%">�� ��</th>
					<td>
						<textarea name="content" width="55%"  align="center" rows="12" cols="55" class="box" /readonly>${getNoticeBoard.content}</textarea></td>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="20%">�� �� ��</th>
					<td >
						<textarea name="content" width="55%"  align="center" rows="12" cols="55" class="box" /readonly>${getNoticeBoard.content}</textarea></td>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="15%">��ȸ��</th>
					<td align="center" width="35%">${getNoticeBoard.count}</td>
				</tr>
				
				<tr>	
					<th bgcolor="yellow" width="15%">�ۼ���</th>
					<td align="center" width="35%">${getNoticeBoard.day}</td>
				</tr>
	
				<tr bgcolor="yellow">
					<td colspan="4" align="right"><input type="button" value="�ۼ���"
						onclick="window.location='notice_update.do?no=${getNoticeBoard.no}'">					
						<input type="button" value="�ۻ���"
						onclick="window.location='notice_delete.do?no=${getNoticeBoard.no}'">
						<input type="button" value="�۸��"
						onclick="window.location='notice_list.do'">
					</td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>
