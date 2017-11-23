<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="admin_top.jsp" %>
<div align="center">
	<hr color="green" width="300">
	<h2>ȸ �� ã ��</h2>
	
	<form name="f" method="post" action="">	
		<select name="search">
			<option value="no">ȸ����ȣ</option>
			<option value="nickname">�г���</option>
			<option value="email">�̸���</option>
		</select>
		<input type="text" name="searchString">
		<input type="submit" value="ã��">
	</form>
	<hr color="green" width="300">
		
	<table border="1" width="600">
		<tr>
			<th>ȸ����ȣ</th>
			<th>�г���</th>
			<th width="50%">�̸���</th>
			<th>����</th>
			<th>����</th>
		</tr>
		
	<c:if test="${getList eq null}">
		<tr>
			<td colspan="5">�˻����ּ���</td>
		</tr>
	</c:if>	
		<c:forEach var="dto" items="${getList}">
			<tr>
				<td></td>
				<td>${dto.nickname}</td>
				<td>${dto.email}</td>
				<td>${dto.gender}</td>
				<td><input type="button" value="����" onclick="deleteMember()"></td>
			</tr>
		</c:forEach>	
	</table>
</div>
<%@ include file="admin_bottom.jsp"%>