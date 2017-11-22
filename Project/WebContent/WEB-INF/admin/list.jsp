<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="admin_top.jsp" %>
<div align="center">
	<table border="1">
		<tr>
			<th>회원번호</th>
			<th>닉네임</th>
			<th>이메일</th>
			<th>성별</th>
			<th>삭제</th>
		</tr>
				
		<c:forEach var="dto" items="${getList}">
			<tr>
				<td></td>
				<td>${dto.nickname}</td>
				<td>${dto.email}</td>
				<td>${dto.gender}</td>
				<td><input type="button" value="삭제" onclick=""></td>
			</tr>
		</c:forEach>
	</table>
</div>
<%@ include file="admin_bottom.jsp"%>