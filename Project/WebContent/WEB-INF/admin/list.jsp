<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="admin_top.jsp" %>
<div align="center">
	<table border="1">
		<tr>
			<th>ȸ����ȣ</th>
			<th>�г���</th>
			<th>�̸���</th>
			<th>����</th>
			<th>����</th>
		</tr>
				
		<c:forEach var="dto" items="${getList}">
			<tr>
				<td></td>
				<td>${dto.nickname}</td>
				<td>${dto.email}</td>
				<td>${dto.gender}</td>
				<td><input type="button" value="����" onclick=""></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="admin_bottom.jsp"%>