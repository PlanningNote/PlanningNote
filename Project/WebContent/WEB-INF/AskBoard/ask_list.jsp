<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>���ǻ��� �Խ���</title>
</head>
<body>
	<div align="center">
		<b>�� �� �� �� </b>
		<table border="0" width="600">
			<tr bgcolor="sky blue">
				<td align="right"><a href="ask_writeForm.do">���Ǿ���</a></td>
			</tr>
		</table>
		<table border="1" width="600">
			<tr bgcolor="pink">
				<th>��ȣ</th>
				<th>�ۼ���</th>
				<th width="50%">����</th>
				<th>��ȸ��</th>
				<th>����</th>
				<th>��¥</th>
			</tr>
			<!-- ���⿡ db�� �ڷḦ ������ ǥ���� ���� -->
			<c:if test="${empty askList}">
				<tr>
					<td colspan="5">�Խõ� ���� �����ϴ�.</td>
				</tr>
			</c:if>

			<c:forEach var="dto" items="${askList}">
				<tr>
					<td>${dto.no}</td>
					<td>${dto.writer}</td>
					<td>${dto.subject}</td>
					<td>${dto.count}</td>
					<td>${dto.img}</td>
					<td>${dto.day}</td>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>












