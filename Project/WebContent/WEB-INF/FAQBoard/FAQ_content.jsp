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
					<td align="center" width="35%">${getFAQBoard.no}</td>
				</tr>
				<tr>
					<th bgcolor="yellow" width="15%">������</th>
					<td align="center" width="85%" colspan="3">
						${getFAQBoard.subject}</td>
				</tr>
			
				<tr>
					<th bgcolor="yellow" width="20%">�� ��</th>
					<td>
						<textarea name="content" width="55%"  align="center" rows="12" cols="55" class="box" /readonly>${getFAQBoard.content}</textarea></td>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="20%">�� �� ��</th>
					<td>
						<textarea name="content" width="55%"  align="center" rows="12" cols="55" class="box" /readonly>${getFAQBoard.content}</textarea></td>
					</td>
				</tr>
				
				<tr>
					<th bgcolor="yellow" width="15%">��ȸ��</th>
					<td align="center" width="35%">${getFAQBoard.count}</td>
				</tr>
				
				<tr>	
					<th bgcolor="yellow" width="15%">�ۼ���</th>
					<td align="center" width="35%">${getFAQBoard.day}</td>
				</tr>
	
	
				<tr bgcolor="yellow">
					<td colspan="4" align="right"><input type="button" value="�ۼ���"
						onclick="window.location='FAQ_update.do?no=${getFAQBoard.no}'">
					
						<input type="button" value="�ۻ���"
						onclick="window.location='FAQ_delete.do?no=${getFAQBoard.no}'">
						<input type="button" value="�۸��"
						onclick="window.location='FAQ_list.do'"></td>
				</tr>
			</table>
		</div>
	</td>
</tr>
<%@ include file="../../bottom.jsp" %>